package Processing

import Services.IServices
import org.apache.spark.streaming.dstream.DStream
import twitter4j._


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

  def findTweetsByTags(tag : String) : List[Status] =
  {
    // #ShikakaMusicBox
    var tweetsByHashtag = List[Status]()

    val qr: QueryResult = twitter.search(new Query(tag))
    var i = 0
    while(i < qr.getTweets.size()){
      tweetsByHashtag = tweetsByHashtag :+ qr.getTweets.get(i);
      i = i + 1
    }
    tweetsByHashtag.foreach(status => service.writePlaylist(status.getText))
    tweetsByHashtag
  }




}
