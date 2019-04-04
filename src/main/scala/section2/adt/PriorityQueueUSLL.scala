package section2.adt

// Steven and friends
class PriorityQueueUSLL[A](f: (A,A) => Boolean) extends MyPriorityQueue[A] {
  import PriorityQueueUSLL.Node
  private var hd: Node[A] = null
  private var tl: Node[A] = null
  private var numElems = 0
  def enqueue(elem: A): Unit = {
    tl.next = new Node[A](elem, null)
    tl = tl.next
    numElems+=1
  }
  def dequeue(): A = {
    var rover = hd
    var largePriority = rover
    var largeIndex = 0
    if(hd == null) null.asInstanceOf[A]
    for(x <- 0 until numElems){
      if(getPriority(rover.data, largePriority.data)){
        largePriority = rover
        largeIndex = x
      }
      rover = rover.next
    }
    var nRover = hd
    for(y <- 0 until largeIndex){
      if(y == largeIndex-1){
        nRover.next = largePriority.next
        largePriority.next = null
      }
      nRover = nRover.next
    }
    numElems-=1

    largePriority.data
  }
  def peek: A = {
    var rover = hd
    var largePriority = rover
    var largeIndex = 0
    if(hd == null) null.asInstanceOf[A]
    for(x <- 0 until numElems){
      if(getPriority(rover.data, largePriority.data)){
        largePriority = rover
        largeIndex = x
      }
      rover = rover.next
    }
    largePriority.data
  }
  def isEmpty: Boolean = hd == null
  def getPriority(elem1: A, elem2: A): Boolean = {
    f(elem1, elem2)
  }
}
object PriorityQueueUSLL {
  private class Node[A](var data: A, var next: Node[A])

}