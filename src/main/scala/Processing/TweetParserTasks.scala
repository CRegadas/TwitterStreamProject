package Processing

import java.io.{ByteArrayInputStream, ObjectInputStream}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.Logging
import twitter4j.{HashtagEntity, MediaEntity, Status}

class TweetParserTasks extends Serializable with Logging{

  setStreamingLogLevels()
  println("-----------------------------------SPARKTASKS_: WABA WABA")

  def getStatus(tweet : Array[Byte]) : Status = {new ObjectInputStream(new ByteArrayInputStream(tweet)).readObject().asInstanceOf[Status]}

  /** Get all the hashtags from tweet **/
  def getHashT(tweet: Array[Byte]) : Map[HashtagEntity,String] =
  {

    var ht = Map[HashtagEntity,String]()
    val status = getStatus(tweet)
    println("Passei aqui!")

    if(status.getHashtagEntities!=null){
      //Analisar se as hashtags encontradas num retweet sao as mesmas do tweet em causa
      println("entrei aqui2!")
      status.getHashtagEntities.foreach(entity => {
        println("Hashtag: #"+entity.getText)
        ht += entity -> status.getText

      })
    }

    if(status.getRetweetedStatus!=null){
      if(status.getRetweetedStatus.getHashtagEntities!=null){
        println("entrei aqui3!")
        status.getRetweetedStatus.getHashtagEntities.foreach(entity => {
          println("HashTag: #" + entity.getText)
          ht += entity -> status.getText
        })
      }
    }


    return ht

  }

  def getMyHashtag(tweet : Array[Byte]) : List[MediaEntity] =
  {
    var mht = List[MediaEntity]()
    val status = getStatus(tweet)



    if(status.getMediaEntities == null) return mht

    status.getMediaEntities.foreach(media => {
      println("URL: "+media.getMediaURL)
      mht = mht:+media
    })


    return mht
  }


  def setStreamingLogLevels() =
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




