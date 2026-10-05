FROM eclipse-temurin:22-jdk AS buildstage

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_Base_Aplicada /Wallet_Base_Aplicada

ENV TNS_ADMIN=/Wallet_Base_Aplicada

RUN mvn clean package

FROM eclipse-temurin:22-jdk

COPY --from=buildstage /app/target/semana3d-0.0.1-SNAPSHOT.jar /app/semana3d.jar

COPY Wallet_Base_Aplicada /Wallet_Base_Aplicada

ENV TNS_ADMIN=/Wallet_Base_Aplicada
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/semana3d.jar"]