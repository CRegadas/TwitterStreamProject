import AssemblyKeys._


name := "hello"

version := "1.0"

sbtVersion := "0.13.0"

resolvers ++= Seq(
  "Apache Staging" at "https://repository.apache.org/content/groups/staging/"
)

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka_2.10" %  "0.8.1.1"
    exclude("javax.jms", "jms")
    exclude("com.sun.jdmk", "jmxtools")
    exclude("com.sun.jmx", "jmxri")
    exclude("org.slf4j", "slf4j-simple"),
  "org.twitter4j" % "twitter4j-stream" % "4.0.3",
  "net.debasishg" %% "redisclient" % "3.0",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.3.0-M1",
  "junit" % "junit" % "4.12" % "test",
  "org.apache.spark" %% "spark-core" % "1.4.0" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.4.0",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.4.0",
  "org.apache.spark" % "spark-streaming-twitter_2.10" % "1.1.0"
)


assemblySettings

mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")            => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")        => MergeStrategy.discard
  case "log4j.properties"                                    => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/")   => MergeStrategy.filterDistinctLines
  case "reference.conf"                                      => MergeStrategy.concat
  case _                                                     => MergeStrategy.first
}
