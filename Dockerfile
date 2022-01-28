FROM openjdk:14-jdk-alpine

COPY build/libs/drools-with-decision-tables.jar /

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=${SPRING_PROFILE_ENV} /drools-with-decision-tables.jar