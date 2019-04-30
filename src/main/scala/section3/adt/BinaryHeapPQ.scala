package section3.adt

import scala.reflect.ClassTag

class BinaryHeapPQ[A:ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  
  private var arr = new Array[A](10)
  private var nextIn = 1
  
  def enqueue(a: A): Unit = {
    var bubble = nextIn
    while(bubble > 1 && higherP(a, arr(bubble / 2))) {
      arr(bubble) = arr(bubble / 2)
      bubble /= 2
    }
    arr(bubble) = a
    nextIn += 1
  }
  def dequeue(): A = {
    val ret = arr(1)
    nextIn -= 1
    val tmp = arr(nextIn)
    def helper(stone: Int): Unit = {
      if(stone * 2 >= nextIn) {
        arr(stone) = tmp
      } else {
        var higherChild = stone * 2
        if (stone * 2 + 1 < nextIn && higherP(arr(stone * 2 + 1), arr(higherChild))) {
          higherChild += 1
        }
        if (higherP(arr(higherChild), tmp)) {
          arr(stone) = arr(higherChild)
          helper(higherChild)
        } else {
          arr(stone) = tmp
        }
      }
    }
    helper(1)
    ret
  }
  def peek: A = arr(1)
  def isEmpty: Boolean = nextIn == 1
}