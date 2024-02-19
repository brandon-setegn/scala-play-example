package controllers

import play.api.mvc._

import javax.inject._
import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HelloWorldController @Inject()(
  cc: ControllerComponents
)(implicit ec: ExecutionContext) extends LoggingController(cc) {
  def index() = Action { implicit request: Request[AnyContent] =>
    Thread.sleep(1000)
    logger.info("Hello World")
    Ok("Hello World")
  }
}
