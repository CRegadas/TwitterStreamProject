package hello

import akka.actor.{PoisonPill, ActorSystem, Props, Actor}
import hello.mock.MockTwitterStream
import kafka.consumer.KafkaStream
import kafka.javaapi.producer.Producer
import redis.RedisClient
import twitter.extend.TwitterStreamExtend
import twitter4j.Status

case object filter

class Supervisor(redis: RedisClient, stream : MockTwitterStream, topic : String, producer: Producer[String, Array[Byte]]) extends Actor {

  val filterr = context.actorOf(Props(classOf[FilterControl]))
  val kproducer = context.actorOf(Props(classOf[KafkaProducer], redis, topic, producer, stream))
  val tstream = context.actorOf(Props(classOf[TwitterStream], stream, kproducer))

  def receive = {
    case `filter` => {
      println("coisas")
      kproducer ! filterC(filterr)
    }

    case PoisonPill => {
      kproducer ! PoisonPill
      tstream ! PoisonPill
      context.stop(self)
    }
    case _ => println("WTF????")
  }


}
