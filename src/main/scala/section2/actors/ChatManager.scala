package section2.actors

import akka.actor.Actor
import java.io.PrintStream
import java.io.BufferedReader
import java.net.Socket
import akka.actor.Props

class ChatManager extends Actor {
  import ChatManager._
  def receive = {
    case CheckAllInput =>
      for (child <- context.children) child ! Chatter.CheckInput
    case NewChatter(sock, in, out, name) =>
      if(context.children.exists(_.path.name == name)) {
        out.println("Name isn't unique. Be more creative.")
        sock.close()
      } else {
        context.actorOf(Props(new Chatter(sock, in, out, name)), name)
        out.println("> ")
      }
    case SendMessageToAll(message) =>
      for (child <- context.children) child ! Chatter.PrintMessage(message)
    case m => println("Oops in ChatManager: " + m)
  }
}

object ChatManager {
  case class NewChatter(sock: Socket, in: BufferedReader, out: PrintStream, name: String)
  case object CheckAllInput
  case class SendMessageToAll(message: String)
}