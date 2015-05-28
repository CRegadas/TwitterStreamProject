package hello

import java.io.{ObjectOutputStream, ByteArrayOutputStream}
import java.util.{Date, Properties}
import akka.actor._
import hello.mock.MockTwitterStream
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import redis.RedisClient
import twitter4j.Status

import twitter4j._


case class consume(s : Status)
case class addHashtags(entity : HashtagEntity, tweet: String)
case class filterControl(filterRef: ActorRef)

class KafkaProducer(redisClient: RedisClient, topic: String, producer: Producer[String, Array[Byte]], stream : MockTwitterStream) extends Actor {

  def receive = {
    case consume(s) => {

      /** testar a latencia **/
      //      val time = System.currentTimeMillis()
      //      val date = new Date(time)
      //      println("Time: "+date)


      println("Antes: "+s)
      println("HashtagEntities: "+s.getHashtagEntities.length)
      println("HashtagEntities_user: "+s.getRetweetedStatus.getHashtagEntities.length)

      /** Converter Status no formato Tweet **/

      val bos = new ByteArrayOutputStream()
      val os = new ObjectOutputStream(bos)
      os.writeObject(s)
      val bytes = bos.toByteArray


      /** envia uma msg para o kafka **/
      producer.send(new KeyedMessage[String, Array[Byte]](topic, bytes))

      // add info. para o redis(vai ser deslocado para outro sitio)
      redisClient.sadd(s.getUser.getScreenName, s.getText)
      Thread.sleep(2000)

      os.close()


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
