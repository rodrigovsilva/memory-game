FROM openjdk:11-jdk-slim
VOLUME /tmp
MAINTAINER Rodrigo Silva<rodrigovfsilva@gmail.com>
ADD target/*.jar app.jar

ENV JAVA_OPTS="-Xms256m -Xmx256m"
#ENV SPRING_PROFILE="stag"

ENTRYPOINT exec java $JAVA_OPTS \
 -Dspring.profiles.active=$SPRING_PROFILE \
 -jar app.jar