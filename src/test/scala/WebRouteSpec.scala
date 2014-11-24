import archon.routes.www.WebRoute
import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest

class WebRouteSpec extends Specification with Specs2RouteTest with WebRoute {
  "Any request" should {
    "Reply with Hello, World" in {
      Get() ~> httpRoute ~> check {
        responseAs[String] mustEqual "Hello, world"
      }
    }
  }
}
