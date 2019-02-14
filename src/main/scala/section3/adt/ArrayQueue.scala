package section3.adt

import scala.reflect.ClassTag

class ArrayQueue[A: ClassTag] extends MyQueue[A]{
  private var front = 0
  private var end = 0
  private var arr = Array.fill(10)(null.asInstanceOf[A])
  
  def dequeue(): A = {
    val ret = arr(front)
    front = (front + 1) % arr.length
    ret
  }
  def enqueue(a: A): Unit = {
    if((end + 1) % arr.length == front) {
      val tmp = Array.fill(arr.length*2)(null.asInstanceOf[A])
      for(i <- 0 until arr.length-1) tmp(i) = arr((i+front) % arr.length)
      front = 0
      end = arr.length-1
      arr = tmp
    }
    arr(end) = a
    end = (end + 1) % arr.length
  }
  def isEmpty: Boolean = {
    front==end
  }
  def peek: A = arr(front)
}