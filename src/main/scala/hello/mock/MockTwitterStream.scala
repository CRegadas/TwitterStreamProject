package hello.mock

import twitter.extend.{TwitterStreamExtend, StreamListenerExtend, StatusStreamExtend}
import twitter4j._
import twitter4j.auth.{RequestToken, AccessToken, Authorization}
import twitter4j.conf.Configuration
import twitter.extend.UserStreamExtend

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

    val json: String = """{
                        "text": "RT @PostGradProblem: In preparation for the NFL lockout, I will be spending twice as much time analyzing my fantasy baseball team during ...",
                        "truncated": true,
                        "in_reply_to_user_id": null,
                        "in_reply_to_status_id": null,
                        "favorited": false,
                        "source": "<a href=\"http://twitter.com/\" rel=\"nofollow\">Twitter for iPhone</a>",
                        "in_reply_to_screen_name": null,
                        "in_reply_to_status_id_str": null,
                        "id_str": "54691802283900928",
                        "entities": {
                              "user_mentions": [
                                    {
                                          "indices": [
                                                3,
                                                19
                                          ],
                                          "screen_name": "PostGradProblem",
                                          "id_str": "271572434",
                                          "name": "PostGradProblems",
                                          "id": 271572434
                                    }
                              ],
                              "urls": [ ],
                              "hashtags": [ ]
                        },
                        "contributors": null,
                        "retweeted": false,
                        "in_reply_to_user_id_str": null,
                        "place": null,
                        "retweet_count": 4,
                        "created_at": "Sun Apr 03 23:48:36 +0000 2011",
                        "retweeted_status": {
                              "text": "In preparation for the NFL lockout, I will be spending twice as much time analyzing my fantasy baseball team during company time. #PGP",
                              "truncated": false,
                              "in_reply_to_user_id": null,
                              "in_reply_to_status_id": null,
                              "favorited": false,
                              "source": "<a href=\"http://www.hootsuite.com\" rel=\"nofollow\">HootSuite</a>",
                              "in_reply_to_screen_name": null,
                              "in_reply_to_status_id_str": null,
                              "id_str": "54640519019642881",
                              "entities": {
                                  "media": [
                                      {
                                        "id": 76360760611180544,
                                        "id_str": "76360760611180544",
                                        "media_url": "http://p.twimg.com/AQ9JtQsCEAA7dEN.jpg",
                                        "media_url_https": "https://p.twimg.com/AQ9JtQsCEAA7dEN.jpg",
                                        "url": "http://t.co/qbJx26r",
                                        "display_url": "pic.twitter.com/qbJx26r",
                                        "expanded_url": "http://twitter.com/twitter/status/76360760606986241/photo/1",
                                        "sizes": {
                                          "large": {
                                            "w": 700,
                                            "resize": "fit",
                                            "h": 466
                                          },
                                          "medium": {
                                            "w": 600,
                                            "resize": "fit",
                                            "h": 399
                                          },
                                          "small": {
                                            "w": 340,
                                            "resize": "fit",
                                            "h": 226
                                          },
                                          "thumb": {
                                            "w": 150,
                                            "resize": "crop",
                                            "h": 150
                                          }
                                        },
                                        "type": "photo",
                                        "indices": [
                                          34,
                                          53
                                        ]
                                      }
                                    ],
                                    "user_mentions": [ ],
                                    "urls": [ ],
                                    "hashtags": [
                                          {
                                                "text": "PGP",
                                                "indices": [
                                                      130,
                                                      134
                                                ]
                                          },
                                          {
                                                "text": "LaLa",
                                                "indices": [
                                                      130,
                                                      134
                                                ]
                                           }
                                    ]
                              },
                              "contributors": null,
                              "retweeted": false,
                              "in_reply_to_user_id_str": null,
                              "place": null,
                              "retweet_count": 4,
                              "created_at": "Sun Apr 03 20:24:49 +0000 2011",
                              "user": {
                                    "notifications": null,
                                    "profile_use_background_image": true,
                                    "statuses_count": 31,
                                    "profile_background_color": "C0DEED",
                                    "followers_count": 3066,
                                    "profile_image_url": "http://a2.twimg.com/profile_images/1285770264/PGP_normal.jpg",
                                    "listed_count": 6,
                                    "profile_background_image_url": "http://a3.twimg.com/a/1301071706/images/themes/theme1/bg.png",
                                    "description": "",
                                    "screen_name": "PostGradProblem",
                                    "default_profile": true,
                                    "verified": false,
                                    "time_zone": null,
                                    "profile_text_color": "333333",
                                    "is_translator": false,
                                    "profile_sidebar_fill_color": "DDEEF6",
                                    "location": "",
                                    "id_str": "271572434",
                                    "default_profile_image": false,
                                    "profile_background_tile": false,
                                    "lang": "en",
                                    "friends_count": 21,
                                    "protected": false,
                                    "favourites_count": 0,
                                    "created_at": "Thu Mar 24 19:45:44 +0000 2011",
                                    "profile_link_color": "0084B4",
                                    "name": "PostGradProblems",
                                    "show_all_inline_media": false,
                                    "follow_request_sent": null,
                                    "geo_enabled": false,
                                    "profile_sidebar_border_color": "C0DEED",
                                    "url": null,
                                    "id": 271572434,
                                    "contributors_enabled": false,
                                    "following": null,
                                    "utc_offset": null
                              },
                              "id": 54640519019642880,
                              "coordinates": null,
                              "geo": null
                        },
                        "user": {
                              "notifications": null,
                              "profile_use_background_image": true,
                              "statuses_count": 351,
                              "profile_background_color": "C0DEED",
                              "followers_count": 48,
                              "profile_image_url": "http://a1.twimg.com/profile_images/455128973/gCsVUnofNqqyd6tdOGevROvko1_500_normal.jpg",
                              "listed_count": 0,
                              "profile_background_image_url": "http://a3.twimg.com/a/1300479984/images/themes/theme1/bg.png",
                              "description": "watcha doin in my waters?",
                              "screen_name": "OldGREG85",
                              "default_profile": true,
                              "verified": false,
                              "time_zone": "Hawaii",
                              "profile_text_color": "333333",
                              "is_translator": false,
                              "profile_sidebar_fill_color": "DDEEF6",
                              "location": "Texas",
                              "id_str": "80177619",
                              "default_profile_image": false,
                              "profile_background_tile": false,
                              "lang": "en",
                              "friends_count": 81,
                              "protected": false,
                              "favourites_count": 0,
                              "created_at": "Tue Oct 06 01:13:17 +0000 2009",
                              "profile_link_color": "0084B4",
                              "name": "GG",
                              "show_all_inline_media": false,
                              "follow_request_sent": null,
                              "geo_enabled": false,
                              "profile_sidebar_border_color": "C0DEED",
                              "url": null,
                              "id": 80177619,
                              "contributors_enabled": false,
                              "following": null,
                              "utc_offset": -36000
                        },
                        "id": 54691802283900930,
                        "coordinates": null,
                        "geo": null
                  }"""

          val status = TwitterObjectFactory.createStatus(json)
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
