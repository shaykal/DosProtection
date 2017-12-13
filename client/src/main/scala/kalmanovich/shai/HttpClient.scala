package kalmanovich.shai


import main.scala.kalmanovich.shai.MainApp
import main.scala.kalmanovich.shai.utils.PropertiesUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.slf4j.{Logger, LoggerFactory}


/**
  * Created by Shai Kalmanovich on 12/13/2017.
  */
class HttpClient(url : String, id : Int) extends Runnable {

  val log: Logger = LoggerFactory.getLogger(this.getClass)


  def sendRequest(): Unit = {
    val urlWithId = s"$url/?clientId=$id"
    getRestContent(urlWithId, 100, 100)
  }

  def getRestContent(url: String,
                     connectionTimeout: Int,
                     socketTimeout: Int): String = {

    val client : CloseableHttpClient = HttpClientBuilder.create().build()
    val request: HttpGet = new HttpGet(url)
    log.info(s"url is: $url")

    //val response : HttpResponse = client.execute(request)
/*
    val httpClient = buildHttpClient(connectionTimeout, socketTimeout)
    val httpResponse = httpClient.execute(new HttpGet(url))
    val entity = httpResponse.getEntity
    var content = ""
    if (entity != null) {
      val inputStream = entity.getContent
      content = io.Source.fromInputStream(inputStream).getLines.mkString
      inputStream.close
    }
    httpClient.getConnectionManager.shutdown
    content*/
    ???
  }

  override def run(): Unit = {
    log.info("Starting...")
    while(!MainApp.isStop) {
      sendRequest
      val randomInt = scala.util.Random
      val timeToWait = randomInt.nextInt(PropertiesUtils.maxTimeToWait)
      log.info(s"Going to sleep for $timeToWait milliseconds")
      Thread.sleep(timeToWait)
      log.info(s"woke up. isStop is: ${MainApp.isStop}")
    }
  }
}

object HttpClient {

  def apply(url: String, id: Int): HttpClient = new HttpClient(url, id)
}
