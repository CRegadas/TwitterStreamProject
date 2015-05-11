package hello.mock

import twitter4j._
import twitter4j.auth.{AccessToken, Authorization, RequestToken}
import twitter4j.conf.Configuration

//class MockTwitterStream extends TwitterStream {

//  override def filter(filterQuery: FilterQuery): Unit = ???
//
//  override def shutdown(): Unit = ???
//
//  override def sample(): Unit = ???
//
//  override def links(i: Int): Unit = ???
//
//  override def getFilterStream(filterQuery: FilterQuery): StatusStream = ???
//
//  override def getSampleStream: StatusStream = ???
//
//  override def getFirehoseStream(i: Int): StatusStream = ???
//
//  override def user(): Unit = ???
//
//  override def user(strings: Array[String]): Unit = ???
//
//  override def getUserStream: UserStream = ???
//
//  override def getUserStream(strings: Array[String]): UserStream = ???
//
//  override def getRetweetStream: StatusStream = ???
//
//  override def retweet(): Unit = ???
//
//  override def site(b: Boolean, longs: Array[Long]): StreamController = ???
//
//  override def addConnectionLifeCycleListener(connectionLifeCycleListener: ConnectionLifeCycleListener): Unit = ???
//
//  override def addListener(userStreamListener: UserStreamListener): Unit = ???
//
//  override def addListener(statusListener: StatusListener): Unit = {
//
//    val json = """{
//                      "createdAt": 1431018346000,
//                      "id": 596360285939757000,
//                      "text": "RT @calumpalla: 😂😂😂 http://t.co/oCyHefTZoU",
//                      "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
//                      "inReplyToStatusId": -1,
//                      "inReplyToUserId": -1,
//                      "inReplyToScreenName": null,
//                      "geoLocation": null,
//                      "place": null,
//                      "retweetCount": 0,
//                      "retweetedStatus": {
//                          "createdAt": 1431016845000,
//                          "id": 596353990864035800,
//                          "text": "😂😂😂 http://t.co/oCyHefTZoU",
//                          "source": "<a href=\"http://twitter.com/download/iphone\" rel=\"nofollow\">Twitter for iPhone</a>",
//                          "inReplyToStatusId": -1,
//                          "inReplyToUserId": -1,
//                          "inReplyToScreenName": null,
//                          "geoLocation": null,
//                          "place": null,
//                          "retweetCount": 5,
//                          "retweetedStatus": null,
//                          "userMentionEntities": [],
//                          "hashtagEntities": [
//                              {
//                                  "text": "Athletics"
//                              },
//                              {
//                                  "text": "Yankees"
//                              },
//                              {
//                                  "text": "Athletics2"
//                              },
//                              {
//                                  "text": "Yankees2"
//                              },
//                              {
//                                  "text": "Athletics3"
//                              },
//                              {
//                                  "text": "Yankees3"
//                              }
//                          ],
//                          "mediaEntities": [
//                              {
//                                  "start": 4,
//                                  "end": 26,
//                                  "id": 596353986493546500,
//                                  "url": "http://t.co/oCyHefTZoU",
//                                  "mediaURL": "http://pbs.twimg.com/media/CEasyoVWAAAZ8iD.jpg",
//                                  "mediaURLHttps": "https://pbs.twimg.com/media/CEasyoVWAAAZ8iD.jpg",
//                                  "expandedURL": "http://twitter.com/calumpalla/status/596353990864035840/photo/1",
//                                  "displayURL": "pic.twitter.com/oCyHefTZoU",
//                                  "sizes": {
//                                      "0": {
//                                          "width": 150,
//                                          "height": 150,
//                                          "resize": 101
//                                      },
//                                      "1": {
//                                          "width": 340,
//                                          "height": 604,
//                                          "resize": 100
//                                      },
//                                      "2": {
//                                          "width": 422,
//                                          "height": 750,
//                                          "resize": 100
//                                      },
//                                      "3": {
//                                          "width": 422,
//                                          "height": 750,
//                                          "resize": 100
//                                      }
//                                  },
//                                  "type": "photo"
//                              }
//                          ],
//                          "currentUserRetweetId": -1,
//                          "user": {
//                              "id": 405820024,
//                              "name": "Calum Palla",
//                              "screenName": "calumpalla",
//                              "location": "Swansea, Wales",
//                              "description": "18 | ChelseaFC | Instagram - calumpalla | ⚽️",
//                              "descriptionURLEntities": [],
//                              "profileImageUrlHttps": "https://pbs.twimg.com/profile_images/536654234377547777/Wis2NFTQ_normal.jpeg",
//                              "url": null,
//                              "followersCount": 18548,
//                              "status": null,
//                              "profileBackgroundColor": "17E658",
//                              "profileTextColor": "333333",
//                              "profileLinkColor": "0926E3",
//                              "profileSidebarFillColor": "DDEEF6",
//                              "profileSidebarBorderColor": "FFFFFF",
//                              "profileUseBackgroundImage": false,
//                              "showAllInlineMedia": false,
//                              "friendsCount": 18930,
//                              "createdAt": 1320527092000,
//                              "favouritesCount": 10646,
//                              "utcOffset": -21600,
//                              "timeZone": "Mountain Time (US & Canada)",
//                              "profileBackgroundImageUrl": "http://pbs.twimg.com/profile_background_images/435031978602024961/NlnDcjl4.jpeg",
//                              "profileBackgroundImageUrlHttps": "https://pbs.twimg.com/profile_background_images/435031978602024961/NlnDcjl4.jpeg",
//                              "profileBackgroundTiled": true,
//                              "lang": "en",
//                              "statusesCount": 26669,
//                              "translator": false,
//                              "listedCount": 10,
//                              "protected": false,
//                              "geoEnabled": true,
//                              "contributorsEnabled": false,
//                              "profileImageURL": "http://pbs.twimg.com/profile_images/536654234377547777/Wis2NFTQ_normal.jpeg",
//                              "biggerProfileImageURL": "http://pbs.twimg.com/profile_images/536654234377547777/Wis2NFTQ_bigger.jpeg",
//                              "miniProfileImageURL": "http://pbs.twimg.com/profile_images/536654234377547777/Wis2NFTQ_mini.jpeg",
//                              "originalProfileImageURL": "http://pbs.twimg.com/profile_images/536654234377547777/Wis2NFTQ.jpeg",
//                              "profileImageURLHttps": "https://pbs.twimg.com/profile_images/536654234377547777/Wis2NFTQ_normal.jpeg",
//                              "biggerProfileImageURLHttps": "https://pbs.twimg.com/profile_images/536654234377547777/Wis2NFTQ_bigger.jpeg",
//                              "miniProfileImageURLHttps": "https://pbs.twimg.com/profile_images/536654234377547777/Wis2NFTQ_mini.jpeg",
//                              "originalProfileImageURLHttps": "https://pbs.twimg.com/profile_images/536654234377547777/Wis2NFTQ.jpeg",
//                              "profileBackgroundImageURL": "http://pbs.twimg.com/profile_background_images/435031978602024961/NlnDcjl4.jpeg",
//                              "profileBannerURL": "https://pbs.twimg.com/profile_banners/405820024/1411085153/web",
//                              "profileBannerRetinaURL": "https://pbs.twimg.com/profile_banners/405820024/1411085153/web_retina",
//                              "profileBannerIPadURL": "https://pbs.twimg.com/profile_banners/405820024/1411085153/ipad",
//                              "profileBannerIPadRetinaURL": "https://pbs.twimg.com/profile_banners/405820024/1411085153/ipad_retina",
//                              "profileBannerMobileURL": "https://pbs.twimg.com/profile_banners/405820024/1411085153/mobile",
//                              "profileBannerMobileRetinaURL": "https://pbs.twimg.com/profile_banners/405820024/1411085153/ipad_retina",
//                              "verified": false,
//                              "followRequestSent": false,
//                              "urlentity": {
//                                  "start": 0,
//                                  "end": 0,
//                                  "url": "",
//                                  "expandedURL": "",
//                                  "displayURL": ""
//                              },
//                              "rateLimitStatus": null,
//                              "accessLevel": 0
//                          },
//                          "contributors": [],
//                          "truncated": false,
//                          "favorited": false,
//                          "retweet": false,
//                          "urlentities": [],
//                          "retweetedByMe": false,
//                          "possiblySensitive": false,
//                          "rateLimitStatus": null,
//                          "accessLevel": 0
//                      },
//                      "userMentionEntities": [
//                          {
//                              "start": 3,
//                              "end": 14,
//                              "name": "Calum Palla",
//                              "screenName": "calumpalla",
//                              "id": 405820024
//                          }
//                      ],
//                      "hashtagEntities": [
//                          {
//                              "text": "Athletics"
//                          },
//                          {
//                              "text": "Athletics2"
//                          },
//                          {
//                              "text": "Athletics3"
//                          },
//                          {
//                              "text": "Athletics4"
//                          },
//                          {
//                              "text": "Athletics"
//                          },
//                          {
//                              "text": "Yankees"
//                          }
//                      ],
//                      "mediaEntities": [
//                          {
//                              "start": 20,
//                              "end": 42,
//                              "id": 596353986493546500,
//                              "url": "http://t.co/oCyHefTZoU",
//                              "mediaURL": "http://pbs.twimg.com/media/CEasyoVWAAAZ8iD.jpg",
//                              "mediaURLHttps": "https://pbs.twimg.com/media/CEasyoVWAAAZ8iD.jpg",
//                              "expandedURL": "http://twitter.com/calumpalla/status/596353990864035840/photo/1",
//                              "displayURL": "pic.twitter.com/oCyHefTZoU",
//                              "sizes": {
//                                  "0": {
//                                      "width": 150,
//                                      "height": 150,
//                                      "resize": 101
//                                  },
//                                  "1": {
//                                      "width": 340,
//                                      "height": 604,
//                                      "resize": 100
//                                  },
//                                  "2": {
//                                      "width": 422,
//                                      "height": 750,
//                                      "resize": 100
//                                  },
//                                  "3": {
//                                      "width": 422,
//                                      "height": 750,
//                                      "resize": 100
//                                  }
//                              },
//                              "type": "photo"
//                          }
//                      ],
//                      "currentUserRetweetId": -1,
//                      "user": {
//                          "id": 378045278,
//                          "name": "Ryan phillips",
//                          "screenName": "RyanPhillips98",
//                          "location": "Hull",
//                          "description": "one day the blood wont flow so gladly",
//                          "descriptionURLEntities": [],
//                          "profileImageUrlHttps": "https://pbs.twimg.com/profile_images/585249027136061440/AYNNtQc8_normal.jpg",
//                          "url": null,
//                          "followersCount": 221,
//                          "status": null,
//                          "profileBackgroundColor": "C0DEED",
//                          "profileTextColor": "333333",
//                          "profileLinkColor": "0084B4",
//                          "profileSidebarFillColor": "DDEEF6",
//                          "profileSidebarBorderColor": "C0DEED",
//                          "profileUseBackgroundImage": true,
//                          "showAllInlineMedia": false,
//                          "friendsCount": 119,
//                          "createdAt": 1316704021000,
//                          "favouritesCount": 1110,
//                          "utcOffset": 7200,
//                          "timeZone": "Amsterdam",
//                          "profileBackgroundImageUrl": "http://abs.twimg.com/images/themes/theme1/bg.png",
//                          "profileBackgroundImageUrlHttps": "https://abs.twimg.com/images/themes/theme1/bg.png",
//                          "profileBackgroundTiled": false,
//                          "lang": "en",
//                          "statusesCount": 2592,
//                          "translator": false,
//                          "listedCount": 0,
//                          "protected": false,
//                          "geoEnabled": true,
//                          "contributorsEnabled": false,
//                          "profileImageURL": "http://pbs.twimg.com/profile_images/585249027136061440/AYNNtQc8_normal.jpg",
//                          "biggerProfileImageURL": "http://pbs.twimg.com/profile_images/585249027136061440/AYNNtQc8_bigger.jpg",
//                          "miniProfileImageURL": "http://pbs.twimg.com/profile_images/585249027136061440/AYNNtQc8_mini.jpg",
//                          "originalProfileImageURL": "http://pbs.twimg.com/profile_images/585249027136061440/AYNNtQc8.jpg",
//                          "profileImageURLHttps": "https://pbs.twimg.com/profile_images/585249027136061440/AYNNtQc8_normal.jpg",
//                          "biggerProfileImageURLHttps": "https://pbs.twimg.com/profile_images/585249027136061440/AYNNtQc8_bigger.jpg",
//                          "miniProfileImageURLHttps": "https://pbs.twimg.com/profile_images/585249027136061440/AYNNtQc8_mini.jpg",
//                          "originalProfileImageURLHttps": "https://pbs.twimg.com/profile_images/585249027136061440/AYNNtQc8.jpg",
//                          "profileBackgroundImageURL": "http://abs.twimg.com/images/themes/theme1/bg.png",
//                          "profileBannerURL": "https://pbs.twimg.com/profile_banners/378045278/1430234872/web",
//                          "profileBannerRetinaURL": "https://pbs.twimg.com/profile_banners/378045278/1430234872/web_retina",
//                          "profileBannerIPadURL": "https://pbs.twimg.com/profile_banners/378045278/1430234872/ipad",
//                          "profileBannerIPadRetinaURL": "https://pbs.twimg.com/profile_banners/378045278/1430234872/ipad_retina",
//                          "profileBannerMobileURL": "https://pbs.twimg.com/profile_banners/378045278/1430234872/mobile",
//                          "profileBannerMobileRetinaURL": "https://pbs.twimg.com/profile_banners/378045278/1430234872/ipad_retina",
//                          "verified": false,
//                          "followRequestSent": false,
//                          "urlentity": {
//                              "start": 0,
//                              "end": 0,
//                              "url": "",
//                              "expandedURL": "",
//                              "displayURL": ""
//                         },
//                         "rateLimitStatus": null,
//                         "accessLevel": 0
//                     },
//                     "contributors": [],
//                     "truncated": false,
//                     "favorited": false,
//                     "retweet": true,
//                     "urlentities": [],
//                     "retweetedByMe": false,
//                     "possiblySensitive": false,
//                     "rateLimitStatus": null,
//                     "accessLevel": 0
//                 }"""
//
//
////    val status = DataObjectFactory.createStatus(json)
// //   println("Ola mundo " + status)
////    (1 to 1000).map(x => {
////      statusListener.onStatus(status); Thread.sleep(2000)
////    })
//
//  }
//
//  override def addListener(siteStreamsListener: SiteStreamsListener): Unit = ???
//
//  override def addListener(rawStreamListener: RawStreamListener): Unit = ???
//
//  override def getLinksStream(i: Int): StatusStream = ???
//
//  override def cleanUp(): Unit = ???
//
//  override def firehose(i: Int): Unit = ???
//
//  override def setOAuthConsumer(s: String, s1: String): Unit = ???
//
//  override def getOAuthRequestToken: RequestToken = ???
//
//  override def getOAuthRequestToken(s: String): RequestToken = ???
//
//  override def getOAuthRequestToken(s: String, s1: String): RequestToken = ???
//
//  override def setOAuthAccessToken(accessToken: AccessToken): Unit = ???
//
//  override def getOAuthAccessToken: AccessToken = ???
//
//  override def getOAuthAccessToken(s: String): AccessToken = ???
//
//  override def getOAuthAccessToken(requestToken: RequestToken): AccessToken = ???
//
//  override def getOAuthAccessToken(requestToken: RequestToken, s: String): AccessToken = ???
//
//  override def getOAuthAccessToken(s: String, s1: String): AccessToken = ???
//
//  override def addRateLimitStatusListener(rateLimitStatusListener: RateLimitStatusListener): Unit = ???
//
//  override def getScreenName: String = ???
//
//  override def getId: Long = ???
//
//  override def getAuthorization: Authorization = ???
//
//  override def getConfiguration: Configuration = ???
//}
