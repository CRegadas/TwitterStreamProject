package hello

import akka.actor.{ActorSystem, Props, Actor}
import kafka.javaapi.producer.Producer
import redis.RedisClient

class Supervisor(redis: RedisClient, system: ActorSystem, stream : twitter4j.TwitterStream, topic : String, producer: Producer[String, String]) extends Actor {

  val filter = system.actorOf(Props(classOf[FilterControl]))
  val kproducer = system.actorOf(Props(classOf[KafkaProducer], redis, topic, producer, filter))
  val tstream = system.actorOf(Props(classOf[TwitterStream], stream, kproducer))

  tstream ! run

  def receive = {
    case _ => println("WTF????")
  }


}
