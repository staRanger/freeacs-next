package util
import scala.concurrent.{ExecutionContext, Future}

object MonadTransformers {
  implicit class EitherFutureTransformer[T](either: Either[String, Future[T]]) {
    def mapToFutureEither(implicit ec: ExecutionContext): Future[Either[String, T]] =
      either match {
        case Left(s)  => Future.successful(Left(s))
        case Right(f) => f.map(Right(_))
      }
  }

  implicit class OptionFutureTransformer[T](option: Option[Future[T]]) {
    def mapToFutureEither(left: String)(implicit ec: ExecutionContext): Future[Either[String, T]] =
      option match {
        case None    => Future.successful(Left(left))
        case Some(f) => f.map(Right(_))
      }
  }

  implicit class OptionTransformer[T](option: Option[T]) {
    def mapToEither(left: String): Either[String, T] =
      option match {
        case None        => Left(left)
        case Some(value) => Right(value)
      }
  }

}
