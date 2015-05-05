package hello

import java.util.{Date, Properties}
import akka.actor.{Props, ActorRef, ActorLogging, Actor}
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import redis.RedisClient
import twitter4j.Status
import twitter4j.HashtagEntity


case class KProd(s : Status)
case class addHashtags(entity : HashtagEntity, tweet: String)
case object canal

class KafkaProducer(redisClient: RedisClient, topic: String, producer: Producer[String, String], filterControl: ActorRef) extends Actor with ActorLogging {

  val count = "testee"

  def receive = {
    case KProd(s) => {
      //log.info("waba waba!");

      //      val time = System.currentTimeMillis()
      //      val date = new Date(time)
      //      println("Time: "+date)

      // envia um tweet para o kafka
//      val msg: String = s.getUser.getScreenName + '=' + s.getText
//      producer.send(new KeyedMessage[String, String](topic, msg))

      println("Nick: " + s.getUser.getScreenName)

      // add info. para o redis(vai ser deslocado para outro sitio)
      redisClient.sadd(s.getUser.getScreenName, s.getText)
      //Thread.sleep(2000)

      //ask to filter the data
      val propFilter = Props(classOf[ByHashtags])
      val ref: ActorRef = context.self
      filterControl ! RequestFilter(s, propFilter, ref)

    }
    case addHashtags(entity, tweet) => {
      println("BUUUUUUUU")
      redisClient.sadd("HashTag: "+entity.getText, tweet)
    }

    case _ => println("questamerda?!")
  }

}