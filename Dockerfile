FROM openjdk:11.0.6
ADD target/first-project.jar first-project.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","first-project.jar"]