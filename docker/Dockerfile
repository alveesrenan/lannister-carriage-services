FROM java:8

ADD target/java-challenge.jar /

EXPOSE 8080
EXPOSE 8787

ENTRYPOINT java -Dspring.profiles.active=${PROFILE} -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n -Dserver.port=8080 -jar java-challenge.jar