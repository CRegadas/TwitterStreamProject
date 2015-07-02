package Services

import twitter4j.Status

trait IServices[T] {

  def writeHashtags(hashs: Array[(T, String)]) : scala.Unit = { }
  def writeStatus(status : Status) : scala.Unit = { }
  def writePlaylist(tweet: String) : scala.Unit = { }
  def read() : scala.Unit = { }

}
