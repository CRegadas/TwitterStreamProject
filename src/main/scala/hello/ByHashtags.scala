package hello


import java.util.Properties

import akka.actor.{ActorRef, Actor}
import kafka.consumer.{ConsumerConfig, Consumer}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.kafka._
import org.apache.spark.SparkConf
import twitter4j.{JSONObject, TwitterObjectFactory, HashtagEntity}
import utils.JSONUtils



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
      while(kafkaStream.hasNext()) {
        val json = new String(kafkaStream.next().message(), "UTF-8")

        val jsonObj: AnyRef = JSONUtils.prepareJSONObjectToStatus(new JSONObject(json))
        val str: String = jsonObj.toString
        println("Depois_em_ByHashtags: "+str)
        val statusJson = TwitterObjectFactory.createStatus(str)

        if(statusJson.getHashtagEntities.length>0){
          //Analisar se as hashtags encontradas num retweet sao as mesmas do tweet em causa
          if(statusJson.getRetweetCount>0){
            println("entrei aqui2!")
            statusJson.getRetweetedStatus.getHashtagEntities.foreach(entity => {
              println("HashTag: #" + entity.getText)
              ref ! addHashtags(entity,statusJson.getText)
              hashtags:+entity
            })
          }else{
            println("entrei aqui3!")
            statusJson.getHashtagEntities.foreach(entity => {
              println("Hashtag: #"+entity.getText)
              ref ! addHashtags(entity, statusJson.getText)
              hashtags:+entity
            })
          }
        }

      }

    }

    case _ => println("WTF????")

  }


  def top5 =
  {

  }


}
