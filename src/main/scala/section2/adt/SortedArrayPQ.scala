package section2.adt

import scala.reflect.ClassTag

// Erica and friends
class SortedArrayPQ[A:ClassTag](f: (A,A) => Boolean) extends MyPriorityQueue[A] {
  private var front = 0
  private var end = 0
  private var arr = Array.fill(10)(null.asInstanceOf[A])
  
  
  def enqueue(a: A): Unit = {
    while(f(a,arr(front))){
        if(front==arr.length){
          val tmp=Array.fill(arr.length*2)(null.asInstanceOf[A])
          for(i<-0 until arr.length-1) tmp(i)=arr((front+i)%arr.length)
        }
          front+=1
          arr(front)=a
      }
  }

  def dequeue(): A = {
    val ret = arr(front)
    front = (front + 1) % arr.length
    ret
  }

  def isEmpty: Boolean = {
    front == end
  }
  def peek: A = arr(front)
}