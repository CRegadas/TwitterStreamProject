package hello


import java.io.{Serializable, ByteArrayInputStream, ObjectInputStream}
import java.util
import java.util.{Date, Properties}

import akka.actor.{Props, ActorRef, Actor}
import kafka.consumer.{ConsumerConfig, Consumer}
import org.apache.spark.rdd.RDD
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
//  val sparkConf = new SparkConf()
//    .setAppName("StreamTest2")
//    .setMaster("spark://lopo1048:7077")
  val ssc = new StreamingContext(new SparkConf(), Seconds(2))

  def receive = {
    case addTagsToRedis(ref) => {
      println("entrei aqui_addTags!")

      /** create an Kafka Stream **/
      val encTweets: ReceiverInputDStream[(String, Array[Byte])] = {
        val topics = Map(topic -> 1)
        val kafkaParams = Map(
          "zookeeper.connect" -> "localhost:2181",
          "auto.commit.interval.ms" -> "100",
          "auto.commit.enable.reset" -> "true",
          "group.id" -> "16666")
        KafkaUtils.createStream[String, Array[Byte], StringDecoder, DefaultDecoder](
          ssc, kafkaParams, topics, StorageLevel.MEMORY_ONLY)
      }

      /** create an accumulator **/
      //val numInputTweet = ssc.sparkContext.accumulator(0L, "Kafka messages consumed")



//      val ds: DStream[Array[HashtagEntity]] = encTweets.map(pair => {
//        println("-------------------------------------------------------------")
//        getHashT(pair._2,Array[HashtagEntity]())
//      })

      val ds: DStream[Array[Byte]] = encTweets.map(_._2)
      val dhtags: DStream[String] = ds.flatMap(tweet => {
        val hashtags: Array[HashtagEntity] = new SparkTasks(ref).getHashT(tweet,Array[HashtagEntity]())
        List("waba_1","waba_2")
      })
      dhtags.print()


//      ssc.sparkContext.parallelize(1 to 1000).count()

      /** Send the processed data to be saved on redis **/
//      sHT.foreachRDD(rdd => rdd.foreach(hash => ref ! addHashtags(hash,"")))
//
//      dstreamHashtags.saveAsHadoopFiles()
//
//      ds.foreachRDD(rdd => {
//        println("RDD: "+rdd)
//        rdd.collect().map(println(_))
//        ref ! addHashtags(rdd,"")
//      })


      ssc.start()
      ssc.awaitTermination()


//
//      while(kafkaStream.hasNext()) {
//
//        val is = new ObjectInputStream(new ByteArrayInputStream(kafkaStream.next().message()))
//        val status = is.readObject().asInstanceOf[Status]
//
//        if(!hashtags.isEmpty)
//          hashtags.foreach(tag => {println("Tag do array hashtags: "+tag.getText)})
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
//            hashtags = hashtags:+entity
//            ref ! addHashtags(entity, status.getText)
//          })
//        }
//
//        if(status.getRetweetedStatus.getHashtagEntities!=null){
//          println("entrei aqui3!")
//          status.getRetweetedStatus.getHashtagEntities.foreach(entity => {
//            println("HashTag: #" + entity.getText)
//            hashtags = hashtags:+entity
//            ref ! addHashtags(entity,status.getText)
//          })
//        }
//
//        hashtags = hashtags.distinct
//
//      }

    }

    case _ => println("WTF????")

  }


  def top5 =
  {

  }


}
