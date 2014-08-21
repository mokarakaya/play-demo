
package controllers

import play.api._
import play.api.mvc._

object HelloWorldScala extends Controller {

  def index = Action {
    Ok("Hello World Scala")
  }

}