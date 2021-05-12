package com.knoldus.serializer

import akka.serialization.Serializer
import com.knoldus.models.VoteRecorded
import org.slf4j.{Logger, LoggerFactory}

// serializer
class VoteSerializer extends Serializer{

  val logger: Logger = LoggerFactory.getLogger(classOf[VoteSerializer])

  val SEPARATOR = "//"
  override def identifier: Int = 53278

  override def toBinary(o: AnyRef): Array[Byte] = o match {
    case event @ VoteRecorded(_,_,candidate) =>
      logger.info(s"Serializing $event")
      s"[$candidate]".getBytes()
    case _ =>
      throw new IllegalArgumentException("only voting events supported in this serializer")
  }

  override def fromBinary(bytes: Array[Byte], manifest: Option[Class[_]]): AnyRef = {
    val string = new String(bytes)
    val values = string.substring(1, string.length() - 1).split(SEPARATOR)
    val voterId = values(0).toInt
    val voterName = values(1)
    val candidate = values(2)

    val result = VoteRecorded(voterId,voterName,candidate)
    logger.info(s"Deserialized $string to $result")

    result
  }

  override def includeManifest: Boolean = false
}