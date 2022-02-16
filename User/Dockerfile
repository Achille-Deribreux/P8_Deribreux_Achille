FROM openjdk:11
EXPOSE 8082
WORKDIR /tourguide/user
COPY build/libs/User.jar tourguide-user.jar
CMD ["java", "-jar", "tourguide-user.jar"]