package hello

import java.util.Properties
import kafka.consumer.ConsumerConfig
import kafka.consumer.Consumer
import scala.collection.Map
import hello.Main.redis



class KafkaConsumer {

  // private val logger = Logger.getLogger(getClass())

  val topic = "teste"

  val props = new Properties
  props.put("group.id", "1")
  props.put("auto.commit.interval.ms", "100")
  props.put("auto.commit.enable.reset", "true")
  props.put("zookeeper.connect", "localhost:2181")


  val consumer = Consumer.create(new ConsumerConfig(props))
  val topicMessageStreams = consumer.createMessageStreams(Map(topic -> 1))

  val test = topicMessageStreams.get(topic).get


  // get tweets from kafka
  def getData = {
    println("teste")
    for (stream <- test) {
      for (message <- stream) {
        val msg: Array[String] = new String(message.message, "UTF-8").split("=")
        println("testeee: "+ msg(0))

        // add info. para o redis
        redis.sadd(msg(0),msg(1))
        Thread.sleep(2000)

      }
    }
  }

  def filterData = {


  }



  def close(){
    consumer.shutdown()
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