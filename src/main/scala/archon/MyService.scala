package archon

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

class MyServiceActor extends Actor with MyService {
  def actorRefFactory = context
  // could add other things here like request stream processing or timeout handling
  def receive = runRoute(appRoutes)
}

trait MyService extends HttpService {

  val appRoutes =
    get {
      path("") {
        respondWithMediaType(`text/html`) {
          complete(html.index("Hello World!").toString())
        }
      } ~
        path("test") {
          respondWithMediaType(`text/html`) {
            complete(html.index("Hello Test!").toString())
          }
        }
    }

}