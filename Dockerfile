FROM openjdk:8-jdk-alpine

RUN apk update && apk upgrade

#Copying files
COPY pom.xml pom.xml
COPY README.md README.md
COPY src src
COPY target target

#Maven process
RUN apk add --no-cache curl tar bash
ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
ENTRYPOINT ["/usr/bin/mvn"]
 
#ADD target/address-0.0.1-SNAPSHOT.jar
RUN mvn -T 1C install && rm -rf target
RUN mvn clean install

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/address-0.0.1-SNAPSHOT.jar"]
