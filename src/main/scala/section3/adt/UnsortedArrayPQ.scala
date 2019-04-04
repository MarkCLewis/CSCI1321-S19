package section3.adt

import scala.reflect.ClassTag

// Aiden and friends
//Unsorted Array
class UnsortedArrayPQ[A: ClassTag]( higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A]{
  private var data = Array.fill(10)(null.asInstanceOf[A])
  private var front = 0 
  private var back = 0
  def enqueue(a: A): Unit = {
    if((back+1)%data.length == front) {
      val temp = Array.fill(data.length*2)(null.asInstanceOf[A])
      for(i <- 0 until data.length-1) {
        temp(i) = data((i+front)%data.length)
      }
      front = 0
      back = data.length-1
      data = temp
    }
    data(back) = a
    back = (back+1)%data.length
  }
  def dequeue: A = {
    val n = peek
    if(n == front) front = (front+1)%data.length
    else if(n == back) back = back-1
    else {
      for(i <- data.indexOf(n) until data.length) {
        data(i) = data(i+1)
      }
      back = back-1
    }
    n
  }
  def peek: A = {
    var maxPriority: A = data(0)
    for(i <- 1 until data.length) {
      if(higherPriority(maxPriority, data(i))) maxPriority = data(i)
    }
    maxPriority
  }
  def isEmpty: Boolean = front == back
}