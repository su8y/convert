FROM alpine/java:21-jdk

COPY build/libs/web.jar /webapp.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/webapp.jar"]
