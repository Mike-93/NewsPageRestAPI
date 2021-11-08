FROM openjdk:11
ADD target/news-page-rest.jar news-page-rest.jar
ENTRYPOINT ["java", "-jar","news-page-rest.jar"]