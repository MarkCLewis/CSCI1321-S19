package section2.adt

import org.junit.Test
import org.junit.Assert._

class TestBSTMap {
  @Test
  def emptyOnCreate = {
    val m = new BSTMap[Int, Int](_ < _)
    assertTrue(m.isEmpty)
  }
  
  @Test
  def addThreeAndGet = {
    val m = new BSTMap[String, Int](_ < _)
    m += ("one" -> 1) += ("two" -> 2) += ("three" -> 3)
    assertFalse(m.isEmpty)
    assertEquals(1, m.get("one").get)
    assertEquals(2, m.get("two").get)
    assertEquals(3, m.get("three").get)
  }

  @Test
  def addThreeAndIterate = {
    val m = new BSTMap[String, Int](_ < _)
    m += ("one" -> 1) += ("two" -> 2) += ("three" -> 3)
    assertFalse(m.isEmpty)
//    List(
//    assertEquals(1, m.get("one").get)
//    assertEquals(2, m.get("two").get)
//    assertEquals(3, m.get("three").get)
  }
}