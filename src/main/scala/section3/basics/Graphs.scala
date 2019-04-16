package section3.basics

object Graphs extends App {
  val graph = Array(
    Array(0, 1, 1, 0, 0),
    Array(0, 0, 1, 0, 0),
    Array(1, 0, 0, 0, 1),
    Array(1, 0, 1, 0, 0),
    Array(0, 0, 0, 0, 0))

  def reachable(node1: Int, node2: Int, connect: Array[Array[Int]], visited: Set[Int] = Set.empty): Boolean = {
    if (node1 == node2) true else {
      var ret = false
      var i = 0
      val newVisited = visited + node1
      while (!ret && i < connect.length) {
        if (connect(node1)(i) != 0 && !visited(i)) ret |= reachable(i, node2, connect, newVisited)
        i += 1
      }
      ret
    }
  }

  println(reachable(0, 4, graph))
  println(reachable(0, 3, graph))

  def shortestPath(node1: Int, node2: Int, connect: Array[Array[Int]], visited: Set[Int] = Set.empty): Int = {
    if (node1 == node2) 0 else {
      var ret = 1000000000
      var i = 0
      val newVisited = visited + node1
      while (i < connect.length) {
        if (connect(node1)(i) != 0 && !visited(i)) ret = ret min shortestPath(i, node2, connect, newVisited)
        i += 1
      }
      ret + 1
    }
  }
  
  println(shortestPath(0, 4, graph))
  println(shortestPath(0, 3, graph))  
  println(shortestPath(0, 1, graph))  
}