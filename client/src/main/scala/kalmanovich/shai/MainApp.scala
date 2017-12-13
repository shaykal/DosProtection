package main.scala.kalmanovich.shai

import kalmanovich.shai.HttpClient
import main.scala.kalmanovich.shai.utils.Consts

/**
  * Created by Shai Kalmanovich on 12/13/2017.
  */
object MainApp  {

  var isStop = false

  def main(args: Array[String]): Unit = {
    println("test")

    val numOfClients = 1 // args(0).toInt

    for(i <- 1 to numOfClients) {
      val client : HttpClient = HttpClient(Consts.BASE_URL, i)
      val thread = new Thread(client)
      thread.start
    }




  }

}
