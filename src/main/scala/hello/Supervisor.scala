package hello

import akka.actor.{PoisonPill, ActorSystem, Props, Actor}
import kafka.consumer.KafkaStream
import kafka.javaapi.producer.Producer
import redis.RedisClient
import twitter4j.Status

case object filter

class Supervisor(redis: RedisClient, stream : twitter4j.TwitterStream, topic : String, producer: Producer[String, Status]) extends Actor {

  val filterr = context.actorOf(Props(classOf[FilterControl]))
  val kproducer = context.actorOf(Props(classOf[KafkaProducer], redis, topic, producer))
  val tstream = context.actorOf(Props(classOf[TwitterStream], stream, kproducer))

  def receive = {
    case filter => {
      println("coisas")
      kproducer ! filterControl(filterr)
    }

    case PoisonPill => {
      kproducer ! PoisonPill
      tstream ! PoisonPill
      context.stop(self)
    }
    case _ => println("WTF????")
  }


}
