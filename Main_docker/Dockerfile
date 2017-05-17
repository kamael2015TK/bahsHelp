FROM anapsix/alpine-java
MAINTAINER s153067@student.dtu.dk
copy /dist/*  /data/
copy /dist/lib/* /data/lib/
CMD ["java", "-jar", "-Djava.security.policy=java.policy.applet", "data/sensorinfrastructure.jar"]
EXPOSE 53712
EXPOSE 7777
EXPOSE 23030
EXPOSE 23031