package hello

import java.io.{ByteArrayInputStream, ObjectInputStream}
import akka.actor.ActorRef
import twitter4j.{Status, HashtagEntity}

class SparkTasks(ref: ActorRef) extends Serializable {

  /** Get all the hashtags from tweet **/
  def getHashT(tweet: Array[Byte], ht: Array[HashtagEntity]) : Array[HashtagEntity] = {
    println("WABA WABA")
    val status = new ObjectInputStream(new ByteArrayInputStream(tweet)).readObject().asInstanceOf[Status]
    println("Passei aqui!")
    if(status.getHashtagEntities!=null){
      //Analisar se as hashtags encontradas num retweet sao as mesmas do tweet em causa
      println("entrei aqui2!")
      status.getHashtagEntities.foreach(entity => {
        println("Hashtag: #"+entity.getText)
        ref ! addHashtags(entity, status.getText)
        ht:+entity
      })
    }

    if(status.getRetweetedStatus.getHashtagEntities!=null){
      println("entrei aqui3!")
      status.getRetweetedStatus.getHashtagEntities.foreach(entity => {
        println("HashTag: #" + entity.getText)
        ref ! addHashtags(entity,status.getText)
        ht:+entity
      })
    }

    return ht

  }


}