FROM mysql:8.0
WORKDIR /src/app
COPY . .
CMD ["java", "-jar", "app-deadline.jar -P:jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass"]
EXPOSE 9999