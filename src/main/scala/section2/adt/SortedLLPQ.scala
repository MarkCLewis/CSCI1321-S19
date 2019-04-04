package section2.adt

// Connor and friends
class SortedLLPQ[A](sort:(A, A) => Boolean) extends MyPriorityQueue[A] {
  //sort returns true if first A > second A, false otherwise.
  import SortedLLPQ._
  private var front:Node[A] = null
  def dequeue: A = {
    val ret = front.next.data
    front.next = front.next.next
    ret
  }
  def enqueue(a: A): Unit = {
    val n = new Node[A](a, null)
    if(sort(front.data, n.data)) {
      n.next = front
      front = n
    } else {
      var rover:Node[A] = front
      while(rover.next != null && sort(rover.next.data, n.data) ) {
        //if Rover > n
        rover = rover.next
      }
      n.next = rover.next
      rover.next = n
    }
  }
  def isEmpty: Boolean = front == null
  def peek: A = front.next.data
}
object SortedLLPQ {
  class Node[A](val data: A, var next: Node[A])
}