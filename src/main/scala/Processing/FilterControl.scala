package Processing

import Services.IServices
import org.apache.spark.streaming.dstream.DStream
import twitter4j._
import collection.JavaConversions._


class FilterControl[T](process: IProcess[DStream[(T, String)]], service: IServices[T], twitter : Twitter) {

  /** Collect hashtags and store in redis **/
  def filterByHashTags() =
  {

  	println("------------------------------------------FILTER_CONTROL")

    //var t = Array[(T, String)]()
    val data: DStream[(T, String)] = process.collect()
    println("HASHTAGS no FILTER_CONTROL recolhidas do Kafka: "+data.print())

	  data.foreachRDD( rdd => service.writeHashtags(rdd.collect()) )

    process.start()

  }

  def findTweetsByTags(tag : String) : Array[Status] =
  {
    // #ShikakaMusicBox
    val query = new Query(tag)
    // Max allowed is 100
    query.setCount(50)
    query.getSince
    val qr: QueryResult = twitter.search(query)
    val tweetsByHashtag = (qr.getTweets: Iterable[Status]).toArray
    tweetsByHashtag.foreach(status =>{
      println("---------------------------------- USER "+status.getUser.getScreenName+" subscreveu o servico ShikakaMusicBox!")
      service.writePlaylist(status.getText);

    })

    tweetsByHashtag
  }




}
