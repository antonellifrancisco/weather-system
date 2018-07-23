FROM gcr.io/google-appengine/openjdk:9

 COPY target/weather-system-1.0-SNAPSHOT.jar app.jar
 CMD [ "java", "-jar","app.jar", "server"]
