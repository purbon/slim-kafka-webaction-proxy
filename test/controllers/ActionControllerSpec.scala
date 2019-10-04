package controllers

import play.api.test.FakeRequest
import play.api.test.Helpers._
import utils.KafkaPlaySpec

class ActionControllerSpec  extends KafkaPlaySpec {

  "ActionController POST" should {

    "receive the ACTION post request without errors" in {
      val controller: ActionController = inject[ActionController]
      val actionRequest = controller
        .saveAction("click", "1234")
        .apply(FakeRequest(POST, "/actions/click/users/1234"))

      status(actionRequest) mustBe OK

    }
  }
}
