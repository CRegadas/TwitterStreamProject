package Services

import twitter4j.Status

trait IServices[T] {

  def writeHashtags(hashs: Array[(T, String)])
  def writeStatus(status : Status)
  def read()

}
