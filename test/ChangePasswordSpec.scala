import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import play.api.test.Helpers._
import play.api.test._

/**
  * Created by knoldus on 27/2/16.
  */

class ChangePasswordSpec extends Specification with Mockito {


  "ChangePassword" should {
//    "send 404 on a bad request" in new WithApplication{
//      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
//    }

//    "render the index page" in new WithApplication{
//      val home = route(FakeRequest(GET, "/changepassword")).get
//
//      status(home) must equalTo(OK)
//      contentType(home) must beSome.which(_ == "text/html")
//      contentAsString(home) must contain ("old")
//    }

    "invalid form submission" in new WithApplication() {
      val input = route(FakeRequest(GET, "/check").withFormUrlEncodedBody("oldpassword"->"rishabh","newpassword"->"r","confirmpassword"->"r1")).get
      status(input) must equalTo(400)
    }

    "valid" in new WithApplication(){
      val input = route(FakeRequest(GET, "/check").withFormUrlEncodedBody("oldpassword"->"rishabh","newpassword"->"r","confirmpassword"->"r")).get
      contentAsString(input) must contain ("old")
    }
  }
}