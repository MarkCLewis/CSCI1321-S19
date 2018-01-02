package basics

import org.junit.Test
import org.junit.Assert._

class TestHelloWorld {
  @Test def testSquare(): Unit = {
    assertEquals(4, HelloWorld.square(2), 1e-8)
  }
}