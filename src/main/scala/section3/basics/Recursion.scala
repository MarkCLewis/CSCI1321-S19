package section3.basics

object Recursion extends App {
  def fact(n: BigInt): BigInt = if (n < 2) 1 else n * fact(n-1)
  
  def fib(n: Int): Int = if (n < 2) 1 else fib(n-1) + fib(n-2)
  
  println(fact(5))
  println(fact(170))
  println(fib(5))
  println(fib(8))
  
  def knapsack(items: List[(Double, Double)], weightLeft: Double): Double = items match {
    case Nil => 0.0
    case (value, weight) :: t =>
      knapsack(t, weightLeft) max (if(weight > weightLeft) 0.0 
        else value+knapsack(t, weightLeft-weight))
  }
  
  def binPack(bins: Array[Double], objs: List[Double]): Boolean = objs match {
    case Nil => true
    case obj :: t =>
      bins.indices.exists { i =>
        obj <= bins(i) && {
          bins(i) -= obj
          val ret = binPack(bins, t)
          bins(i) += obj
          ret
        }
      }
  }
}