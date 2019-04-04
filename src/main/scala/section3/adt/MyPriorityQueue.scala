package section3.adt

trait MyPriorityQueue[A] {
  def enqueue(a: A): Unit
  def dequeue(): A
  def peek: A
  def isEmpty: Boolean
}