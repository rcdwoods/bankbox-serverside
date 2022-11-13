FROM gradle:7.5.1-jdk11-hotspot AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle -Dorg.gradle.jvmargs=-Xmx1536m assemble --no-daemon

FROM amazoncorretto:11

COPY --from=build /home/gradle/src/build/libs/bankbox.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]