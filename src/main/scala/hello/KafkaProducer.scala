package hello

import java.util.Properties
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import kafka.producer.ProducerConfig
import twitter4j.{Status, FilterQuery}

//import scala.concurrent.Await


class KafkaProducer {
  
  // Zookeeper connection properties
  val kafkaTopic = "teste"
  
  val props = new Properties
  props.put("metadata.broker.list", "localhost:9092")
  props.put("serializer.class", "kafka.serializer.StringEncoder")
  props.put("producer.type", "sync")
  props.put("compression.codec", "gzip")
  
  val pconfig = new ProducerConfig(props)
  val producer = new Producer[String, String](pconfig)
  
  // func. que envia tweet para o kafka
  def sendToKafka(s: Status) = {
    val msg: String = s.getUser.getScreenName+'='+s.getText
    producer.send(new KeyedMessage[String, String](kafkaTopic, msg))
  } 

      
  
}