package controllers

import javax.inject._
import play.api.mvc._
import services.KafkaService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ActionController  @Inject()(cc: ControllerComponents, kafkaService: KafkaService)(implicit ec: ExecutionContext)   extends AbstractController(cc) {

  def saveAction(action: String, user: String) = Action.async  {
    Future {
      kafkaService.saveAction(action, user)
      Ok(s"Action ${action} from ${user} successfully created")
    }
  }
}
