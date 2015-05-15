package hello

import java.util.{Date, Properties}
import akka.actor._
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import redis.RedisClient
import twitter.extend.TwitterStreamExtend

import twitter4j.{JSONObject, Status, HashtagEntity}
import utils.JSONUtils


case class consume(s : Status)
case class addHashtags(entity : HashtagEntity, tweet: String)
case class filterControl(filterRef: ActorRef)

class KafkaProducer(redisClient: RedisClient, topic: String, producer: Producer[String, String], stream : TwitterStreamExtend) extends Actor with ActorLogging {

  def receive = {
    case consume(s) => {

      /** testar a latencia **/
      //      val time = System.currentTimeMillis()
      //      val date = new Date(time)
      //      println("Time: "+date)


      //println("pois: "+new JSONObject(s))

      val jsonObj: AnyRef = JSONUtils.prepareJSONObjectToStatus(new JSONObject(s))
      val str: String = jsonObj.toString
      println("Antes: "+str)
      //val status: StatusJSONImpl = new StatusJSONImpl(jsonObj,stream.getConfiguration)
      // println("KP_JSON: "+jsonObj)

      /** envia uma msg para o kafka **/
      producer.send(new KeyedMessage[String, String](topic, str))

      //println("Nick: " + s.getUser.getScreenName)

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
