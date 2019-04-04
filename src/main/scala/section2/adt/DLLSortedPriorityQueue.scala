package section2.adt

// Morgan and friends
class DLLSortedPriorityQueue[A](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var default: A = _
  
  private class Node(val data: A, var prev: Node, var next: Node)
  private var end = new Node(default, null, null)
  end.next = end
  end.prev = end
  
  def enqueue(a: A): Unit = {
    var rover = end.next
    while(rover != end && higherP(rover.data, a)) {
      rover = rover.next
    }
    rover.next.prev = new Node(a, rover, rover.next)
    rover.next = rover.next.prev
  }
  
  def dequeue(): A = {
    val ret = end.next.data
    end.next.next.prev = end
    end.next = end.next.next
    ret
  }
  
  def peek: A = end.next.data
  
  def isEmpty: Boolean = end.next == end
}