import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import controllers.Signup

/**
  * Created by knoldus on 29/2/16.
  */
class SignupSpec extends Specification{

  "Signup" should {
    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "render the signup page" in new WithApplication{
      val home = route(FakeRequest(GET, "/signup")).get
      status(home) must equalTo(OK)
    }

    "invalid form submission" in new WithApplication() {
      val input = route(FakeRequest(GET, "/show")).get
      status(input) must equalTo(200)
    }

    "valid" in new WithApplication(){
      val input = route(FakeRequest(GET, "/changepassword")).get
      status(input) must equalTo(200)
    }
  }
}
