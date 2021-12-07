FROM openjdk:11-jdk
# Resources are not packed as part of package
# So copying file
RUN mkdir -p src/main/resources
COPY src/main/resources/product_configuration.csv src/main/resources/product_configuration.csv
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]