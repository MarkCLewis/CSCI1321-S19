package section3.adt


import scala.reflect.ClassTag

/**
 *  higher priority returns true when the first A is higher than the second
 */
// Alex and Quinn
class SortedArrayPQ[A: ClassTag](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var front = 0
  private var back = 0
  private var arr = Array.fill(10)(null.asInstanceOf[A])

  def enqueue(o: A): Unit = {
    var rover = front
    if (isEmpty) {
      arr(front) = o
      front += 1
    } else {
      while (!higherPriority(o, arr(rover-1))) {
        arr(rover) = arr(rover -1)
        rover -= 1
      }
      arr(rover) = o
    }
    //    arr(front) = o

  }
  def dequeue(): A = {
    val ret = arr(front)
    front += 1
    ret
  }
  def peek: A = arr(front)
  def isEmpty: Boolean = front == back
}