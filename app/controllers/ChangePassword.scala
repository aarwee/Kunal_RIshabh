package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.{Iteratee, Enumerator}
import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

import scala.concurrent.Future
/**
  * Created by kunal on 29/2/16.
  */
class ChangePassword extends Controller{

  val changepasswordform = Form(
    tuple(
      "oldpassword" -> nonEmptyText,
      "newpassword" -> nonEmptyText,
      "confirmpassword" -> nonEmptyText
    )
  )

  def check = Action{ implicit request =>

    changepasswordform.bindFromRequest.fold(
      badform => {print("2");BadRequest(views.html.changepassword(badform))},
      validform => {
          if(request.getQueryString("newpassword").toString == request.getQueryString("confirmpassword").toString){

            Redirect(routes.Dashboard.show).flashing("password"->"Password Changed")
          }

        else
            Redirect(routes.ChangePassword.changepassword).flashing("password"->"Password Mismatch")
      })
  }


  def changepassword = Action{ implicit request =>
    Ok(views.html.changepassword(changepasswordform))
  }
}
