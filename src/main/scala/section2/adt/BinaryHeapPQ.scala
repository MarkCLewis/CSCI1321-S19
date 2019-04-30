package section2.adt

import scala.reflect.ClassTag

class BinaryHeapPQ[A: ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var arr: Array[A] = Array.fill(6)(null.asInstanceOf[A])
  private var end = 1
  def enqueue(a: A): Unit = {
    if(end >= arr.length){
      val temp = Array.fill(arr.length * 2)(null.asInstanceOf[A])
      for(i <- arr.indices){
        temp(i) = arr(i)
      }
      arr = temp
    }
    var bubble = end
    while(bubble != 1 && higherP(a, arr(bubble/2))){
      arr(bubble) = arr(bubble/2)
      bubble /= 2
    }
    arr(bubble) = a
    end += 1
  }
  def dequeue(): A = {
    val ret = arr(1)
    end -= 1
    val tmp = arr(end)
    var stone = 1
    var flag = true
    while(flag && stone * 2 < end) {
      var higherChild = stone * 2
      if (stone * 2 + 1 < end && higherP(arr(stone*2+1), arr(higherChild))) higherChild += 1
      if (higherP(arr(higherChild), tmp)) {
        arr(stone) = arr(higherChild)
        stone = higherChild
      } else flag = false
    }
    arr(stone) = tmp
    ret
  }
  def peek: A = arr(1)
  def isEmpty: Boolean = end == 1
}