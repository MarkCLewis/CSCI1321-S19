package section2.basics

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
}