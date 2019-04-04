package section3.adt

import scala.reflect.ClassTag

// Matthew and Paul
class SortedArrayPriorityQueue[A: ClassTag](comp: (A,A) => Boolean) extends MyPriorityQueue[A]
{
  private var data = Array.fill(10)(null.asInstanceOf[A])
  private var front = 0
  private var end = 0
  
  def enqueue(a: A): Unit =
  {
    if((end + 1) % data.length == front)
    {
      val temp = Array.fill(data.length * 2)(null.asInstanceOf[A])
      for(i <- 0 until data.length - 1) temp(i) = data((i + front) % data.length)
      front = 0
      end = data.length - 1
      data = temp    
    }
    var i = end
    while(i != front && comp(a, data(i)))
    {
      val temp = data(i)
      data(i) = a
      data(i+1) = temp
      i -= 1
    }
  }
  
  def dequeue(): A =
  {
    val ret = data(front)
    front = (front + 1) % data.length
    ret    
  }
  
  def peek: A = data(front)
  
  def isEmpty: Boolean = front == end 
  
}