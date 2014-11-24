package archon.routes.www

import spray.http.HttpCookie
import spray.routing.{Directives, Route}

trait WebRoute extends Directives {
  val httpRoute: Route =
    get {
      //complete(html.index("Hello, world").toString())
      complete { "Hello, world" }
    }
}

trait URLMatchingRoute extends Directives {

  case class Color(r: Int, g: Int, b: Int) {
    require(r >= 0 && r <= 255, "Invalid color range")
    require(g >= 0 && g <= 255, "Invalid color range")
    require(b >= 0 && b <= 255, "Invalid color range")
  }

  val urlMatchingRoute: Route = {
    get {
      path("path" / IntNumber) { id =>
          complete {
            html.index(s"path id $id").toString()
          }
      }
    } ~
    path("path") {
      parameter('id.as[Int]) { id =>
        complete {
          html.index(s"path Q id $id").toString()
        }}
    } ~
    path("color") {
      parameters(('r.as[Int], 'g.as[Int], 'b.as[Int])).as(Color)
      { color =>
        import color._

        complete {
          html.index(r + " " + g + " " + b).toString()
        }
      }
    }
  }
}

trait HeadersMatchingRoute extends Directives {
  val headersMatchingRoute = get {
    path("browser") {
      headerValueByName("User-Agent") { userAgent =>
        complete {
          html.index(s"Client is $userAgent").toString()
        }
      }
    }
  }
}

trait CookiesMatchingRoute extends Directives {
  val cookiesMatchingRoute = get {
    path("cookie") {
      cookie("spray") { spray =>
        complete {
          s"Cookie value is $spray"
        }
      }
    }
  } ~
  post {
    path("cookie") {
      setCookie(HttpCookie("spray","SGVsbG8sIHdvcmxkCg==", httpOnly = true)) {
        complete {
          "Cookie created"
        }
      }
    }
  }
}