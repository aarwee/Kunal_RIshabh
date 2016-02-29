package models

/**
  * Created by kunal on 29/2/16.
  */
case class User(email:String,password:String)

trait UserServiceApi {

  def getUser(email:String): User
  def updateUserEmail(email:String): Boolean
  def updateUserPassword(email:String,password:String): Boolean

}

class UserService extends UserServiceApi{

  val listOfUser:List[User] = List(User("kunal@knoldus.in","kunal"),User("rishabh@knoldus.com","rishabh"))

  override def getUser(email: String): User = {

    def local(list:List[User]):User = {
      list match{
        case head::tail if head.email == email => head
        case head::tail  => local(tail)
        case Nil => User("","")
      }
    }

    local(listOfUser)
  }

  override def updateUserEmail(email: String): Boolean = {
    true
  }

  override def updateUserPassword(email:String,password:String): Boolean = {
    true
  }
}
