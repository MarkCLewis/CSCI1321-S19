package section3.actors

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

object ThreeActorCountDown extends App {
  class CountingActor extends Actor {
    def receive = {
      case StartCounting(i, next, nextNext) =>
        println(i)
        next ! CountDown(i-1, nextNext)
      case CountDown(i, next) =>
        if(i > 0) {
          println(i)
          next ! CountDown(i-1, sender)
        } else {
          system.terminate()
        }
      case m => println("Unhandled message in CountingActor: " + m)
    }
  }
  
  val system = ActorSystem("ThreeActorCountdown")
  
  val actor1 = system.actorOf(Props[CountingActor], "Mark")
  val actor2 = system.actorOf(Props[CountingActor], "David")
  val actor3 = system.actorOf(Props[CountingActor], "Sara")
  
  actor1 ! StartCounting(10, actor2, actor3)
  
  case class StartCounting(i: Int, next: ActorRef, nextNext: ActorRef)
  case class CountDown(i: Int, next: ActorRef)
}