package section3.adt

// Stephan and Lewis
class SLLPriorityQueue[A](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  def enqueue(a: A): Unit = {
    if (front == null || higherP(a, front.data)) {
      front = new Node(a, front)
    } else {
      var rover = front
      while (rover.next != null && higherP(rover.next.data, a)) rover = rover.next
      rover.next = new Node(a, rover.next)
    }
  }

  def dequeue(): A = {
    val ret = front.data
    front = front.next
    ret
  }
  def peek: A = front.data

  def isEmpty: Boolean = front == null

  class Node(val data: A, var next: Node)
  private var front: Node = null

}