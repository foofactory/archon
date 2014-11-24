package archon

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import archon.routes.www.WebRoute
import spray.can.Http
import spray.routing.{Route, HttpServiceActor}

class MainService(route: Route) extends HttpServiceActor {
  def receive: Receive = runRoute(route)
}

object MainService extends WebRoute {
  val route = httpRoute
}

object Boot extends App {
  val system = ActorSystem("on-spray-can")
  val service = system.actorOf(Props(new MainService(MainService.route)))
  IO(Http)(system) ! Http.Bind(service, "0.0.0.0", port = 8080)
}