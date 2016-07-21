import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener
import org.slf4j.LoggerFactory

object JettyLauncher {

  val DEFAULT_SERVICE_PORT: Int = 9000

  val LOGGER = LoggerFactory.getLogger(JettyLauncher.getClass)

  def main(args: Array[String]) {
    val servicePort: String = System.getenv("FIBONACCI_SERVICE_PORT")
    val port = if(servicePort != null) servicePort.toInt else DEFAULT_SERVICE_PORT



    val server = new Server(port)
    val context = new WebAppContext()
    context setContextPath "/"
    val resourceBase = getClass.getClassLoader.getResource("webapp").toExternalForm
    context.setResourceBase(resourceBase)
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/*")

    server.setHandler(context)

    LOGGER.info(s"Starting Jetty server on port : $port")

    server.start
    server.join
  }
}