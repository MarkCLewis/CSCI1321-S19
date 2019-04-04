package section2.adt

import org.junit._
import org.junit.Assert._

class TestPriorityQueue {
  var pq: MyPriorityQueue[Int] = null

  @Before
  def init() = {
    pq = new SortedArrayPQ[Int](_ < _)
  }

  @Test
  def emptyOnCreate = {
    assertTrue(pq.isEmpty)
  }

  @Test
  def addRemoveOne = {
    pq.enqueue(5)
    assertFalse(pq.isEmpty)
    assertEquals(5, pq.peek)
    assertEquals(5, pq.dequeue)
    assertTrue(pq.isEmpty)
  }

  @Test
  def addRemoveMany = {
    val nums = Array.fill(100)(util.Random.nextInt)
    for (n <- nums) pq.enqueue(n)
    for (n <- nums.sorted) {
      assertEquals(n, pq.peek)
      assertEquals(n, pq.dequeue)
    }
  }
  
  @Test
  def fillEmptyRefillReempty = {
    val nums = Array.fill(100)(util.Random.nextInt)
    for (n <- nums) pq.enqueue(n)
    for (n <- nums.sorted) {
      assertEquals(n, pq.peek)
      assertEquals(n, pq.dequeue)
    }
    val nums2 = Array.fill(100)(util.Random.nextInt)
    for (n <- nums2) pq.enqueue(n)
    for (n <- nums2.sorted) {
      assertEquals(n, pq.peek)
      assertEquals(n, pq.dequeue)
    }
  }
}