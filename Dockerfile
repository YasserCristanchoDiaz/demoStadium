FROM maven:3-amazoncorretto-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /target/demoStadium.jar taller-electiva.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","taller-electiva.jar"]