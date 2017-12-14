package kalmanovich.shai

import kalmanovich.shai.utils.Consts
import org.slf4j.{Logger, LoggerFactory}

/**
  * Created by Shai Kalmanovich on 12/13/2017.
  */
object MainApp  {

  val log: Logger = LoggerFactory.getLogger(this.getClass)

  var isStop = false

  def main(args: Array[String]): Unit = {
    log.info("start")
    if(args.length == 0){
      log.info("Please use the first parameter as the number of clients")
      return
    }

    val numOfClients = args(0).toInt

    for(i <- 1 to numOfClients) {
      val client : HttpClient = HttpClient(Consts.BASE_URL, i)
      val thread = new Thread(client)
      thread.start
    }

    log.info("before key stroke")
    System.in.read()
    isStop = true
    log.info("finish")
  }
}