package section2.actors

import akka.actor.Actor

class Minion extends Actor {
  import Minion._
  
  def receive = {
    case MoveFrom(x, y) =>
      ???
    case m => println("Unhandled message in Minion: " + m)
  }
}

object Minion {
  case class MoveFrom(x: Int, y: Int)
}