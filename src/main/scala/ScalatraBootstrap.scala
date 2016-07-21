import javax.servlet.ServletContext

import com.doanduyhai.rest.FibonacciREST
import org.scalatra._
import org.slf4j.LoggerFactory

class ScalatraBootstrap extends LifeCycle {

  private val LOGGER = LoggerFactory.getLogger(this.getClass)

  override def init(context: ServletContext): Unit = {

    LOGGER.info("Mounting Fibonacci REST context")
    context.mount(new FibonacciREST, "/fibonacci")
  }
}
