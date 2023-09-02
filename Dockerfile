FROM amazoncorretto:17-alpine3.15-jdk AS build
WORKDIR /build
RUN apk add maven=3.8.3-r0

COPY pom.xml ./

# Non Cacheable steps
COPY . .
RUN mvn -B -e clean install -DskipTests=true

# Distribution Stage
FROM amazoncorretto:17-alpine3.15-jdk AS runner

WORKDIR /app
COPY --from=build /build/ccee-meter-api/target/*.jar ./application.jar

EXPOSE 80

CMD [ "java", "-jar", "application.jar"]