package hello

import java.util.Properties
import akka.actor.{Props, ActorRef, Actor}

case class RequestFilter(ref: ActorRef)

class FilterControl extends Actor {

  // Consumer Properties
  val propsConsume: Properties = new Properties
  propsConsume.put("group.id", "1")
  propsConsume.put("auto.commit.interval.ms", "100")
  propsConsume.put("auto.commit.enable.reset", "true")
  propsConsume.put("zookeeper.connect", "localhost:2181")

  val filterRef = context.actorOf(Props(classOf[ByHashtags], propsConsume, "teste"))

  def receive = {
    case RequestFilter(ref) => {
      println("coises")
      filterRef ! addTagsToRedis(ref)
      //filterRef ! top
    }
    case _ => self ! true
  }


}
