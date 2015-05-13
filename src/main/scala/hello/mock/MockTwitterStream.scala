package hello.mock

import twitter4j._
import twitter4j.auth.{RequestToken, AccessToken, Authorization}
import twitter4j.conf.Configuration
import utils.{TwitterStreamExtend, StreamListenerExtend, UserStreamExtend, StatusStreamExtend}

class MockTwitterStream extends TwitterStreamExtend {

  override def filter(filterQuery: FilterQuery): Unit = ???

  override def shutdown(): Unit = ???

  override def sample(): Unit = ???

  override def links(i: Int): Unit = ???

  override def user(): Unit = ???

  override def user(strings: Array[String]): Unit = ???

  override def retweet(): Unit = ???

  override def site(b: Boolean, longs: Array[Long]): StreamController = ???

  override def addConnectionLifeCycleListener(connectionLifeCycleListener: ConnectionLifeCycleListener): Unit = ???

  def addListener(statusListener: StatusListener) =
  {

    val json = """{
                      "user_mention_entities": [
                          {
                              "id": 1022902286,
                              "text": "freda_mason",
                              "start": 3,
                              "name": "freda mason",
                              "screen_name": "freda_mason",
                              "end": 15
                          },
                          {
                              "id": 209176493,
                              "text": "Harryslaststand",
                              "start": 17,
                              "name": "Harry Leslie Smith",
                              "screen_name": "Harryslaststand",
                              "end": 33
                          }
                      ],
                      "retweeted_status": {
                          "user_mention_entities": [
                              {
                                  "id": 209176493,
                                  "text": "Harryslaststand",
                                  "start": 0,
                                  "name": "Harry Leslie Smith",
                                  "screen_name": "Harryslaststand",
                                  "end": 16
                              }
                          ],
                          "contributors": [],
                          "text": "@Harryslaststand I have just read a TORY tweet saying they are going to cut right back in northern cities. they do that all the time in N/ E",
                          "access_level": 0,
                          "url_entities": [],
                          "retweeted": false,
                          "in_reply_to_screen_name": "Harryslaststand",
                          "possibly_sensitive": false,
                          "hashtag_entities": [
                                {
                                    "text": "Athletics"
                                },
                                {
                                    "text": "Yankees"
                                }
                          ],
                          "truncated": false,
                          "media_entities": [],
                          "lang": "en",
                          "current_user_retweet_id": -1,
                          "id": 598077006157357000,
                          "source": "<a href=\"http://twitter.com/#!/download/ipad\" rel=\"nofollow\">Twitter for iPad</a>",
                          "favorited": false,
                          "retweet": false,
                          "in_reply_to_status_id": 597144930147016700,
                          "in_reply_to_user_id": 209176493,
                          "retweet_count": 1,
                          "created_at": "Tue May 12 11:47:24 WEST 2015",
                          "symbol_entities": [],
                          "favorite_count": 0,
                          "retweeted_by_me": false,
                          "user": {
                              "description_u_r_l_entities": [],
                              "profile_background_image_u_r_l": "http://abs.twimg.com/images/themes/theme1/bg.png",
                              "original_profile_image_u_r_l": "http://pbs.twimg.com/profile_images/3005772061/b64bcb075cfbbd325af089027dbdd51d.jpeg",
                              "original_profile_image_u_r_l_https": "https://pbs.twimg.com/profile_images/3005772061/b64bcb075cfbbd325af089027dbdd51d.jpeg",
                              "location": "",
                              "profile_background_tiled": false,
                              "profile_image_u_r_l_https": "https://pbs.twimg.com/profile_images/3005772061/b64bcb075cfbbd325af089027dbdd51d_normal.jpeg",
                              "statuses_count": 60215,
                              "lang": "en",
                              "profile_link_color": "0084B4",
                              "id": 1022902286,
                              "favourites_count": 645,
                              "protected": false,
                              "profile_text_color": "333333",
                              "profile_image_u_r_l": "http://pbs.twimg.com/profile_images/3005772061/b64bcb075cfbbd325af089027dbdd51d_normal.jpeg",
                              "verified": false,
                              "contributors_enabled": false,
                              "profile_sidebar_border_color": "C0DEED",
                              "name": "freda mason",
                              "profile_background_color": "C0DEED",
                              "created_at": "Wed Dec 19 21:14:41 WET 2012",
                              "followers_count": 776,
                              "access_level": 0,
                              "geo_enabled": true,
                              "bigger_profile_image_u_r_l_https": "https://pbs.twimg.com/profile_images/3005772061/b64bcb075cfbbd325af089027dbdd51d_bigger.jpeg",
                              "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
                              "follow_request_sent": false,
                              "bigger_profile_image_u_r_l": "http://pbs.twimg.com/profile_images/3005772061/b64bcb075cfbbd325af089027dbdd51d_bigger.jpeg",
                              "utc_offset": -1,
                              "mini_profile_image_u_r_l": "http://pbs.twimg.com/profile_images/3005772061/b64bcb075cfbbd325af089027dbdd51d_mini.jpeg",
                              "translator": false,
                              "_u_r_l_entity": {
                                  "expanded_u_r_l": "",
                                  "text": "",
                                  "start": 0,
                                  "display_u_r_l": "",
                                  "_u_r_l": "",
                                  "end": 0
                              },
                              "profile_use_background_image": true,
                              "friends_count": 893,
                              "profile_sidebar_fill_color": "DDEEF6",
                              "screen_name": "freda_mason",
                              "show_all_inline_media": false,
                              "mini_profile_image_u_r_l_https": "https://pbs.twimg.com/profile_images/3005772061/b64bcb075cfbbd325af089027dbdd51d_mini.jpeg",
                              "listed_count": 18
                          }
                      },
                      "contributors": [],
                      "text": "RT @freda_mason: @Harryslaststand I have just read a TORY tweet saying they are going to cut right back in northern cities. they do that al…",
                      "access_level": 0,
                      "retweeted": false,
                      "url_entities": [],
                      "possibly_sensitive": false,
                      "hashtag_entities": [
                              {
                                  "text": "Athletics"
                              },
                              {
                                  "text": "Yankees"
                              },
                              {
                                  "text": "Athletics2"
                              },
                              {
                                  "text": "Yankees2"
                              },
                              {
                                  "text": "Athletics3"
                              },
                              {
                                  "text": "Yankees3"
                              }
                      ],
                      "truncated": false,
                      "media_entities": [],
                      "lang": "en",
                      "current_user_retweet_id": -1,
                      "id": 598078574848036900,
                      "source": "<a href=\"http://twitter.com/#!/download/ipad\" rel=\"nofollow\">Twitter for iPad</a>",
                      "favorited": false,
                      "retweet": true,
                      "in_reply_to_status_id": -1,
                      "in_reply_to_user_id": -1,
                      "retweet_count": 0,
                      "created_at": "Tue May 12 11:53:38 WEST 2015",
                      "symbol_entities": [],
                      "favorite_count": 0,
                      "retweeted_by_me": false,
                      "user": {
                          "description_u_r_l_entities": [],
                          "profile_background_image_u_r_l": "http://pbs.twimg.com/profile_background_images/444740368/judes_sixtieth_030.jpg",
                          "original_profile_image_u_r_l": "http://pbs.twimg.com/profile_images/516339068813053952/7p6-QkJU.png",
                          "original_profile_image_u_r_l_https": "https://pbs.twimg.com/profile_images/516339068813053952/7p6-QkJU.png",
                          "location": "",
                          "profile_background_tiled": false,
                          "profile_image_u_r_l_https": "https://pbs.twimg.com/profile_images/516339068813053952/7p6-QkJU_normal.png",
                          "statuses_count": 73160,
                          "lang": "en",
                          "profile_link_color": "038543",
                          "id": 110460403,
                          "favourites_count": 136,
                          "protected": false,
                          "profile_text_color": "333333",
                          "profile_image_u_r_l": "http://pbs.twimg.com/profile_images/516339068813053952/7p6-QkJU_normal.png",
                          "verified": false,
                          "description": "Leaning as I age, leftwards. Angry too !",
                          "contributors_enabled": false,
                          "profile_sidebar_border_color": "C0DEED",
                          "name": "Magapanthus",
                          "profile_background_color": "ACDED6",
                          "created_at": "Mon Feb 01 16:39:00 WET 2010",
                          "followers_count": 1113,
                          "access_level": 0,
                          "geo_enabled": false,
                          "bigger_profile_image_u_r_l_https": "https://pbs.twimg.com/profile_images/516339068813053952/7p6-QkJU_bigger.png",
                          "profile_background_image_url_https": "https://pbs.twimg.com/profile_background_images/444740368/judes_sixtieth_030.jpg",
                          "follow_request_sent": false,
                          "bigger_profile_image_u_r_l": "http://pbs.twimg.com/profile_images/516339068813053952/7p6-QkJU_bigger.png",
                          "utc_offset": -1,
                          "mini_profile_image_u_r_l": "http://pbs.twimg.com/profile_images/516339068813053952/7p6-QkJU_mini.png",
                          "translator": false,
                          "_u_r_l_entity": {
                              "expanded_u_r_l": "",
                              "text": "",
                              "start": 0,
                              "display_u_r_l": "",
                              "_u_r_l": "",
                              "end": 0
                          },
                          "profile_use_background_image": true,
                          "friends_count": 1538,
                          "profile_sidebar_fill_color": "DDEEF6",
                          "screen_name": "magapanthus",
                          "show_all_inline_media": false,
                          "mini_profile_image_u_r_l_https": "https://pbs.twimg.com/profile_images/516339068813053952/7p6-QkJU_mini.png",
                          "listed_count": 34
                      }
                  }"""


          val status = TwitterObjectFactory.createStatus(json)
          println("Ola mundo " + status)
          (1 to 1000).map(x => {
            statusListener.onStatus(status); Thread.sleep(2000)
          })

  }


  def getLinksStream(i: Int): StatusStreamExtend = ???

  override def cleanUp(): Unit = ???

  override def firehose(i: Int): Unit = ???

  override def setOAuthConsumer(s: String, s1: String): Unit = ???

  override def getOAuthRequestToken: RequestToken = ???

  override def getOAuthRequestToken(s: String): RequestToken = ???

  override def getOAuthRequestToken(s: String, s1: String): RequestToken = ???

  override def setOAuthAccessToken(accessToken: AccessToken): Unit = ???

  override def getOAuthAccessToken: AccessToken = ???

  override def getOAuthAccessToken(s: String): AccessToken = ???

  override def getOAuthAccessToken(requestToken: RequestToken): AccessToken = ???

  override def getOAuthAccessToken(requestToken: RequestToken, s: String): AccessToken = ???

  override def getOAuthAccessToken(s: String, s1: String): AccessToken = ???

  override def addRateLimitStatusListener(rateLimitStatusListener: RateLimitStatusListener): Unit = ???

  override def getScreenName: String = ???

  override def getId: Long = ???

  override def getAuthorization: Authorization = ???

  override def getConfiguration: Configuration = ???

  override def sample(s: String): Unit = ???

  override def clearListeners(): Unit = ???

  override def getOAuthRequestToken(s: String, s1: String, s2: String): RequestToken = ???

  override def removeListener(streamListener: StreamListenerExtend): Unit = ???

  override def replaceListener(streamListener: StreamListenerExtend, streamListener1: StreamListenerExtend): Unit = ???

  override def addListener(streamListener: StreamListenerExtend): Unit = ???
}
