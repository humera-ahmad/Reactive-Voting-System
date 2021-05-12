package com.knoldus.vote

import akka.actor.{ActorSystem, Props}
import com.knoldus.models.Vote
import com.typesafe.config.ConfigFactory

import scala.util.Random

object CastVote extends App {

  /*
    send command to the actor
      actor calls persist
      serializer serializes the event into bytes
      the journal writes the bytes
   */
  val cassandraActorSystem = ActorSystem("cassandraSystem", ConfigFactory.load().getConfig("customSerializerDemo"))
  val votingSystem = cassandraActorSystem.actorOf(Props[VotingSystem], "ReactiveVotingSystem")

  val candidateList = Seq( "Adam", "Damon", "Stefan", "Klaus")

  val random = new Random

  for( i <- 1 to 50) {
    votingSystem ! Vote(s"Person$i",candidateList(random.nextInt(candidateList.length)))
  }

}
