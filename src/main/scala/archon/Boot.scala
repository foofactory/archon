package archon

import akka.actor.Actor.Receive
import akka.actor.{ActorSystem, Props}
import akka.io.IO
import archon.routes.www.WebRoute
import spray.can.Http
import akka.util.Timeout
import spray.routing.{Route, HttpServiceActor}
import scala.concurrent.duration._

class MainService(route: Route) extends HttpServiceActor {
  def receive: Receive = runRoute(route)
}

object MainService extends WebRoute {
  val route = httpRoute
}

object Boot extends App {
  val system = ActorSystem("on-spray-can")
  val service = system.actorOf(Props(
    new MainService(MainService.route)))

  IO(Http)(system) ! Http.Bind(service, "0.0.0.0", port = 8080)

  //val service = system.actorOf(Props[MainService], "archon-service")
  //implicit val timeout = Timeout(5.seconds)
  //IO(Http) ! Http.Bind(service, "0.0.0.0", port = 8080)
}