package hello

import java.util.Properties
import akka.actor.{PoisonPill, Props}
import hello.mock.MockTwitterStream

import kafka.javaapi.producer.Producer
import kafka.producer.ProducerConfig
import kafka.serializer.{DefaultDecoder, StringDecoder}
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import scala.math.random
import org.apache.spark.{SparkContext, Logging, SparkConf}
import org.apache.spark.streaming.dstream.{InputDStream, DStream, ReceiverInputDStream}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.RedisClient
import twitter4j.auth.{OAuthAuthorization, AuthorizationFactory}
import twitter4j.{TwitterFactory, Status, TwitterStreamFactory}
import twitter4j.conf.ConfigurationBuilder

import scala.collection.immutable.Queue
import scala.collection.mutable.ArrayBuffer


object Main {

    //    implicit val akkaSystem = akka.actor.ActorSystem()
    //    val redis = RedisClient()
    //    val topic = "teste"
    //
    //    // TwitterStream configuration
    //    val config = new ConfigurationBuilder()
    //    .setOAuthConsumerKey("BBC5CelxP2NKtSjzGrjKfEHbl")
    //    .setOAuthConsumerSecret("Y5k7hCkHDTTSJzUwWXBz4O30bHU6rlGydAZTedEc8GHVTvBiyG")
    //    .setOAuthAccessToken("3166270558-x1uMGZ2t9gcqOEQr90ww6OKDMGKI0n7xYdIKUJz")
    //    .setOAuthAccessTokenSecret("wWJXdqAgyR5iAXTgjqrMxsHvLoBWjVZCgTMbn5txEJQi8")
    //    .setJSONStoreEnabled(true)
    //    .build
    //
    //
    //    //val stream: twitter4j.TwitterStream = new TwitterStreamFactory(config).getInstance()
    //    val stream = new MockTwitterStream
    //
    //
    //    /** Zookeeper connection properties **/
    //    val props = new Properties
    //    props.put("metadata.broker.list", "localhost:9092")
    //    props.put("serializer.class", "kafka.serializer.DefaultEncoder")
    //    props.put("producer.type", "sync")
    //    props.put("compression.codec", "gzip")
    //
    //    val pconfig = new ProducerConfig(props)
    //    val producer = new Producer[String, Array[Byte]](pconfig)
    //
    //
    //    /** Creates the SuperVisor actor **/
    //    val twitterSuperVisor = akkaSystem.actorOf(Props(new Supervisor(redis, stream, topic, producer)))
    //
    //    /** ask to filter the data **/
    //    twitterSuperVisor ! filter
    //
    //    Thread.sleep(100000)
    //
    //    twitterSuperVisor ! PoisonPill
    //
    //    akkaSystem.shutdown()


    //        val sparkConf = new SparkConf()
    //              .setAppName("Spark Count")
    //              .setMaster("spark://lopo1048:7077")
    //        val sc = new SparkContext(sparkConf)
    //
    //        // split each document into words
    //        val tokenized = sc.textFile("/home/carlaregadas/workspace/diriri/teste.txt").flatMap(_.split(" "))
    //
    //        // count the occurrence of each word
    //        val wordCounts = tokenized.map((_, 1)).reduceByKey(_ + _)
    //
    //        // count characters
    //        val charCounts = wordCounts.flatMap(_._1.toCharArray).map((_, 1)).reduceByKey(_ + _)
    //        println(charCounts.collect().mkString(", "))


    //object Main2 extends App with Logging {

    //    /** estabelecer ligacao do spark com kafka **/
    //    val sparkConf = new SparkConf()
    //      .setAppName("Spark Twitter Stream")
    //      .setMaster("spark://lopo1048:7077")
    //    val sc2 = new SparkContext(sparkConf)
    //
    //
    //    // Create the context
    //    val ssc = new StreamingContext(sparkConf, Seconds(5))
    //
    //    val topics = Set("teste")
    //    val kafkaParams = Map(
    //        "metadata.broker.list" -> "localhost:9092",
    //        "zookeeper.connect" -> "localhost:2181",
    //        "auto.commit.interval.ms" -> "100",
    //        "auto.commit.enable.reset" -> "true",
    //        "group.id" -> "1234")
    //    val stream: InputDStream[(String, Array[Byte])] = KafkaUtils.createDirectStream[String, Array[Byte],
    //      StringDecoder, DefaultDecoder](ssc, kafkaParams, topics)
    //
    //    /** Transformations operations **/
    //    stream.foreachRDD(rdd => {
    ////            rdd.map()
    //    })


    //    /** Example just for tests **/

    //    val sparkConf = new SparkConf()
    //      .setAppName("Spark Twitter Stream")
    //      .setMaster("spark://lopo1048:7077")
    //
    //    val sc = new SparkContext(sparkConf)
    //
    //    // Create RDD
    //    val lines = Seq("To be or not to be.", "That is the question.")
    //    val rdd : RDD[String] = sc.parallelize(lines,2)
    //
    //    /** Transformations operations **/
    //    println("Teste1")
    //    val words = rdd.flatMap(_.split(" "))
    //    val wordsCount: RDD[(String, Int)] = words.map((_,1)).reduceByKey(_ + _)
    //    wordsCount.foreach(println)
    //
    //    sc.stop()


    //}



    def main(args: Array[String]) {
        val textFile = "teste.txt"

        val conf = new SparkConf()
          .setAppName("Spark Pi")
          .setMaster("spark://lopo1048:7077")
          .setJars(List("target/scala-2.10/hello_2.10-1.0.jar"))
        val sc = new SparkContext(conf)

        val logData = sc.textFile(textFile, 2).cache()
        val numAs = logData.filter(line => line.contains("a")).count()
        val numBs = logData.filter(line => line.contains("b")).count()
        println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
        sc.stop()
    }








}







































