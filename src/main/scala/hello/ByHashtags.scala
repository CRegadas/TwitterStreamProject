package hello

import akka.actor.{ActorRef, Actor}
import twitter4j.Status

case object selectTags
case class printTags(status : Status, ref: ActorRef)

class ByHashtags extends Actor{

  def receive = {
    case printTags(status, ref) => {
      status.getHashtagEntities.foreach(entity => {
        //println("Hashtag2: #"+entity.getText)
        ref ! addHashtags(entity, status.getText)
      })
    }
    case _ => println("WTF????")

  }



}
