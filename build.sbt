name := "hello"

version := "1.0"

sbtVersion := "0.13.0"

resolvers ++= Seq(
  "Apache Staging" at "https://repository.apache.org/content/groups/staging/",
  "rediscala" at "http://dl.bintray.com/etaty/maven"
)

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka_2.10" %  "0.8.1.1"
    exclude("javax.jms", "jms")
    exclude("com.sun.jdmk", "jmxtools")
    exclude("com.sun.jmx", "jmxri")
    exclude("org.slf4j", "slf4j-simple"),
  "org.twitter4j" % "twitter4j-stream" % "4.0.3",
  "com.etaty.rediscala" %% "rediscala" % "1.4.0",
  "junit" % "junit" % "4.12" % "test",
  "org.apache.spark" %% "spark-core" % "1.3.0" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.3.0",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.3.0",
  "org.apache.spark" % "spark-streaming-twitter_2.10" % "1.1.0",
  "com.twitter" %% "bijection-core" % "0.7.0",
  "com.twitter" %% "bijection-avro" % "0.7.0"
)
