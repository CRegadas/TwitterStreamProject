package hello

import java.io.{FileInputStream, ObjectInputStream, ByteArrayInputStream}
import java.util.Properties

import akka.actor.{ActorRef, Actor}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.smile.SmileFactory
import kafka.consumer.{ConsumerConfig, Consumer}
import twitter4j.{Status, HashtagEntity, TwitterObjectFactory}

case object selectTags

case class addTagsToRedis(ref: ActorRef)

class ByHashtags(propsConsume: Properties, topic: String) extends Actor{

  val consumer = Consumer.create(new ConsumerConfig(propsConsume))
  val topicMessageStreams = consumer.createMessageStreams(Map(topic -> 1))
  val kafkaStream = topicMessageStreams(topic)(0).iterator

  val hashtags = Array[HashtagEntity]()

  def receive = {
    case addTagsToRedis(ref) => {
      println("entrei aqui!")
      while(kafkaStream.hasNext()) {
        println("entrei aqui!")

	//val json = new String(kafkaStream.next().message(), "UTF-8")
        val obj = new ObjectMapper()
       // val status = obj.readValue(kafkaStream.next().message(), classOf[Status])
	//println("JSON: "+json)
        //val string = new String(kafkaStream.next().message(), "UTF-8")
        //println("Lixera:" +string)


//        status.getHashtagEntities.foreach(entity => {
//          println("Hashtag: #"+entity.getText)
//          ref ! addHashtags(entity, status.getText)
//          hashtags:+entity
//        })

      }

    }

    case _ => println("WTF????")

  }



}
