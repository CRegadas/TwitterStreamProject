package Services

import java.io.{ObjectOutputStream, ByteArrayOutputStream}
import java.util.Properties
import kafka.javaapi.producer.Producer
import kafka.producer.{KeyedMessage, ProducerConfig}
import twitter4j.{HashtagEntity, Status}

class KafkaService(producer: Producer[String, Array[Byte]], topic : String) extends IServices[HashtagEntity] {

  override def writeStatus(tweet : Status) =
  {
    /** Converter Status num Array[Byte] **/
    val bos = new ByteArrayOutputStream()
    val os = new ObjectOutputStream(bos)
    os.writeObject(tweet)
    val bytes = bos.toByteArray

    println("KafkaService_WRITE_STATUS")
    producer.send(new KeyedMessage[String, Array[Byte]](topic, bytes))
    Thread.sleep(2000)

    os.close()
  }

  override def writeHashtags(hashs: Array[(HashtagEntity, String)]) = {}

  override def read() =
  {
    // Consumer Properties
    val propsConsume: Properties = new Properties
    propsConsume.put("group.id", "1")
    propsConsume.put("auto.commit.interval.ms", "100")
    propsConsume.put("auto.commit.enable.reset", "true")
    propsConsume.put("zookeeper.connect", "localhost:2181")

  }


}
