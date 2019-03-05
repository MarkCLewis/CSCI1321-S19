package section3.mud

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props

class RoomManager extends Actor {
  
	val rooms = readRooms()
	for(room <- context.children) room ! Room.LinkExits(rooms)

  def receive = {
    case m => println("RoomManager got an unhandled message: " + m)
  }

  def readRooms(): Map[String, ActorRef] = {
    val source = scala.io.Source.fromFile("mapSection3.txt")
    val lines = source.getLines()
    Array.fill(lines.next.trim.toInt)(readRoom(lines)).toMap
  }

  def readRoom(lines: Iterator[String]): (String, ActorRef) = {
    val keyword = lines.next
    val name = lines.next
    val desc = lines.next
    val items = List.fill(lines.next.trim.toInt) {
      Item(lines.next, lines.next)
    }
    val exits = lines.next.split(",").map(_.trim)
    val child = context.actorOf(Props(new Room(name, desc, items, exits)), keyword)
    keyword -> child
  }

}

object RoomManager {
  
}