package Processing

trait IProcess[T] {

  def start() : scala.Unit = { }
  def collect() : T
  def setStreamingLogLevels() : scala.Unit = { }

}
