import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}
/**
  * Created by knoldus on 29/2/16.
  */
class ForgotPasswordSpec extends Specification{
  "ForgotPassword" should {
    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "render the forgot password page" in new WithApplication{
      val home = route(FakeRequest(GET, "/forgotpassword")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("email")
    }

    "invalid email" in new WithApplication() {
      val input = route(FakeRequest(POST, "/checkpass").withFormUrlEncodedBody("email"->"abcd")).get
      status(input) must equalTo(400)
    }

    "valid email" in new WithApplication(){
      val input = route(FakeRequest(POST, "/checkpass").withFormUrlEncodedBody("email"->"k@k.com")).get
      status(input) must equalTo(303)
    }
  }
}
