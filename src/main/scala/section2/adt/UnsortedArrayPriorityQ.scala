package section2.adt

import scala.reflect.ClassTag

// Sean and William
class UnsortedArrayPriorityQ [A: ClassTag](comp: (A,A) => Boolean) extends MyPriorityQueue[A] {
  private var front = 0
  private var back = 0
  private var data = Array.fill(10)(null.asInstanceOf[A])
  private var numElems = 0
  
  def dequeue(): A = {
    val index = priorityCheck(data)(comp)
    val ret = data(index)
    data(index) = data(numElems-1)
    ret
  }
  def enqueue(a: A): Unit = {
    numElems += numElems
    if ((back + 1) % data.length == front) {
      val tmp = Array.fill(data.length*2)(null.asInstanceOf[A])
      for(i <- 0 until data.length-1) tmp(i) = data((front + i) % data.length)
      front = 0
      back = data.length - 1
      data = tmp
    }
    data(back) = a
    back = (back + 1) % data.length
  }
  def isEmpty: Boolean = {
    numElems == 0
    //front==back
  }
  def peek: A = {
      //comp is comparison function when instantiated
    data(priorityCheck(data)(comp))
  }
  
  def priorityCheck[A](arr: Array[A])(comp: (A, A) => Boolean): Int = {
    var priorityIndex = 0
    for(i <- 0 until arr.length-1; j <- 0 until arr.length-1-i) {
      if(comp(arr(j), arr(priorityIndex))) {
        priorityIndex = j+1
      }
    }
    priorityIndex//returns index of lowest priority
  }
}