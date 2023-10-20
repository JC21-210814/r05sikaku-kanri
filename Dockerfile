FROM gradle:latest
USER root
RUN apt update
RUN apt install -y git
WORKDIR /app
RUN git clone https://github.com/JC21-210814/r05sikaku-kanri.git servlet-source
RUN ls -l /app/servlet-source
RUN gradle -p servlet-source war

FROM tomcat:9-jdk17
USER root
RUN apt update
RUN apt install -y git

RUN java --version
COPY --from=0 /app/servlet-source/build/libs/my-servlet-app.war /usr/local/tomcat/webapps/
EXPOSE 8080

CMD ["catalina.sh", "run"]
