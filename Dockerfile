FROM gradle:latest
WORKDIR /app
RUN git clone https://github.com/JC21-210814/r05sikaku-kanri.git servlet-source10
RUN ls -l /app/servlet-source10
COPY ./build.gradle /app/servlet-source10/build.gradle
RUN gradle -p servlet-source10 war



#FROM jetty:latest
FROM tomcat:9-jdk17
USER root
RUN apt update
RUN apt install -y git

RUN java --version
COPY --from=0 /app/servlet-source/build/libs/my-servlet-app.war /usr/local/tomcat/webapps/
EXPOSE 8080

CMD ["catalina.sh", "run"]
