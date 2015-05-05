package hello

import akka.actor.{ActorRef, Actor}
import twitter4j.StatusListener
import twitter4j.Status
import twitter4j.StallWarning
import twitter4j.StatusDeletionNotice

case object run

class TwitterStream(stream : twitter4j.TwitterStream, kproducer : ActorRef) extends Actor{

    class OnTweetPosted extends StatusListener {
      
      override def onStatus(status: Status): Unit = {
        kproducer ! KProd(status)
      }
      override def onException(ex: Exception): Unit = throw ex
      // no-op for the following events
      override def onStallWarning(warning: StallWarning): Unit = {}
      override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = {}
      override def onScrubGeo(userId: Long, upToStatusId: Long): Unit = {}
      override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}
    }

    def receive = {
      case run => {
        stream.addListener(new OnTweetPosted())
        stream.sample()
      }
    }

}


