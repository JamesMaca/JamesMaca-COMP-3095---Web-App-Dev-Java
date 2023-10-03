# ---------------------------------------
# Dockerfile for product-service
# ---------------------------------------

# ----
# Build stage
# --

#base image
FROM gradle:8-jdk17-alpine AS builder

# Copy the application files from the host machine to the image filesystem
COPY --chown=gradle:gradle . /home/gradle/src

# set the directory within the image, for excuting future commands
WORKDIR /home/gradle/src

#run gradle build within the container - NOT the host machine
RUN gradle build -x test