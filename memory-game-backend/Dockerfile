FROM adoptopenjdk/maven-openjdk11

MAINTAINER Rodrigo Silva<rodrigovfsilva@gmail.com>

# make the 'app' folder the current working directory
WORKDIR /app

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build the app
RUN mvn clean install -DskipTests

# rename app jar
RUN mv ./target/*jar app.jar

#define environment java opts
ENV JAVA_OPTS="-Xms256m -Xmx256m"
#ENV SPRING_PROFILE="stag"

ENTRYPOINT exec java $JAVA_OPTS \
 -jar app.jar
