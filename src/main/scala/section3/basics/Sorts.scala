package section3.basics

object Sorts extends App {
  def bubbleSortInt(arr: Array[Int]): Unit = {
    for (i <- 0 until arr.length - 1; j <- 0 until arr.length - 1 - i) {
      if (arr(j) > arr(j + 1)) {
        val tmp = arr(j)
        arr(j) = arr(j + 1)
        arr(j + 1) = tmp
      }
    }
  }

  def bubbleSortOrdered[A <% Ordered[A]](arr: Array[A]): Unit = {
    for (i <- 0 until arr.length - 1; j <- 0 until arr.length - 1 - i) {
      if (arr(j) > arr(j + 1)) {
        val tmp = arr(j)
        arr(j) = arr(j + 1)
        arr(j + 1) = tmp
      }
    }
  }

  def bubbleSort[A](arr: Array[A])(gt: (A, A) => Boolean): Unit = {
    for (i <- 0 until arr.length - 1; j <- 0 until arr.length - 1 - i) {
      if (gt(arr(j), arr(j + 1))) {
        val tmp = arr(j)
        arr(j) = arr(j + 1)
        arr(j + 1) = tmp
      }
    }
  }

  val nums = Array.fill(10)(util.Random.nextInt(100))
  println(nums.mkString(" "))
  bubbleSort(nums)(_ > _)
  println(nums.mkString(" "))

  def quicksort[A](lst: List[A])(lt: (A, A) => Boolean): List[A] = lst match {
    case Nil => Nil
    case h :: Nil => lst
    case pivot :: t =>
      val (less, greater) = t.partition(a => lt(a, pivot))
      quicksort(less)(lt) ::: (pivot :: quicksort(greater)(lt))
  }

  def quicksort[A](arr: Array[A])(lt: (A, A) => Boolean): Unit = {
    def insertionSort(start: Int, end: Int): Unit = {
      for (i <- start + 1 until end) {
        var j = i - 1
        var tmp = arr(i)
        while (j >= start && lt(tmp, arr(j))) {
          arr(j + 1) = arr(j)
          j -= 1
        }
        arr(j + 1) = tmp
      }
    }
    def helper(start: Int, end: Int): Unit = {
      if (end - start < 13) insertionSort(start, end)
      else {
        val mid = (start + end) / 2
        val p = if(lt(arr(start), arr(mid)) && lt(arr(end-1), arr(start))) start
          else if(lt(arr(mid), arr(start)) && lt(arr(end-1), arr(mid))) mid
          else end-1
        val tmp = arr(p)
        arr(p) = arr(start)
        arr(start) = tmp
        var low = start + 1
        var high = end - 1
        while (low <= high) {
          if (lt(arr(low), arr(start))) {
            low += 1
          } else {
            val tmp = arr(low)
            arr(low) = arr(high)
            arr(high) = tmp
            high -= 1
          }
        }
        val tmp2 = arr(start)
        arr(start) = arr(high)
        arr(high) = tmp2
        helper(start, high)
        helper(low, end)
      }
    }
    helper(0, arr.length)
  }
  
  val qnums = Array.fill(100)(util.Random.nextInt(1000))
  quicksort(qnums)(_ < _)
  println(qnums.mkString(" "))
}