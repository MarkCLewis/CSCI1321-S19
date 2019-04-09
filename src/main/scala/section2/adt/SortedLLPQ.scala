package section2.adt

// Connor and friends
class SortedLLPQ[A](sort:(A, A) => Boolean) extends MyPriorityQueue[A] {
  //sort returns true if first A > second A, false otherwise.
  import SortedLLPQ._
  private var front:Node[A] = null
  
  def dequeue: A = {
    val ret = front.data
    front = front.next
    ret
  }
  def enqueue(a: A): Unit = {
    if(front == null || sort(a, front.data)) {
      front = new Node[A](a, front)
    } else {
      var rover:Node[A] = front
      while(rover.next != null && sort(rover.next.data, a) ) {
        //if Rover > n
        rover = rover.next
      }
      rover.next = new Node[A](a, rover.next)
    }
  }
  def isEmpty: Boolean = front == null
  def peek: A = front.data
}
object SortedLLPQ {
  class Node[A](val data: A, var next: Node[A])
}