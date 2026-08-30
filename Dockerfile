FROM eclipse-temurin:25

WORKDIR /opt/app

COPY target/accesscontrol-0.0.1.jar app.jar

EXPOSE 8080

RUN useradd app && chown -R app:app /opt/app
USER app

CMD ["java", "-jar", "app.jar"]