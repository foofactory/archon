package archon.routes.www

import spray.routing.{Directives, Route}

/**
 * Created by eis on 11/23/14.
 */
class WebRoute extends Directives {
  val httpRoute: Route =
    get {
      complete(html.index("What are you testing?").toString())
    }
}
