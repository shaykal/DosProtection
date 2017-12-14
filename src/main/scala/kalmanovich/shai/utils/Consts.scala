package kalmanovich.shai.utils

/**
  * Created by Shai Kalmanovich on 12/13/2017.
  */
object Consts {

  val SERVER_HOST = "server.host"
  val SERVER_PORT = "server.port"
  val MAX_TIME_TO_WAIT = "max.time.to.wait"

  lazy val BASE_URL = s"http://${PropertiesUtils.serverHost}:${PropertiesUtils.serverPort}/DosServer"

  val EMPTY_STRING = ""

}
