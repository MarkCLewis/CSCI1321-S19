package section3.adt

import org.junit.Assert._
import org.junit._

class TestLinkedList {
  var lst: DLList[Int] = null
  
  @Before
  def init() {
    lst = new DLList[Int]()
  }
  
  @Test
  def emptyOnCreate() {
    assertTrue(lst.isEmpty)
  }
  
  @Test
  def addSomeAndCheck() {
    lst += 2 += 3 += 4
    assertEquals(3, lst.length)
    assertEquals(2, lst(0))
    assertEquals(3, lst(1))
    assertEquals(4, lst(2))
  }
}