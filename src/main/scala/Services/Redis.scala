package Services

import redis.RedisClient
import twitter4j.{HashtagEntity, Status}

class Redis extends IServices[HashtagEntity] {

  implicit val akkaSystem = akka.actor.ActorSystem()
  val redis = RedisClient()

  override def writeStatus(status : Status) =
  {
    redis.sadd(status.getUser.getScreenName, status.getText)
  }

  override def writeHashtags(hashs: Array[(HashtagEntity, String)]) =
  {
    println("BUUUUUUUU")
    hashs.foreach(pair =>{
      println("Redis-Hashtag: "+pair._1.getText)
      println("Redis-tweet: "+pair._2)
      redis.sadd("HashTag: "+pair._1.getText, pair._2)
    })
  }

  override def read() = {}


  akkaSystem.shutdown()

}


