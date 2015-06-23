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
import org.apache.spark.{Logging, SparkConf}
import twitter4j._



case object selectTags
case class addTagsToRedis(ref: ActorRef)
case object top

class ByHashtags(propsConsume: Properties, topic: String) extends Actor with Logging{

//  val consumer = Consumer.create(new ConsumerConfig(propsConsume))
//  val topicMessageStreams = consumer.createMessageStreams(Map(topic -> 1))
//  val kafkaStream = topicMessageStreams(topic)(0).iterator

  /** estabelecer ligacao do spark com kafka **/
//  val sparkConf = new SparkConf()
//    .setAppName("StreamTest2")
//    .setMaster("spark://lopo1048:7077")
  val ssc = new StreamingContext(new SparkConf(), Seconds(2))
  var teste = Map[String,Integer]()

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

      /** Collect all the hashtags and store in redis **/
      val ds: DStream[Array[Byte]] = encTweets.map(_._2)
      val dhtags: DStream[(HashtagEntity, String)] = ds.flatMap(t => {
        new SparkTasks().getHashT(t)
      })
      dhtags.foreachRDD(rdd => {val tags = rdd.collect(); tags.foreach(t=> { println("Hashyy: "+t._1.getText); ref!addHashtags(t._1,t._2) })})

      val newDS: DStream[((HashtagEntity, String), Int)] = dhtags.map(k => (k, 1)).reduceByKey(_ + _)
      newDS.foreachRDD(rdd =>{val cena = rdd.collect(); cena.foreach(par =>{ println("HASH_GUARDADA: "+par); teste += par._1._2 -> par._2 })})
      teste.foreach(println)
      //Thread.sleep(800)

      ssc.start()
      ssc.awaitTermination()



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

    /** Most used hashtags on twitter **/
    case top => {





    }

    case _ => println("WTF????")

  }





}


