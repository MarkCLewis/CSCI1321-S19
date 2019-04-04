package section2.adt

import scala.reflect.ClassTag

// Jimmy and friends
class PriorityQueueWithSortedArray[A: ClassTag](p: (A,A) => Boolean) extends MyPriorityQueue[A]{
  private var data = Array.fill(10)(null.asInstanceOf[A])
  private var back = data.length-1
  private var look = data.length-1
  def enqueue(a: A): Unit = {
    //DOES NOT WORK
    val tmp = Array.fill(data.length * 2)(null.asInstanceOf[A])
    while(!(p(a,data(look))) && look >= 0) {
      data(look+1)=data(look)
      look -= 1
    }
    data(look) = a
    look = data.length
    back += 1
  }
  def dequeue(): A ={
    back -= 1
    data(back)
  }
  def peek: A = {
    data(back)
  }
  def isEmpty: Boolean = {
    back == 0
  }

}