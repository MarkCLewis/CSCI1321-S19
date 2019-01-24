package section3.mud

class Room(
    val name: String,
    val desc: String,
    private var items: List[Item],
    private val exits: Array[Int]) {
  def description(): String = ???
  
  def getExit(dir: Int): Option[Room] = {
    if(exits(dir) == -1) None else Some(Room.rooms(exits(dir)))
  }
  
  def getItem(itemName: String): Option[Item] = ???
  
  def dropItem(item: Item): Unit = ???

}

object Room {
  val rooms = readRooms()
  
  def readRooms(): Array[Room] = {
    val source = scala.io.Source.fromFile("mapSection3.txt")
    val lines = source.getLines()
    Array.fill(lines.next.trim.toInt)(readRoom(lines))
  }
  
  def readRoom(lines: Iterator[String]): Room = {
    val name = lines.next
    val desc = lines.next
    val items = List.fill(lines.next.trim.toInt) {
      Item(lines.next, lines.next)
    }
    val exits = lines.next.split(",").map(_.trim.toInt)
    new Room(name, desc, items, exits)
  }
}