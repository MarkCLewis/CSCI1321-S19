package section2.adt

import org.junit.Test
import org.junit.Assert._

class TestArrayQueue {
  @Test def emptyOnCreate(): Unit = {
    val queue = new ArrayQueue[Int]
    assertTrue(queue.isEmpty)
  }
  
  @Test def addOneAndDequeue(): Unit = {
    val queue = new ArrayQueue[Int]
    queue.enqueue(5)
    assertFalse(queue.isEmpty)
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue())
    assertTrue(queue.isEmpty)
  }
  
  @Test def fillAndEmptyTwice(): Unit = {
    val queue = new ArrayQueue[Int]
    val nums1 = Array.fill(20)(util.Random.nextInt(100))
    for(n <- nums1) queue.enqueue(n)
    for(n <- nums1) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
    val nums2 = Array.fill(20)(util.Random.nextInt(100))
    for(n <- nums2) queue.enqueue(n)
    for(n <- nums2) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
  }
}