package section2.adt

// Ryan and Kevin
class ULLPQ[A](higherP: (A, A) => Boolean) { 
  private var default:A = _
  private class Node(val data: A, var prev: Node, var next: Node)  
  private val end = new Node(default, null, null)
  end.prev = end
  end.next = end
  
  def enqueue(elem: A):Unit = {
    val newNode = new Node(elem, end.prev, end)
    end.prev.next = newNode
    end.prev = newNode
    println(newNode.data)
  }
  
  def dequeue: A = {
    val torem = findHighestPriority
    torem.prev.next = torem.next
    torem.next.prev = torem.prev
    torem.data
  }
  def peek: A = {
    findHighestPriority.data
  }
  
  def isEmpty: Boolean = end.prev == end
  
  private def findHighestPriority(): Node = {
    var ret = end.next
    var rover = ret.next
    while(rover != end){
      if(higherP(rover.data, ret.data)) ret = rover
      rover = rover.next
    }
    ret
  }
}