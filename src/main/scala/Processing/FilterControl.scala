package Processing

import Services.IServices
import org.apache.spark.streaming.dstream.DStream


class FilterControl[T](process: IProcess[DStream[(T, String)]], service: IServices[T]) {

  /** Collect hashtags and store in redis **/
  def filterByHashTags() =
  {
    var t = Array[(T, String)]()
    val hashtags: DStream[(T, String)] = process.collect()

    hashtags.foreachRDD(rdd =>{ t = rdd.collect()})
    service.writeHashtags(t)

    process.start()
  }




}
