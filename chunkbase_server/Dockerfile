FROM gradle:7.6-jdk17-alpine

WORKDIR /app

COPY build.gradle settings.gradle ./
RUN gradle dependencies --no-daemon

COPY src ./src
RUN gradle build --no-daemon -x test

CMD ["gradle", "run", "--no-daemon"]
