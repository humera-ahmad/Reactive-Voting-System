package com.knoldus.vote

import akka.actor.ActorLogging
import akka.persistence.PersistentActor
import com.knoldus.models.{Vote, VoteRecorded}

class VotingSystem extends PersistentActor with ActorLogging {
  override def persistenceId: String = "vote"
  var currentId = 0

  override def receiveCommand: Receive = {
    case Vote(voterName,candidate) =>
      persist(VoteRecorded(currentId,voterName,candidate)) { e =>
        currentId += 1
        log.info(s"Persisted: $e")
      }
  }

  override def receiveRecover: Receive = {
    case event @ VoteRecorded(id,_, _) =>
      log.info(s"Recovered: $event")
      currentId = id
  }
}