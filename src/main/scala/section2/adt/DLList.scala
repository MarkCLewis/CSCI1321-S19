package section2.adt

import collection.mutable

class DLList[A] extends mutable.Buffer[A] {
  import DLList.Node
  private val end = new Node[A](null.asInstanceOf[A], null, null)
  end.next = end
  end.prev = end
  private var numElems = 0

  def +=(elem: A): this.type = {
    val n = new Node[A](elem, end.prev, end)
    end.prev.next = n
    end.prev = n
    numElems += 1
    this
  }
  def +=:(elem: A): this.type = ???
  def apply(n: Int): A = {
    require(n >= 0 && n < numElems)
    var rover = end.next
    for (_ <- 1 to n) rover = rover.next
    rover.data
  }
  def clear(): Unit = {
    end.next = end
    end.prev = end
    numElems = 0
  }
  def insertAll(n: Int, elems: Traversable[A]): Unit = ???
  def length: Int = numElems
  def remove(n: Int): A = {
    require(n >= 0 && n < numElems)
    numElems -= 1
    var rover = end.next
    for (_ <- 1 to n) rover = rover.next
    rover.prev.next = rover.next
    rover.next.prev = rover.prev
    rover.data
  }
  def update(n: Int, newelem: A): Unit = {
    require(n >= 0 && n < numElems)
    var rover = end.next
    for (_ <- 1 to n) rover = rover.next
    rover.data = newelem
  }

  // Members declared in scala.collection.IterableLike
  def iterator = new Iterator[A] {
    var rover = end.next
    def hasNext: Boolean = rover != end
    def next(): A = {
      val ret = rover.data
      rover = rover.next
      ret
    }
  }
}

object DLList {
  private class Node[A](var data: A, var prev: Node[A], var next: Node[A])
}