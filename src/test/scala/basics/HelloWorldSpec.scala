package basics

import org.scalatest._

class HelloWorldSpec extends FlatSpec with Matchers {
  "square" should "return 4 when called on 2" in {
    HelloWorld.square(2) should be (4.0)
  }
  
  it should "return 9 when called on 3" in {
    HelloWorld.square(3) should be (9.0)
  }
  
  "cube" should "return 8 when called on 2" in {
    HelloWorld.cube(2) should be (8)
  }
}