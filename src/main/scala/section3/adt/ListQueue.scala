package section3.adt

class ListQueue[A] extends MyQueue[A] {
  import ListQueue.Node
  private var front: Node[A] = null
  private var back: Node[A] = null 
  
  def enqueue(a: A): Unit = {
    val n = new Node[A](a, null)
    if(back == null) {
      front = n
    } else {
      back.next = n
    }
    back = n
  }
  def dequeue(): A = {
    val ret = front.data
    front = front.next
    if (front == null) back = null
    ret
  }
  def peek: A = front.data
  def isEmpty: Boolean = front == null
}

object ListQueue {
  private class Node[A](val data: A, var next: Node[A])
}