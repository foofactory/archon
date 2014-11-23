package archon

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.util.Timeout
import scala.concurrent.duration._

object Boot extends App {
  implicit val system = ActorSystem("on-spray-can")
  val service = system.actorOf(Props[MyServiceActor], "archon-service")
  implicit val timeout = Timeout(5.seconds)
  IO(Http) ! Http.Bind(service, "0.0.0.0", port = 8080)
}