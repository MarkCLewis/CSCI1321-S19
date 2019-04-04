package section3.adt

class UnsortedListPriorityQueue[A](higherPriority:(A,A)=>Boolean) extends MyPriorityQueue[A] {
  import UnsortedListPriorityQueue.Node
  private var front = null
  private var back = null
  private val end = new Node[A](null.asInstanceOf[A],null,null)
  end.next = end
  end.prev = end
 
  
  def enqueue(a:A):Unit = {
    val n = new Node(a,end.prev,end)
  end.prev.next = n
  end.prev = n
    
    //if (front==null) {
  //   front = n
 //  } 
   // back.next = n
  }
  
  def dequeue():A = {
    val hpn = findHighestPriorityNode()
    hpn.prev.next = hpn.next
    hpn.next.prev = hpn.prev
    hpn.data
  }
  
  def peek: A = findHighestPriorityNode.data
  
  private def findHighestPriorityNode():Node[A] = {
    var ret = end.next
    var rover = ret.next
    while (rover != end) {
      if(higherPriority(rover.data,ret.data)) ret = rover //need to define higherpriority
          rover = rover.next
    }
    ret
  }
    
  def isEmpty:Boolean = end.next == end
}

object UnsortedListPriorityQueue {
  class Node[A](val data:A,var prev:Node[A],var next:Node[A]) 
 
}