package main.scala.kalmanovich.shai

import java.io.DataInputStream
import java.util.Scanner

import kalmanovich.shai.HttpClient
import main.scala.kalmanovich.shai.utils.Consts
import org.slf4j.{Logger, LoggerFactory}

/**
  * Created by Shai Kalmanovich on 12/13/2017.
  */
object MainApp  {

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  var isStop = false

  def main(args: Array[String]): Unit = {
    log.info("test")

    val numOfClients = args(0).toInt

    for(i <- 1 to numOfClients) {
      val client : HttpClient = HttpClient(Consts.BASE_URL, i)
      val thread = new Thread(client)
      thread.start
    }


    log.info("before key stroke")
    scala.io.StdIn.readLine() // TODO fix for any key
    //val scanner : Scanner = new java.util.Scanner(System.in)
    isStop = true
    log.info("finish")
  }

}
