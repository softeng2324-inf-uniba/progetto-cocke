FROM openjdk:19-jdk-slim
RUN mkdir /app
COPY ./build/libs/ataxx-all.jar /app
RUN mkdir /app/src
ADD ./src /app/src
WORKDIR /app
ENTRYPOINT ["java", "-jar", "ataxx-all.jar"]
