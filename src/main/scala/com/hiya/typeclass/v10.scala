package com.hiya.typeclass

import java.time.Instant

import io.circe.{Decoder, Encoder, Error}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax._
import io.circe.parser

/**
  * Type classes for JSON serialization with Circe
  */
object v10 extends App {

  case class ProfileUpdate(
    phoneNumber: String,
    companyId: String,
    profiles: Map[String, Profile]
  )

  object ProfileUpdate {
    implicit val profileUpdateEncoder: Encoder[ProfileUpdate] = deriveEncoder[ProfileUpdate]
    implicit val profileUpdateDecoder: Decoder[ProfileUpdate] = deriveDecoder[ProfileUpdate]
  }

  case class Profile(
    name: String,
    creation: Instant
  )

  object Profile {
    implicit val profileEncoder: Encoder[Profile] = deriveEncoder[Profile]
    implicit val profileDecoder: Decoder[Profile] = deriveDecoder[Profile]
  }

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
