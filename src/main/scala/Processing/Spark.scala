package Processing

import kafka.serializer.{DefaultDecoder, StringDecoder}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{SparkConf, Logging}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import twitter4j.HashtagEntity

class Spark extends IProcess[DStream[(HashtagEntity, String)]] with Logging{

  /** Collect all the data **/
   // val conf = new SparkConf().setAppName("Teste")                       
                                //.setMaster("spark://macbookarura.lan:7077")                          
                                //.setJars(Seq("/Users/sindz/MEI/Dissertacao/workspace/diriri/target/scala-2.10/hello-assembly-1.0.jar"))
  val ssc = new StreamingContext(new SparkConf(), Seconds(5))
  //var topHashtags = List[(String, Int)]()

  def init() : DStream[Array[Byte]] =
  {
    /** create an Kafka Stream **/
    val encTweets: ReceiverInputDStream[(String, Array[Byte])] = {
      val topics = Map("teste" -> 1)
      val kafkaParams = Map(
        "zookeeper.connect" -> "localhost:2181",
        "auto.commit.interval.ms" -> "100",
        "auto.commit.enable.reset" -> "true",
        "group.id" -> "16666")
      KafkaUtils.createStream[String, Array[Byte], StringDecoder, DefaultDecoder](
        ssc, kafkaParams, topics, StorageLevel.MEMORY_ONLY)
    }
    println("KAFKA_STREAM: "+encTweets.foreachRDD(rdd => { rdd.foreach(println) }))
    encTweets.map(_._2)
  }

  override def start() =
  {
    ssc.start()
    ssc.awaitTermination()
  }


  override def collect(): DStream[(HashtagEntity, String)] =
  {
    println("------------------------------------------SPARK_COLLECT")
    //var hashtags = List[(String, Int)]()

    val teste = init()
    println("------------------------------------------TESTE: "+teste.print())
    val dhtags: DStream[(HashtagEntity, String)] = teste.flatMap(t => {
      new TweetParserTasks().getHashT(t)
    })
    dhtags.foreachRDD(rdd => {val tags = rdd.collect(); tags.foreach(t=> { println("Hashyy: "+t._1.getText) })})

    //val newDS: DStream[((HashtagEntity, String), Int)] = dhtags.map(k => (k, 1)).reduceByKey(_ + _)
    //newDS.foreachRDD(rdd =>{val cena = rdd.collect(); cena.foreach(par =>{ println("HASH_GUARDADA: "+par); hashtags = hashtags:+(par._1._2 , par._2)})})
    //hashtags.foreach(println)
    //topHashtags = topHashtags ++ hashtags
    return dhtags

  }

  override def setStreamingLogLevels() =
  {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      logInfo("Setting log level to [WARN] for streaming example." +
        " To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }


}
