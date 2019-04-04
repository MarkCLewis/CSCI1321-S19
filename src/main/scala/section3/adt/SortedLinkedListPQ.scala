package section3.adt

// Jacob and Davis
class SortedLinkedListPQ[A](comparitor: (A, A) => Boolean) extends MyPriorityQueue[A] {
 import SortedLinkedList._
  private var front: Node[A] = null
  
  def enqueue(a: A): Unit = {
    val n = new Node(a, null)
    if(front == null){
      front = n
    }else{
      if (comparitor(a, front.data)) {
        n.next = front
        front = n
      } else {
        var rover = front 
        while(!comparitor(a, rover.next.data) && rover.next != null) rover = rover.next
        n.next = rover.next
        rover.next = n  
      }
    }
    
  }
  def dequeue(): A = {
    val ret = front.data
    front = front.next
    ret
  }
  def peek: A = front.data
  def isEmpty: Boolean = front == null
}
object SortedLinkedList {
  class Node[A](val data: A, var next: Node[A]) 
}

