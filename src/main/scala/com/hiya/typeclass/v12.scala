package com.hiya.typeclass

import java.time.Instant

import io.circe.generic.auto._
import io.circe.syntax._
import io.circe.{Decoder, Encoder, Error, parser}

/**
  * Automatic derivation
  */
object v12 extends App {

  case class ProfileUpdate(
    phoneNumber: String,
    companyId: String,
    profiles: Map[String, Profile]
  )

  case class Profile(
    name: String,
    creation: Instant
  )

  def serialize[T: Encoder](obj: T): String =
    obj.asJson.spaces2

  def deserialize[T: Decoder](raw: String): Either[Error, T] =
    for {
      json <- parser.parse(raw)
      obj  <- json.as[T]
    } yield obj

  println(
    serialize[ProfileUpdate](
      ProfileUpdate(
        phoneNumber = "1/23456789",
        companyId = "Company Ltd",
        profiles = Map("default" -> Profile("Comp. Ltd", Instant.EPOCH))
      )
    )
  )

  println(
    deserialize[ProfileUpdate](
      """{
        |  "phoneNumber": "1/23456789",
        |  "companyId": "Company Ltd",
        |  "profiles": {
        |    "default": {
        |      "name": "Comp. Ltd",
        |      "creation": "1970-01-01T00:00:00Z"
        |    }
        |  }
        |}""".stripMargin
    )
  )

}
