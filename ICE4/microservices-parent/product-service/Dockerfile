# ---------------------------------------
# Dockerfile for product-service
# ---------------------------------------

# ----
# Build stage
# --

#base image
# Windows
FROM gradle:8-jdk17-alpine AS builder


# Copy the application files from the host machine to the image filesystem
COPY --chown=gradle:gradle . /home/gradle/src

# set the directory within the image, for excuting future commands
WORKDIR /home/gradle/src

#run gradle build within the container - NOT the host machine
RUN gradle build -x test

# ----
# Package stage
# ---

# ----------------------------------------------------------------------------------------------------
#Base Image
# Windows
FROM openjdk:17-alpine


RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/product-service.jar

ENV MONGO_DB_USERNAME=rootadmin \
    MONGO_DB_PWD=password

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/product-service.jar"]
