package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

import scala.concurrent.Future
/**
  * Created by kunal on 29/2/16.
  */
class ForgotPassword extends Controller {

  val forgotform = Form(
    "email" -> email
  )

  def forgotpass = Action {
    Ok(views.html.forgotpassword(forgotform))
  }

  def checkpass = Action { implicit request =>
    forgotform.bindFromRequest.fold(
      badform => BadRequest(views.html.forgotpassword(badform)),
      validform => Redirect(routes.Application.login).flashing("success"->"mail has been sent")
    )


  }
}
