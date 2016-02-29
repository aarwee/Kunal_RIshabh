import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}


/**
  * Created by knoldus on 29/2/16.
  */
class DashboardSpec extends Specification{
  "Dashboard" should {
    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "url hit without session" in new WithApplication{
      val home = route(FakeRequest(GET, "/show")).get

      status(home) must equalTo(303)
    }

    "Log out button" in new WithApplication() {
      val input = route(FakeRequest(GET, "/logout")).get
      status(input) must equalTo(303)
    }

    "change password button" in new WithApplication(){
      val input = route(FakeRequest(GET, "/changepassword")).get
      status(input) must equalTo(200)
    }
  }
}
