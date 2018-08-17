name := "InClass"
version := "1.0"
scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % "2.5.14",
	"com.typesafe.akka" %% "akka-testkit" % "2.5.14" % Test,
	"org.scala-lang.modules" %% "scala-xml" % "1.1.0",
	"org.scalafx" %% "scalafx" % "8.0.144-R12",
	"com.novocode" % "junit-interface" % "0.11" % Test,
	"org.scalactic" %% "scalactic" % "3.0.5",
	"org.scalatest" %% "scalatest" % "3.0.5" % "test"
)


