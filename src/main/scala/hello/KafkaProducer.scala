package hello

import java.util.{Date, Properties}
import akka.actor._
import kafka.consumer.KafkaStream
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import redis.RedisClient
import twitter4j.Status
import twitter4j.HashtagEntity
import twitter4j.TwitterObjectFactory
import scala.collection.mutable._
import net.liftweb.json._
import net.liftweb.json.Serialization.write


case class consume(s : Status)
case class addHashtags(entity : HashtagEntity, tweet: String)
case class filterControl(filterRef: ActorRef)

class KafkaProducer(redisClient: RedisClient, topic: String, producer: Producer[String, Status]) extends Actor with ActorLogging {

  def receive = {
    case consume(s) => {
      println("primeiro: "+s)

      //log.info("waba waba!");

      //      val time = System.currentTimeMillis()
      //      val date = new Date(time)
      //      println("Time: "+date)

      // envia uma msg para o kafka
      //val msg: String = s.getUser.getScreenName + '=' + s.getText
	implicit val formats = DefaultFormats
	val json = write(s)	
      	println("JSONNN: "+json)
	producer.send(new KeyedMessage[String, Status](topic, s))

      println("Nick: " + s.getUser.getScreenName)

      // add info. para o redis(vai ser deslocado para outro sitio)
      redisClient.sadd(s.getUser.getScreenName, s.getText)
      Thread.sleep(2000)


    }

    case filterControl(filterRef) => {
      filterRef ! RequestFilter(self)
    }

    case addHashtags(entity, tweet) => {
      println("BUUUUUUUU")
      redisClient.sadd("HashTag: "+entity.getText, tweet)
    }

    case _ => println("questamerda?!")
  }

}
