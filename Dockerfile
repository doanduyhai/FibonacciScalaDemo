FROM java:openjdk-8-jdk
MAINTAINER doanduyhai <doanduyhai@gmail.com>

ENV MAVEN_VERSION 3.3.9

RUN mkdir -p /usr/share/maven \
  && curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    | tar -xzC /usr/share/maven --strip-components=1 \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

VOLUME /root/.m2

RUN apt-get update && apt-get install -y git

WORKDIR /opt

RUN git clone https://github.com/doanduyhai/FibonacciScalaDemo.git

RUN  cd FibonacciScalaDemo && mvn clean package

EXPOSE 9000

CMD java -jar /opt/FibonacciScalaDemo/target/fibonacci-rest-service-1.0-jar-with-dependencies.jar
