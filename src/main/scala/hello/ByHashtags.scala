package hello

import java.util.Properties

import akka.actor.{ActorRef, Actor}
import kafka.consumer.{ConsumerConfig, Consumer}
import twitter4j.{JSONObject, TwitterObjectFactory, HashtagEntity}
import utils.JSONUtils


case object selectTags

case class addTagsToRedis(ref: ActorRef)

class ByHashtags(propsConsume: Properties, topic: String) extends Actor{

  val consumer = Consumer.create(new ConsumerConfig(propsConsume))
  val topicMessageStreams = consumer.createMessageStreams(Map(topic -> 1))
  val kafkaStream = topicMessageStreams(topic)(0).iterator

  val hashtags = Array[HashtagEntity]()

  def receive = {
    case addTagsToRedis(ref) => {
      //println("entrei aqui!")
      while(kafkaStream.hasNext()) {
        val json = new String(kafkaStream.next().message(), "UTF-8")
        //println("Depois: "+json)
        val jsonObj: AnyRef = JSONUtils.prepareJSONObjectToStatus(new JSONObject(json))
        val str: String = jsonObj.toString
        println("Depois: "+str)
        val statusJson = TwitterObjectFactory.createStatus(str)
	      //println("JSON: "+statusJson)

        statusJson.getHashtagEntities.foreach(entity => {
          println("Hashtag: #"+entity.getText)
          ref ! addHashtags(entity, statusJson.getText)
          hashtags:+entity
        })

      }

    }

    case _ => println("WTF????")

  }



}
