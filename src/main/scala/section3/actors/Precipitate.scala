package section3.actors

import akka.actor.Actor

class Precipitate extends Actor {
  def receive = {
    case m => println("Unhandled message in Precipitate: " + m)
  }
}