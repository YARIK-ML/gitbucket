package gitbucket.core.model

trait AccountFederationComponent { self: Profile =>
  import profile.api._

  lazy val AccountFederations = TableQuery[AccountFederations]

  class AccountFederations(tag: Tag) extends Table[AccountFederation](tag, "ACCOUNT_FEDERATION") {
    val issuer = column[String]("ISSUER")
    val subject = column[String]("SUBJECT")
    val userName = column[String]("USER_NAME")
    def * = (issuer, subject, userName).mapTo[AccountFederation]

    def byPrimaryKey(issuer: String, userName: String): Rep[Boolean] =
      (this.issuer === issuer.bind) && (this.userName === userName.bind)
  }
}

case class AccountFederation(issuer: String, subject: String, userName: String)
