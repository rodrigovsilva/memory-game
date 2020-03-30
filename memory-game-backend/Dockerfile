FROM adoptopenjdk/maven-openjdk11

MAINTAINER Rodrigo Silva<rodrigovfsilva@gmail.com>

# make the 'app' folder the current working directory
WORKDIR /app

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build the app
RUN mvn clean install -DskipTests

# copy app jar
COPY target/*.jar app.jar

ENV JAVA_OPTS="-Xms256m -Xmx256m"
#ENV SPRING_PROFILE="stag"

ENTRYPOINT exec java $JAVA_OPTS \
 -Dspring.profiles.active=$SPRING_PROFILE \
 -jar app.jar
