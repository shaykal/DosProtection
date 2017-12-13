package kalmanovich.shai


import main.scala.kalmanovich.shai.MainApp
import main.scala.kalmanovich.shai.utils.PropertiesUtils
import org.apache.http.HttpResponse
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpGet
import org.apache.http.conn.ssl.DefaultHostnameVerifier
import org.apache.http.impl.NoConnectionReuseStrategy
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.apache.http.impl.conn.{DefaultSchemePortResolver, PoolingHttpClientConnectionManager}
import org.slf4j.{Logger, LoggerFactory}


/**
  * Created by Shai Kalmanovich on 12/13/2017.
  */
class HttpClient(url : String, id : Int) extends Runnable {

  val log: Logger = LoggerFactory.getLogger(this.getClass)


  def sendRequest(): Unit = {
    val urlWithId = s"$url?clientId=$id"
    getRestContent(urlWithId, 10, 10)
  }

  def getRestContent(url: String,
                     connectionTimeout: Int,
                     socketTimeout: Int): Unit = {

   // val client : CloseableHttpClient = HttpClientBuilder.create().build()
    //val request: HttpGet = new HttpGet(url)
    log.info(s"url is: $url")

 //   val response : HttpResponse = client.execute(request)

    val poolingHttpClientConnectionManager : PoolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
    poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);
    poolingHttpClientConnectionManager.setMaxTotal(200);

    val cb : HttpClientBuilder = HttpClientBuilder.create()
      .disableAutomaticRetries()
      .setConnectionManager(poolingHttpClientConnectionManager)
      .setSSLHostnameVerifier(new DefaultHostnameVerifier())
      .setConnectionReuseStrategy(new NoConnectionReuseStrategy())
      .setSchemePortResolver(DefaultSchemePortResolver.INSTANCE)
      .setDefaultRequestConfig(
        RequestConfig.custom()
          .setConnectTimeout(connectionTimeout)
          .setSocketTimeout(socketTimeout).build());

    def get(url: String,
            connectTimeout: Int = 5000,
            readTimeout: Int = 5000,
            requestMethod: String = "GET") =
    {
      import java.net.{URL, HttpURLConnection}
      val connection = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
      connection.setConnectTimeout(connectTimeout)
      connection.setReadTimeout(readTimeout)
      connection.setRequestMethod(requestMethod)
      val inputStream = connection.getInputStream
      val content = io.Source.fromInputStream(inputStream).mkString
      if (inputStream != null) inputStream.close
      content
    }
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
    content
*/
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
