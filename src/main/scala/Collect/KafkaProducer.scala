package Collect

import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util.Date

import akka.actor.Actor
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import redis.RedisClient
import twitter4j.Status

case class consume(s : Status)

class KafkaProducer(redisClient: RedisClient, topic: String, producer: Producer[String, Array[Byte]], stream : twitter4j.TwitterStream) extends Actor {

  def receive = {
    case consume(s) =>
    {

      println("Antes: "+s)

      /** Converter Status num Array[Byte] **/
      val bos = new ByteArrayOutputStream()
      val os = new ObjectOutputStream(bos)
      os.writeObject(s)
      val bytes = bos.toByteArray

      /** envia uma msg para o kafka **/
      producer.send(new KeyedMessage[String, Array[Byte]](topic, bytes))

      /** add info. para o redis(a ser movido para outro sitio) **/
      //redisClient.sadd(s.getUser.getScreenName, s.getText)
      //Thread.sleep(4000)

      os.close()

    }

//    case filterC(filterRef) =>
//    {
//      filterRef ! RequestFilter(self)
//    }
//
//    case addHashtags(entity, tweet) =>
//    {
//      /** testar a latencia **/
//      val time = System.currentTimeMillis()
//      val date = new Date(time)
//      println("Time: "+date)
//
//      println("BUUUUUUUU")
//      println("Redis-Hashtag: "+entity.getText)
//      println("Redis-tweet: "+tweet)
//      redisClient.sadd("HashTag: "+entity.getText, tweet)
//    }

    case _ => println("questamerda?!")


  }

}