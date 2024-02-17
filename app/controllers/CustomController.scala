package controllers

import play.api.Logger
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

abstract class CustomController(
  cc: ControllerComponents
)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  private val logger = Logger(this.getClass)

  override def Action: ActionBuilder[Request, AnyContent] = new ActionBuilder[Request, AnyContent] {
    override def parser: BodyParser[AnyContent] = cc.parsers.defaultBodyParser
    override protected def executionContext: ExecutionContext = cc.executionContext

    override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = {
      time(request) {
        block(request)
      }
    }

    private[this] def time[A, R](request: Request[A])(block: => R): R = {
      logger.info(s"Request: $request")
      val t0 = System.currentTimeMillis
      try {
        block
      } finally {
        val t1 = System.currentTimeMillis
        logger.info(s"Request: $request; took ${t1 - t0}")
      }
    }
  }
}