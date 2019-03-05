package section2.mud

import akka.actor.Actor
import akka.actor.ActorRef

class Room(
  name: String,
  desc: String,
  private var items: List[Item],
  exitKeys: Array[String]) extends Actor {
  
  import Room._
  
  private var exits: Array[Option[ActorRef]] = null

  def receive = {
    case LinkExits(roomsMap) =>
      exits = exitKeys.map(keyword => roomsMap.get(keyword))
    case GetDescription =>
      sender ! Player.PrintMessage(description())
    case GetExit(dir) =>
      sender ! Player.TakeExit(getExit(dir))
    case GetItem(itemName) =>
      sender ! Player.TakeItem(getItem(itemName))
    case DropItem(item) =>
      dropItem(item)
    case m => println("Ooops in Room: " + m)
  }
  
  def description(): String = ???

  def getExit(dir: Int): Option[ActorRef] = {
    exits(dir)
  }

  def getItem(itemName: String): Option[Item] = ???

  def dropItem(item: Item): Unit = ???

}

object Room {
  case class LinkExits(roomsMap: Map[String, ActorRef])
  case object GetDescription
  case class GetExit(dir: Int)
  case class GetItem(itemName: String)
  case class DropItem(item: Item)
}