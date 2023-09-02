#FROM amazoncorretto:17-alpine3.15-jdk AS build
#WORKDIR /build
#RUN apk add maven=3.8.3-r0
#
#COPY pom.xml ./
#
## Non Cacheable steps
#COPY . .
#RUN mvn -B -e clean install -DskipTests=true
#
## Distribution Stage
#FROM amazoncorretto:17-alpine3.15-jdk AS runner
#
#WORKDIR /build
#
#RUN ls .
#COPY --from=build /build/target/*.jar ./application.jar
#
#EXPOSE 80
#
#CMD [ "java", "-jar", "application.jar"]

# Build Stage
FROM amazoncorretto:17-alpine3.15-jdk as build
WORKDIR /build
COPY . .
RUN apk add maven=3.8.3-r0


# Getting version info to build package
RUN mvn org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.name -q -DforceStdout > name
RUN mvn org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.version -q -DforceStdout > version
RUN mvn package -DskipTests
RUN mv ./target/`cat name`-`cat version`.jar ./application.jar

# Distribution Stage
FROM amazoncorretto:17-alpine3.15 as runner
WORKDIR /app
COPY --from=build /build/*.jar ./application.jar
EXPOSE 80
CMD [ "java", "-jar", "application.jar" ]