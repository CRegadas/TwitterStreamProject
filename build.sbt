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
  "org.twitter4j" % "twitter4j-stream" % "3.0.3",
  "com.etaty.rediscala" %% "rediscala" % "1.4.0",
  "org.apache.spark" % "spark-streaming_2.10" % "1.2.1"
)
