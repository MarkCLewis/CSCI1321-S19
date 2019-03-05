package section3.mud

import akka.actor.ActorRef

class Player {
  
}

object Player {
  case object ProcessInput
  case class PrintMessage(message: String)
  case class TakeExit(optRoom: Option[ActorRef])
  case class TakeItem(optItem: Option[Item])
}