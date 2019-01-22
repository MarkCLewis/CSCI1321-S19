package basics

import org.junit.Test
import org.junit.Assert._
import section2.basics.HelloWorld

class TestHelloWorld {
  @Test def testSquare(): Unit = {
    assertEquals(4, HelloWorld.square(2), 1e-8)
  }
}