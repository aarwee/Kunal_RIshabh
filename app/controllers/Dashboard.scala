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
class Dashboard extends Controller{

  def show = Action{ implicit request =>
    request.session.get("email").map{
      user => {Ok(views.html.dashboard(""))}
    }.getOrElse{
      Redirect(routes.Application.login)
    }}


  def logout =Action{
    Redirect(routes.Application.login).withNewSession
  }
}
