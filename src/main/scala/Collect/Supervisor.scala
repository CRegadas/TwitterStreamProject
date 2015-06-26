package Collect

import Processing.FilterControl
import akka.actor.{Actor, PoisonPill, Props}
import kafka.javaapi.producer.Producer
import redis.RedisClient

case object filter

class Supervisor(redis: RedisClient, stream : twitter4j.TwitterStream, topic : String, producer: Producer[String, Array[Byte]])  {

//  val filterr = context.actorOf(Props(classOf[FilterControl]))
//  val kproducer = context.actorOf(Props(classOf[KafkaProducer], redis, topic, producer, stream))
//  val tstream = context.actorOf(Props(classOf[TwitterStream], stream, kproducer))
//
//  def receive = {
//    case `filter` => {
//      println("coisas")
//    }
//
//    case PoisonPill => {
//      kproducer ! PoisonPill
//      tstream ! PoisonPill
//      context.stop(self)
//    }
//    case _ => println("WTF????")
//  }


}
