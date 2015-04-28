package hello

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import twitter4j.conf.ConfigurationBuilder
import twitter4j.TwitterStreamFactory
import twitter4j.StatusListener
import twitter4j.Status
import twitter4j.FilterQuery
import twitter4j.StallWarning
import twitter4j.StatusDeletionNotice
import redis.RedisClient


class TwitterStream {
    
     val config = new ConfigurationBuilder()
    .setOAuthConsumerKey("BBC5CelxP2NKtSjzGrjKfEHbl")
    .setOAuthConsumerSecret("Y5k7hCkHDTTSJzUwWXBz4O30bHU6rlGydAZTedEc8GHVTvBiyG")
    .setOAuthAccessToken("3166270558-x1uMGZ2t9gcqOEQr90ww6OKDMGKI0n7xYdIKUJz")
    .setOAuthAccessTokenSecret("wWJXdqAgyR5iAXTgjqrMxsHvLoBWjVZCgTMbn5txEJQi8")
    .setUseSSL(true)
    .build

  
    val stream = new TwitterStreamFactory(config).getInstance()
    val kproducer = new KafkaProducer()
    

    class OnTweetPosted extends StatusListener {
      
      override def onStatus(status: Status): Unit = {
        kproducer.sendToKafka(status)
      }
      override def onException(ex: Exception): Unit = throw ex
  
      // no-op for the following events
      override def onStallWarning(warning: StallWarning): Unit = {}
      override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = {}
      override def onScrubGeo(userId: Long, upToStatusId: Long): Unit = {}
      override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}
    }
}


object Main extends App {

    implicit val akkaSystem = akka.actor.ActorSystem()
    
    val twitterStream = new TwitterStream()
    val stream = twitterStream.stream
    val kafkaconsumer = new KafkaConsumer()

    val sparkConf = new SparkConf().setAppName("StreamTest2").setMaster("spark://localhost:2181")
    val ssc = new StreamingContext(sparkConf, Seconds(2))

    val redis = RedisClient()

    //if (args.length < 1) {
     // System.err.println("Topic: " +twitterStream.kproducer.kafkaTopic)
    stream.addListener(new twitterStream.OnTweetPosted())
    stream.sample()
    kafkaconsumer.getData
    // System.exit(1)
   // }
    
    

  
  
}