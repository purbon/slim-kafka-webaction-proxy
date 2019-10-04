package services

import java.time.Duration
import java.util.Properties

import akka.Done
import akka.actor.CoordinatedShutdown
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import javax.inject.Inject
import models._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.concurrent.Future

class KafkaService  @Inject() (appConfig: Configuration, cs: CoordinatedShutdown) {

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  val producer = new KafkaProducer[String, String](config);

  cs.addTask(CoordinatedShutdown.PhaseServiceUnbind, "free-the-producer") { () =>
    try {
      close();
      Future.successful(Done);
    } catch {
      case (e: Exception) => {
        Future.failed(e)
      }
    }
  }

  def saveAction(actionLabel: String, user: String): Unit = {
    val action = Action(actionLabel, user, System.currentTimeMillis())
    val actionJsonString = mapper.writeValueAsString(action)
    val record = new ProducerRecord[String, String](s"actions-${actionLabel}", actionLabel, actionJsonString)
    producer.send(record)
  }

  def close(): Unit = {
    producer.close(Duration.ofSeconds(3))
  }

  private def config: Properties = {
   val props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, appConfig.servers.mkString(","));
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "my-producer");
    props.put("max.block.ms", "2000");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    props
  }
}
