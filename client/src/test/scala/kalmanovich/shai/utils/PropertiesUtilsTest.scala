package test.scala.kalmanovich.shai.utils

import main.scala.kalmanovich.shai.utils.PropertiesUtils
import org.scalatest.FunSuite

/**
  * Created by Shai Kalmanovich on 12/13/2017.
  */
class PropertiesUtilsTest extends FunSuite {

    test("loading properties file is successful") {
      val serverHost: String = PropertiesUtils.serverHost
      val serverPort: Int = PropertiesUtils.serverPort

      assert(serverHost == "localhost")
      assert(serverPort == 8080)
    }

}
