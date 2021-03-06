package com.doanduyhai.rest

import com.doanduyhai.service.FibonacciService
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.{InternalServerError, BadRequest, ScalatraServlet}
import org.scalatra.json.JacksonJsonSupport

class FibonacciREST extends ScalatraServlet with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/:rank") {
    val rank = params("rank").toInt
    try {
      FibonacciService.computeFibonacci(rank)
    } catch {
      case ex: IllegalArgumentException => BadRequest(ex.getMessage)
      case throwable: Throwable => InternalServerError(throwable.getMessage)
    }
  }


  notFound {
    contentType = "text/html"
    serveStaticResource() getOrElse resourceNotFound()
  }
}
