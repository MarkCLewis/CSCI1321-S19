package section3.adt

import org.junit.Assert._
import org.junit.Test

class TestArrayQueue {
  @Test def emptyOnCreate() = {
    val queue = new ArrayQueue[Int]    
    assertTrue(queue.isEmpty) 
  }
  @Test def addOneToQueue() = {
    val queue = new ArrayQueue[Int]
    queue.enqueue(7)
    assertFalse(queue.isEmpty)
    assertEquals(7, queue.peek)
    assertEquals(7, queue.dequeue())
    assertTrue(queue.isEmpty)
  }
  @Test def addRemoveAdd() = {
    val queue = new ArrayQueue[Int]
    val nums1 = Array(1,2,3)
    for(n <- nums1) queue.enqueue(n)
    for(n <- nums1) assertEquals(n, queue.dequeue())
    assertTrue(queue.isEmpty)
    val nums2 = Array(4,5,6,7,8,9,10,11,12,13,14,15,1,6,17,86,4,7,4)
    for(n <- nums2) queue.enqueue(n)
    for(n <- nums2) assertEquals(n, queue.dequeue())
    assertTrue(queue.isEmpty)
  }
}