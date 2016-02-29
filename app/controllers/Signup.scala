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


class Signup extends Controller{

  val signupform = Form(
    tuple(
      "email" -> email,
      "password" -> text,
      "confirmpassword" -> text
    )
  )

  def show = Action{ implicit request =>
    Ok(views.html.signup(signupform))
  }

  def create = Action{ implicit request =>

    signupform.bindFromRequest.fold(
      badform => {print("2222");BadRequest(views.html.signup(badform))},
      validform => {
        if(request.getQueryString("newpassword").toString == request.getQueryString("confirmpassword").toString){

          Redirect(routes.Application.login).flashing("create"->"Account Created")
        }

        else
          Redirect(routes.Signup.show).flashing("mismatch"->"Password Mismatch")
      })
  }
}
