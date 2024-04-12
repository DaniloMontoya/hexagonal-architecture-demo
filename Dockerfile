FROM 8.5-jdk17-focal AS TEMP_BUILD_IMAGE
ENV INSTALL_DIR=/opt/app
WORKDIR $INSTALL_DIR
COPY . $INSTALL_DIR
RUN gradle :clean
RUN gradle :bootJar

FROM 8.5-jdk17-focal
ENV INSTALL_DIR=/opt/app
WORKDIR $INSTALL_DIR
COPY --from=TEMP_BUILD_IMAGE /opt/app/build/libs/*T.jar ./app.jar
COPY --from=TEMP_BUILD_IMAGE /opt/app/deploy/application.yml ./application.yml
EXPOSE 8082
CMD [ "java","-jar","/opt/app/app.jar", "--spring.config.location=file:application.yml"]
