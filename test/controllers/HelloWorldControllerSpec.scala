package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.{ JsResult, Json }
import play.api.mvc.{ RequestHeader, Result }
import play.api.test._
import play.api.test.Helpers._
import play.api.test.CSRFTokenHelper._
import v1.post.PostResource

import scala.concurrent.Future

class HelloWorldControllerSpec extends PlaySpec with GuiceOneAppPerTest {
  "HelloWorldController" should {
    "render hello world" in {
      val request = FakeRequest(GET, "/").withHeaders(HOST -> "localhost:9000").withCSRFToken
      val home: Future[Result] = route(app, request).get
      val content = contentAsString(home)
      content mustBe "Hello World"
    }
  }
}