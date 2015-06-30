package Processing

import Services.IServices
import org.apache.spark.streaming.dstream.DStream


class FilterControl[T](process: IProcess[DStream[(T, String)]], service: IServices[T]) {

  /** Collect hashtags and store in redis **/
  def filterByHashTags() =
  {

  	println("------------------------------------------FILTER_CONTROL")

    //var t = Array[(T, String)]()
    val hashtags: DStream[(T, String)] = process.collect()
    println("HASHTAGS no FILTER_CONTROL recolhidas do Kafka: "+hashtags.print())
    
	hashtags.foreachRDD( rdd => service.writeHashtags(rdd.collect()) )

    process.start()
  }




}
