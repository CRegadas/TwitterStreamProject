package hello

import akka.actor.{ActorLogging, Actor}
import kafka.consumer.{KafkaStream, ConsumerConfig, Consumer}
import redis.RedisClient
import scala.collection.Map

case object KConsumer


class KafkaConsumer(redisClient: RedisClient, kafkaStream: KafkaStream[Array[Byte],Array[Byte]]) extends Actor with ActorLogging{

  // get tweets from kafka
  def getData = {
    println("teste")
      for (message <- kafkaStream) {
        val msg: Array[String] = new String(message.message(), "UTF-8").split("=")
        println("testeee: "+ msg(0))

        // add info. para o redis
        redisClient.sadd(msg(0),msg(1))
        Thread.sleep(2000)
      }
    }

  def userTimeline(nick : String) = {
    val userTweets = redisClient.get(nick)
    println("timeline: "+nick)
    //userTweets.foreach(println)

  }


//  def close(){
//    consumer.shutdown()
//  }

  def receive = {
    case KConsumer => log.info("waba waba!"); getData
    case _      => self ! true
  }

  //  def receiveFromKafka(n : Integer, topicMessageStreams: Map[String, List[KafkaStream[Array[Byte], Array[Byte]]]]) = {
  //    //var messages : List[Message] = Nil
  //
  //    for ((topic, messageStreams) <- topicMessageStreams){
  //      for(message <- messageStreams){
  //        println("received message: " + message.toString())
  //      }
  //    }
  //    //return messages
  //
  //  }


  // user timeline
  //    val status = new TwitterFactory(config).getInstance.getUserTimeline
  //    status.map(x => println("@" + x.getUser().getScreenName() + " - " + x.getText()))
  //
  //getStream.addListener(new OnTweetPosted)

  //getStream.filter(new FilterQuery(Array(getStream.getId)))
  //    Thread.sleep(10000)
  //    getStream.cleanUp()
  //    getStream.shutdown()
  //getStream.sample()




}