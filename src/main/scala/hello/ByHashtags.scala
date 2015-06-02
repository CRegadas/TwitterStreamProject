package hello


import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util.{Date, Properties}

import akka.actor.{ActorRef, Actor}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import kafka.serializer.{DefaultDecoder, StringDecoder}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka._
import org.apache.spark.SparkConf
import twitter4j._



case object selectTags
case class addTagsToRedis(ref: ActorRef)

class ByHashtags(propsConsume: Properties, topic: String) extends Actor{

//  val consumer = Consumer.create(new ConsumerConfig(propsConsume))
//  val topicMessageStreams = consumer.createMessageStreams(Map(topic -> 1))
//  val kafkaStream = topicMessageStreams(topic)(0).iterator

  /** estabelecer ligacao do spark com kafka **/
  val sparkConf = new SparkConf()
    .setAppName("StreamTest2")
    .setMaster("spark://lopo1048:7077")
  val ssc = new StreamingContext(sparkConf, Seconds(5))

  def receive = {
    case addTagsToRedis(ref) => {
      println("entrei aqui!")

      /** create an Kafka Stream **/
      val encTweets: ReceiverInputDStream[(String, Array[Byte])] = {
        val topics = Map(topic -> 1)
        val kafkaParams = Map(
          "zookeeper.connect" -> "localhost:2181",
          "auto.commit.interval.ms" -> "100",
          "auto.commit.enable.reset" -> "true",
          "group.id" -> "1")
        KafkaUtils.createStream[String, Array[Byte], StringDecoder, DefaultDecoder](
          ssc, kafkaParams, topics, StorageLevel.MEMORY_ONLY)
      }

      /** create an accumulator **/
      val numInputTweet = ssc.sparkContext.accumulator(0L, "Kafka messages consumed")

      val ds: DStream[Array[Byte]] = encTweets.map(_._2)

      def getHashT(tweet: Array[Byte]) : Array[HashtagEntity] = {

        val ht = Array[HashtagEntity]()
        numInputTweet += 1
        println(numInputTweet)
        val status = new ObjectInputStream(new ByteArrayInputStream(tweet)).readObject().asInstanceOf[Status]
        println("Passei aqui!")
        if(status.getHashtagEntities!=null){
          //Analisar se as hashtags encontradas num retweet sao as mesmas do tweet em causa
          println("entrei aqui2!")
          status.getHashtagEntities.foreach(entity => {
            println("Hashtag: #"+entity.getText)

            //ref ! addHashtags(entity, status.getText)
            ht:+entity
          })
        }

        if(status.getRetweetedStatus.getHashtagEntities!=null){
          println("entrei aqui3!")
          status.getRetweetedStatus.getHashtagEntities.foreach(entity => {
            println("HashTag: #" + entity.getText)
            //ref ! addHashtags(entity,status.getText)
            ht:+entity
          })
        }

        return ht

      }

      val sHT: DStream[HashtagEntity] = ds.flatMap(s => getHashT(s))

      /** Send the processed data to be saved on redis **/
      sHT.foreachRDD(rdd => rdd.foreach(hash => ref ! addHashtags(hash,"")))

//      dstreamHashtags.saveAsHadoopFiles()

//      ds.foreachRDD(rdd => {
//        println("RDD: "+rdd)
//        rdd.collect().map(println(_))
//        ref ! addHashtags(rdd,"")
//      })


      ssc.start()
      ssc.awaitTermination()



//      while(kafkaStream.hasNext()) {
//
//        val is = new ObjectInputStream(new ByteArrayInputStream(kafkaStream.next().message()))
//        val status = is.readObject().asInstanceOf[Status]
//
//        is.close()
//
//        println("Depois: "+status)
//
//        if(status.getHashtagEntities!=null){
//          //Analisar se as hashtags encontradas num retweet sao as mesmas do tweet em causa
//          println("entrei aqui2!")
//          status.getHashtagEntities.foreach(entity => {
//            println("Hashtag: #"+entity.getText)
//            ref ! addHashtags(entity, status.getText)
//            hashtags:+entity
//          })
//        }
//
//        if(status.getRetweetedStatus.getHashtagEntities!=null){
//          println("entrei aqui3!")
//          status.getRetweetedStatus.getHashtagEntities.foreach(entity => {
//            println("HashTag: #" + entity.getText)
//            ref ! addHashtags(entity,status.getText)
//            hashtags:+entity
//          })
//        }
//
//      }

    }

    case _ => println("WTF????")

  }


  def top5 =
  {

  }


}
