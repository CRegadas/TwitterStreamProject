package Collect

import Services.IServices
import hello.mock.MockTwitterStream
import twitter4j._

class TwitterStream(stream : twitter4j.TwitterStream, service: IServices[HashtagEntity]) {

  class OnTweetPosted extends StatusListener {

    override def onStatus(status: Status): Unit = {
      service.writeStatus(status)
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


}
