package section3.actors

import akka.actor.ActorSystem
import akka.actor.Props
import java.net.ServerSocket
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object ActorChat extends App {
  val system = ActorSystem("ActorChat")
  val chatManager = system.actorOf(Props[ChatManager], "ChatManager")
  system.scheduler.schedule(0.seconds, 0.1.seconds, chatManager, ChatManager.CheckAllInput)

  val ss = new ServerSocket(4040)
  while (true) {
    val sock = ss.accept()
    val in = new BufferedReader(new InputStreamReader(sock.getInputStream))
    val out = new PrintStream(sock.getOutputStream)
    Future {
      out.println("What is your name?")
      val name = in.readLine()
      chatManager ! ChatManager.NewChatter(sock, in, out, name) 
    }
  }
}