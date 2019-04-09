package section2.adt

import scala.reflect.ClassTag

// Sean and William
class UnsortedArrayPriorityQ [A: ClassTag](comp: (A,A) => Boolean) extends MyPriorityQueue[A] {
  private var front = 0
  private var data = Array.fill(10)(null.asInstanceOf[A])
  
  def dequeue(): A = {
    val index = priorityCheck(data)(comp)
    val ret = data(index)
    data(index) = data(front-1)
    front -= 1
    ret
  }
  def enqueue(a: A): Unit = {
    if (data.length == front) {
      val tmp = Array.fill(data.length*2)(null.asInstanceOf[A])
      for(i <- 0 until data.length) tmp(i) = data(i)
      data = tmp
    }
    data(front) = a
    front += 1
  }
  def isEmpty: Boolean = {
    front == 0
  }
  def peek: A = {
      //comp is comparison function when instantiated
    data(priorityCheck(data)(comp))
  }
  
  def priorityCheck[A](arr: Array[A])(comp: (A, A) => Boolean): Int = {
    var priorityIndex = 0
    for(i <- 1 until front) {
      if(comp(arr(i), arr(priorityIndex))) {
        priorityIndex = i
      }
    }
    priorityIndex//returns index of lowest priority
  }
}