package section2.adt

import scala.reflect.ClassTag

// Erica and friends
class SortedArrayPQ[A: ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var front = 0
  private var arr = Array.fill(10)(null.asInstanceOf[A])

  def enqueue(a: A): Unit = {
    if (front == arr.length) {
      val tmp = Array.fill(arr.length * 2)(null.asInstanceOf[A])
      for (i <- 0 until arr.length) tmp(i) = arr(i)
      arr = tmp
    }
    var i = front
    while (i > 0 && higherP(arr(i - 1), a)) {
      arr(i) = arr(i - 1)
      i -= 1
    }
    arr(i) = a
    front += 1
  }

  def dequeue(): A = {
    front -= 1
    arr(front)
  }

  def isEmpty: Boolean = {
    front == 0
  }
  def peek: A = arr(front - 1)
}