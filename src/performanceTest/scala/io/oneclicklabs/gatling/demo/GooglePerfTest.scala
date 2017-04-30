package io.oneclicklabs.gatling.demo

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GooglePerfTest extends Simulation {

  val httpConf = http
    .baseURL("http://www.google.com")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("REST-PERF-V2")
    .exec(http("rest-request-v2")
    .get("/")
    .check(status.is(200), responseTimeInMillis.lessThan(15000))
    )
  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
    .assertions(global.responseTime.max.lessThan(20000))
}