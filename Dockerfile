FROM java:openjdk-8-jdk
MAINTAINER doanduyhai <doanduyhai@gmail.com>

ENV MAVEN_VERSION 3.3.9

ENV UPDATE_DATE 2016-07-23

RUN mkdir -p /usr/share/maven \
  && curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    | tar -xzC /usr/share/maven --strip-components=1 \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

VOLUME /root/.m2

#Install Git
RUN apt-get update && apt-get install -y git

WORKDIR /opt

#Clone the code repo
RUN git clone https://github.com/doanduyhai/FibonacciScalaDemo.git

#Compile the source code
RUN cd FibonacciScalaDemo && mvn clean package

RUN cp /opt/FibonacciScalaDemo/target/fibonacci-rest-service-1.0-jar-with-dependencies.jar /opt/FibonacciScalaDemo/

#Clean up
RUN rm -rf /opt/FibonacciScalaDemo/target/* && \
    rm -rf /root/.m2/* && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get purge -y --auto-remove && \
    rm -rf /var/lib/apt /var/lib/dpkg /var/lib/cache /var/lib/log
 
EXPOSE 9000

CMD [ "java",  "-jar", "/opt/FibonacciScalaDemo/fibonacci-rest-service-1.0-jar-with-dependencies.jar" ]
