package section2.adt

trait MyQueue[A] {
  def enqueue(a: A): Unit
  def dequeue(): A
  def peek: A
  def isEmpty: Boolean
}