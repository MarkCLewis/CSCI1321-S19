package section3.adt


import scala.reflect.ClassTag

/**
 *  higher priority returns true when the first A is higher than the second
 */
// Alex and Quinn
class SortedArrayPQ[A: ClassTag](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var front = 0
  private var arr = Array.fill(10)(null.asInstanceOf[A])

  def enqueue(o: A): Unit = {
    if (isEmpty) {
      arr(front) = o
    } else {
    	var rover = front
      while (rover > 0 && !higherPriority(o, arr(rover-1))) {
        arr(rover) = arr(rover -1)
        rover -= 1
      }
      arr(rover) = o
    }
    front += 1
  }
  def dequeue(): A = {
		front -= 1
    arr(front)
  }
  def peek: A = arr(front-1)
  def isEmpty: Boolean = front == 0
}