package archon.routes.www

import spray.routing.{Directives, Route}

trait WebRoute extends Directives {
  val httpRoute: Route =
    get {
      //complete(html.index("What are you testing?").toString())
      complete {
        "Hello, world"
      }
    }
}
