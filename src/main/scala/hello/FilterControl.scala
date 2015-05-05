package hello

import akka.actor.{ActorRef, Actor}
import twitter4j.Status
import twitter4j.HashtagEntity

case class RequestFilter(status : Status, props : akka.actor.Props, ref: ActorRef)

class FilterControl extends Actor {

  def receive = {
    case RequestFilter(status, props, ref) => {
      //println("lastTweet: "+status.getText)
      //println("Props: "+props.actorClass().getName)
      //println("Sender: "+ref.toString())
      if(props.actorClass().getName.equals("hello.ByHashtags")){
        if (!status.getHashtagEntities.isEmpty)
          context.actorOf(props) ! printTags(status, ref)
      }

    }

    case _ => self ! true
  }


}
