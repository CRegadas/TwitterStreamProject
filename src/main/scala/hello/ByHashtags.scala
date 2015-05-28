package hello


import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util.{Date, Properties}

import akka.actor.{ActorRef, Actor}
import kafka.consumer.{ConsumerConfig, Consumer}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.kafka._
import org.apache.spark.SparkConf
import twitter4j._



case object selectTags

case class addTagsToRedis(ref: ActorRef)

class ByHashtags(propsConsume: Properties, topic: String) extends Actor{

  val consumer = Consumer.create(new ConsumerConfig(propsConsume))
  val topicMessageStreams = consumer.createMessageStreams(Map(topic -> 1))
  val kafkaStream = topicMessageStreams(topic)(0).iterator

//  val sparkConf = new SparkConf()
//    .setAppName("StreamTest2")
//    .setMaster("spark://lopo1048:7077")
//
//  val ssc = new StreamingContext(sparkConf, Seconds(2))
//
//  val kafkaStream2 = KafkaUtils.createStream(ssc,"",topic,Map(topic->1))

  val hashtags = Array[HashtagEntity]()

  def receive = {
    case addTagsToRedis(ref) => {
      println("entrei aqui!")
      println(kafkaStream.length)
      while(kafkaStream.hasNext()) {

        val is = new ObjectInputStream(new ByteArrayInputStream(kafkaStream.next().message()))
        val status = is.readObject().asInstanceOf[Status]

        is.close()

        println("HashtagEntities: "+status.getHashtagEntities.length)
        println("Depois: "+status)



        //if(status.getHashtagEntities.length>0){
          //Analisar se as hashtags encontradas num retweet sao as mesmas do tweet em causa
//          if(status.getRetweetCount>0){
//            println("entrei aqui2!")
//            status.getRetweetedStatus.getHashtagEntities.foreach(entity => {
//              println("HashTag: #" + entity.getText)
//              ref ! addHashtags(entity,status.getText)
//              hashtags:+entity
//            })
//          }else{
//            println("entrei aqui3!")
//            status.getHashtagEntities.foreach(entity => {
//              println("Hashtag: #"+entity.getText)
//              ref ! addHashtags(entity, status.getText)
//              hashtags:+entity
//            })
//          }
//        }
//
      }

    }

    case _ => println("WTF????")

  }


  def top5 =
  {

  }


}
