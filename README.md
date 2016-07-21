# Fibonacci Rest Service
<br/>
To specify the service port on startup, just set the **FIBONACCI_SERVICE_PORT** environment variable
otherwise the port defaults to <strong>9000</strong>
<br/>
The Fibonacci REST service endpoint is exposed at: _http://ip:port/fibonacci/:rank_ where _rank_ is
the rank of the Fibonacci suite to compute.
<br/>

> Please note that the _rank_ value starts at **1**. Providing a non-strictly positive rank will raise an error

# Source code
The main logic is implemented inside ```com.doanduyhai.service.FibonacciService::computeFibonacci(rank: Int)```.
There is an unit-test to cover most code paths for this service.
<br/>
For the REST endpoint, we use the <a href="http://scalatra.org/" target="_blank">Scalatra</a> framework. The class
```JettyLauncher``` will start a web-server and the **Scalatra** servlet is mounted by the class ```ScalatraBootstrap```
<br/>
There is no unit/integration test for the REST part because it would result in testing Scalatra itself, which
is redundant

# Building the application

 To build the application:
 
* ensure you have **[Maven]** installed (at least version 3.0)
* `git clone` this repository to your local machine
* execute `mvn clean package` to build the project, it will generate an assembly jar in the folder `./target/fibonacci-rest-service-1.0-jar-with-dependencies.jar`

# Running the application

* to start the REST service, type `java -jar /path_to_the_jar/fibonacci-rest-service-1.0-jar-with-dependencies.jar`. You can
override the default service port ( **9000** ) by setting the environment variable **FIBONACCI_SERVICE_PORT** before launching
the application
* use any REST client (curl, browser) to access the service : `http://ip:port/fibonacci/:rank`

# Docker image

 Optionally, you can get the docker image of the REST service and run it:

 * docker pull doanduyhai/fibonacci-scala-demo
 * docker run -p 9000:9000 -d doanduyhai/fibonacci-scala-demo
 
 
[Maven]: https://maven.apache.org/ 
