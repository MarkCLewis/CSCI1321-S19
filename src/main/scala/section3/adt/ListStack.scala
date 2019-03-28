package section3.adt

class ListStack[A] extends MyStack[A] {
  import ListStack.Node
  private var top: Node[A] = null
  
  def push(a: A): Unit = top = new Node(a, top)
  
  def pop(): A = {
    val ret = top.data
    top = top.next
    ret
  }
  
  def peek: A = top.data
  
  def isEmpty: Boolean = top == null
}

object ListStack {
  private case class Node[A](data: A, next: Node[A])
}