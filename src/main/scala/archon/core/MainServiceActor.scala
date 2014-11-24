package archon.core

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import archon.routes.www.{CookiesMatchingRoute, HeadersMatchingRoute, URLMatchingRoute}
import spray.can.Http
import spray.routing.{HttpServiceActor, Route}

class MainServiceActor(route: Route) extends HttpServiceActor {
  def receive: Receive = runRoute(route)
}

object MainService extends URLMatchingRoute with HeadersMatchingRoute with CookiesMatchingRoute {
  val route =
    urlMatchingRoute ~
    headersMatchingRoute ~
    cookiesMatchingRoute
}

object Main extends App {
  val system = ActorSystem("on-spray-can")
  val service = system.actorOf(Props(new MainServiceActor(MainService.route)))
  IO(Http)(system) ! Http.Bind(service, "0.0.0.0", port = 8080)
}