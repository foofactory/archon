package archon.routes.www

import spray.http.HttpCookie
import spray.routing.{RequestContext, Directives, Route}

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
      path("customer" / IntNumber) { id =>
          complete {
            html.index(s"Customer id $id").toString()
          }
      }
    } ~
    path("customer") {
      parameter('id.as[Int]) { id =>
        complete {
          s"Customer Q id $id"
        }}
    } ~
    path("color") {
      parameters(('r.as[Int], 'g.as[Int], 'b.as[Int])).as(Color)
      { color =>
        import color._

        complete {
          <html>
            <body>
              <p>{r}</p>
              <p>{g}</p>
              <p>{b}</p>
            </body>
          </html>
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
          s"Client is $userAgent"
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