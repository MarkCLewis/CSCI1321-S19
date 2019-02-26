package section2.actors

import akka.actor.Actor
import scalafx.scene.image.WritableImage
import akka.actor.Props

class Grower(img: WritableImage) extends Actor {
  for(i <- 1 to 1000) {
    context.actorOf(Props[Minion])
  }
  for(c <- context.children) {
    c ! Minion.MoveFrom(img.width.value.toInt/2, 0)
  }
  
  import Grower._
  
  def receive = {
    case CheckMove(x, y) =>
    case m => println("Unhandled message in Grower: " + m)
  }
}

object Grower {
  case class CheckMove(x: Int, y: Int)
}