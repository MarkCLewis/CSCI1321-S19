package section2.adt

class ListQueue[A] extends MyQueue[A] {
  import ListQueue.Node
  private var front: Node[A] = null
  private var back: Node[A] = null
  
  def enqueue(a: A): Unit = {
    val n = new Node[A](a, null)
    back.next = n
    back = n
    if(front == null) front = back
  }
  def dequeue(): A = {
    val ret = front.data
    front = front.next
    ret
  }
  def peek: A = front.data
  def isEmpty: Boolean = front == null
}

object ListQueue {
  class Node[A](val data: A, var next: Node[A])
}