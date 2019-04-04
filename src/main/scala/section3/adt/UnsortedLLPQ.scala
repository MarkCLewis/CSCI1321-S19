package section3.adt

class UnsortedLLPQ[A](higherPriority:(A, A) => Boolean) extends MyPriorityQueue[A] {
  import UnsortedLLPQ.Node
  
  private var default: A = _
  private val end = new Node(default, null, null)
  end.next = end
  end.prev = end
  
  def enqueue(a: A): Unit = {
    val newNode = new Node(a, end.prev, end)
    end.prev.next = newNode
    end.prev = newNode
  }
  def dequeue(): A = {
    val hpn = findHighestPriorityNode()
    hpn.prev.next = hpn.next
    hpn.next.prev = hpn.prev
    hpn.data
  }
  def peek: A = findHighestPriorityNode().data
  def isEmpty: Boolean = end.next == end
  
  private def findHighestPriorityNode(): Node[A] = {
    var ret = end.next
    var rover = ret.next
    while (rover != end) {
      if(higherPriority(rover.data, ret.data)) ret = rover
      rover = rover.next
    }
    ret
  }
}

object UnsortedLLPQ {
  private class Node[A](val data: A, var prev: Node[A], var next: Node[A])
}