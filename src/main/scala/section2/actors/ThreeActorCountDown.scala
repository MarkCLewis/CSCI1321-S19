package section2.actors

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Actor
import akka.actor.ActorRef

object ThreeActorCountDown extends App {
  class CountDownActor extends Actor {
    def receive = {
      case StartCounting(i, next, nextNext) =>
        println(i)
        next ! CountDown(i - 1, nextNext)
      case CountDown(i, next) =>
        if (i > 0) {
          println(i)
          next ! CountDown(i - 1, sender)
        } else {
          system.terminate()
        }
      case m => println("Unhandled message in CountDownActor: " + m)
    }
  }

  val system = ActorSystem("ThreeActorCountDown")

  val actor1 = system.actorOf(Props[CountDownActor], "Mark")
  val actor2 = system.actorOf(Props[CountDownActor], "Erica")
  val actor3 = system.actorOf(Props[CountDownActor], "Izzy")

  actor1 ! StartCounting(10, actor2, actor3)

  case class StartCounting(i: Int, next: ActorRef, nextNext: ActorRef)
  case class CountDown(i: Int, next: ActorRef)
}