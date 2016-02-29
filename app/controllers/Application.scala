package controllers

import models.{User, UserService}
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.{Iteratee, Enumerator}
import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

import scala.concurrent.{Await, Future}
import play.mvc.Http._
import scala.concurrent.duration._




class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val loginform = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply))

  def login = Action{ implicit request =>
    Ok(views.html.home(loginform))
  }

  def submit = Action{ implicit request =>
    loginform.bindFromRequest.fold(
      badform =>  { BadRequest(views.html.home(badform))},
      validform => {
        if (validate(validform)) {
          Redirect(routes.Dashboard.show).withSession("email" -> validform.email)
        }
        else
          Redirect(routes.Application.login).flashing("invaliduser" -> "Wrong Username or Password")
      })
  }



  def validate(user:User): Boolean ={
    if(new UserService().getUser(user.email)!=User("",""))
      true
    else
      false
  }

}
