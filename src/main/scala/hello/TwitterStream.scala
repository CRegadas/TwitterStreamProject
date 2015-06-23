package hello

import akka.actor.{ActorRef, Actor}
import hello.mock.MockTwitterStream
import twitter.extend.TwitterStreamExtend
import twitter4j._

case object run

class TwitterStream(stream : twitter4j.TwitterStream, kproducer : ActorRef) extends Actor {

  class OnTweetPosted extends StatusListener {

    override def onStatus(status: Status): Unit = {
      kproducer ! consume(status)
    }

    override def onException(ex: Exception): Unit = throw ex

    // no-op for the following events
    override def onStallWarning(warning: StallWarning): Unit = {}

    override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = {}

    override def onScrubGeo(userId: Long, upToStatusId: Long): Unit = {}

    override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}

  }

  stream.addListener((new OnTweetPosted()))
  stream.sample()

  def receive = {
    case _ => println("questamerda?!")
  }


}
