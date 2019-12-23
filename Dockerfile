####
# This Dockerfile is used in order to build a container that runs the Quarkus application in native (no JVM) mode
#
# Before building the docker image run:
#
# mvn package -Pnative -Dquarkus.native.container-build=true
#
# Then, build the image with:
#
# docker build -f src/main/docker/Dockerfile.native -t quarkus/localux-quarkus .
#
# Then run the container using:
#
# docker run -i --rm -p 8080:8080 quarkus/localux-quarkus
#
###
FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /work/
COPY target/*-runner /work/application
RUN chmod 775 /work
EXPOSE 8080
# CMD ["./application", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.log.level=DEBUG", "-Dquarkus.datasource.url=vertx-reactive:mysql://192.168.2.2:3306/localux"]
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
