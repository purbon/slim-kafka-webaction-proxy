name := """slim-kafka-webaction-proxy"""
organization := "com.purbon"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, DebianPlugin)

scalaVersion := "2.12.7"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.purbon.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.purbon.binders._"


libraryDependencies += "org.apache.kafka" %% "kafka" % "2.3.0"
libraryDependencies += "org.apache.kafka" % "kafka-tools" % "2.3.0" % Test
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.3.0"
libraryDependencies += "com.salesforce.kafka.test" % "kafka-junit-core" % "3.0.1" % Test

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.0"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.0"

javaOptions in Test += "-Dconfig.file=conf/test.conf"
javaOptions in Test += "-Dcom.sun.management.jmxremote"
javaOptions in Test += "-Dcom.sun.management.jmxremote.port=5678"
javaOptions in Test += "-Dcom.sun.management.jmxremote.rmi.port=5678"
javaOptions in Test += "-Dcom.sun.management.jmxremote.local.only=false "
javaOptions in Test += "-Dcom.sun.management.jmxremote.ssl=false"
javaOptions in Test += "-Dcom.sun.management.jmxremote.authenticate=false"
javaOptions in Test += "-Djava.rmi.server.hostname=0.0.0.0"

maintainer in Linux := "Pere Urbon <pere@confluent.io>"

packageSummary in Linux := "A slim kafka proxy"
packageDescription := "My longer package description"

rpmRelease := "1"
rpmVendor := "purbon.com"
rpmUrl := Some("https://github.com/purbon/slim-kafka-webaction-proxy")
rpmLicense := Some("Apache v2")
