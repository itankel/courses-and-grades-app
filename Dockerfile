FROM openjdk:11-jdk-slim
MAINTAINER ifat.me
COPY ./target/courses_and_grades_app-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch courses_and_grades_app-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","courses_and_grades_app-0.0.1-SNAPSHOT.jar"]