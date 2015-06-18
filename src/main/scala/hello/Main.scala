package hello

import java.util.Properties
import akka.actor.{PoisonPill, Props}
import hello.mock.MockTwitterStream

import kafka.javaapi.producer.Producer
import kafka.producer.ProducerConfig
import org.apache.spark.{SparkContext, Logging, SparkConf}
import org.apache.spark.streaming.dstream.{InputDStream, DStream, ReceiverInputDStream}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.RedisClient
import twitter4j.auth.{OAuthAuthorization, AuthorizationFactory}
import twitter4j.{TwitterFactory, Status, TwitterStreamFactory}
import twitter4j.conf.ConfigurationBuilder



object Main extends App with Logging {

        implicit val akkaSystem = akka.actor.ActorSystem()
        val redis = RedisClient()
        val topic = "teste"

        // TwitterStream configuration
        val config = new ConfigurationBuilder()
        .setOAuthConsumerKey("BBC5CelxP2NKtSjzGrjKfEHbl")
        .setOAuthConsumerSecret("Y5k7hCkHDTTSJzUwWXBz4O30bHU6rlGydAZTedEc8GHVTvBiyG")
        .setOAuthAccessToken("3166270558-x1uMGZ2t9gcqOEQr90ww6OKDMGKI0n7xYdIKUJz")
        .setOAuthAccessTokenSecret("wWJXdqAgyR5iAXTgjqrMxsHvLoBWjVZCgTMbn5txEJQi8")
        .setJSONStoreEnabled(true)
        .build

        //val stream: twitter4j.TwitterStream = new TwitterStreamFactory(config).getInstance()
        val stream = new MockTwitterStream

        /** Zookeeper connection properties **/
        val props = new Properties
        props.put("metadata.broker.list", "localhost:9092")
        props.put("serializer.class", "kafka.serializer.DefaultEncoder")
        props.put("producer.type", "sync")
        props.put("compression.codec", "gzip")

        val pconfig = new ProducerConfig(props)
        val producer = new Producer[String, Array[Byte]](pconfig)


        /** Creates the SuperVisor actor **/
        val twitterSuperVisor = akkaSystem.actorOf(Props(new Supervisor(redis, stream, topic, producer)))

        /** ask to filter the data **/
        twitterSuperVisor ! filter

        Thread.sleep(100000)

        twitterSuperVisor ! PoisonPill

        akkaSystem.shutdown()




}







































