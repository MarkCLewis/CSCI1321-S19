package section2.adt

import scala.reflect.ClassTag

// Henry and friends
class UnsortedArrayPQ[A:ClassTag](higherP: (A,A) => Boolean) extends MyPriorityQueue[A]{
  private var data = new Array[A](10)
  private var front = 0
  private var back = 0
  
  def enqueue(a: A): Unit = {
    if ((back + 1) % data.length == front) {
      val tmp = Array.fill(data.length * 2)(null.asInstanceOf[A])
      for(i <- 0 until data.length - 1) tmp(i) = data((front + i) % data.length)
      front = 0
      back = data.length - 1
      data = tmp
    }
    data(back) = a
    back = (back + 1) % data.length
  }
  def dequeue(): A = ???
  def peek: A = ???
  def isEmpty: Boolean = front == back
  
  private def highestPriority():A = {
    ???
  }
  
  // pls don't run this in class I'm begging you
}