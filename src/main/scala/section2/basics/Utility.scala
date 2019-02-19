package section2.basics

import section2.adt.ArrayQueue
import collection.mutable

object Utility extends App {
  def findAndRemove[A](lst: List[A])(pred: A => Boolean): (Option[A], List[A]) = {
    lst match {
      case Nil => (None, lst)
      case h :: t =>
        if (pred(h)) (Some(h), t) else {
          val (found, fixedList) = findAndRemove(t)(pred)
          (found, h :: fixedList)
        }
    }
  }
  
  val nums = List(3,7,5,8,2,4,9)
  val (found, others) = findAndRemove(nums)(_%2 == 0)
  println(found, others)
  
  val maze = Array(
      Array( 0,-1, 0, 0, 0,-1, 0,-1, 0, 0),
      Array( 0,-1, 0,-1, 0,-1, 0, 0, 0, 0),
      Array( 0,-1, 0,-1, 0, 0, 0, 0,-1, 0),
      Array( 0,-1, 0,-1,-1,-1,-1, 0,-1, 0),
      Array( 0, 0, 0, 0, 0,-1, 0, 0,-1, 0),
      Array( 0,-1,-1,-1,-1,-1, 0,-1,-1, 0),
      Array( 0,-1, 0, 0, 0, 0, 0,-1, 0, 0),
      Array( 0,-1, 0, 0,-1,-1, 0,-1, 0,-1),
      Array( 0,-1,-1, 0,-1, 0, 0,-1, 0, 0),
      Array( 0, 0, 0, 0,-1, 0, 0,-1, 0, 0))
  
  val offsets = List((0, -1), (0, 1), (-1, 0), (1, 0))
      
  def shortestPath(sx: Int, sy: Int, ex: Int, ey: Int, maze: Array[Array[Int]]): Int = {
    val q = new ArrayQueue[(Int, Int, Int)]()
    q.enqueue(sx, sy, 0)
    val visited = mutable.Set[(Int, Int)](sx -> sy)
    while (!q.isEmpty) {
      val (x, y, steps) = q.dequeue()
      for((dx, dy) <- offsets) {
        val nx = x + dx
        val ny = y + dy
        if (nx == ex && ny == ey) return steps+1
        if (nx >= 0 && nx < maze.length && ny >= 0 && ny < maze(nx).length &&
            maze(nx)(ny) == 0 && !visited(nx -> ny)) {
          q.enqueue(nx, ny, steps + 1)
          visited += nx -> ny
        }
      }
    }
    100000000
  }
  
  println(shortestPath(0, 0, 9, 9, maze))
}