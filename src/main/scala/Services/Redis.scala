package Services

import com.redis.RedisClient
import twitter4j.{HashtagEntity, Status}

class Redis extends IServices[HashtagEntity] {

  //implicit val akkaSystem = akka.actor.ActorSystem()
  val redis = new RedisClient("localhost", 6379)

  override def writeStatus(status : Status) =
  {
    println("--------------------------------------A GUARDAR USER NO REDIS")
    redis.sadd(status.getUser.getScreenName, status.getText)
  }

  override def writeHashtags(hashs: Array[(HashtagEntity, String)]) =
  {
    println("A GUARDAR AS HASHTAGS NO REDIS")
    println("--------------------- Hash_tam_redis: "+hashs.length)
    hashs.foreach(pair =>{
      println("Redis-Hashtag: "+pair._1.getText)
      println("Redis-tweet: "+pair._2)
      redis.sadd("HashTag: "+pair._1.getText, pair._2)
    })
  }

  override def writePlaylist(tweet: String) =
  {
    println("--------------------------------------A GUARDAR MUSICA NO REDIS")

    var list = ""
    var music = ""
    val teste: Array[String] = tweet.split("\\s")
    teste.foreach(s => {
      if(s.startsWith("#P")) list = s.split("#").apply(1)
      if(s.startsWith("http:")) music = s
    })

    println("--------------------------------------ADD A Playlist "+list+" A MUSICA : "+music)
    redis.sadd(list,music)



  }

  override def read() = {}


  //akkaSystem.shutdown()

}


