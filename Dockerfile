# build stage
#  docker build -t grupo60fiap2023/snackhubpay-app .
#  docker push grupo60fiap2023/snackhubpay-app
# Use a imagem Maven como estágio de compilação
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /usr/app/

COPY . .

# Executa a compilação e o empacotamento do JAR
RUN mvn clean package

# Estágio de execução usando a imagem JRE Alpine
FROM eclipse-temurin:17.0.5_8-jre-alpine

# Copia o JAR construído no estágio anterior para o diretório de destino
COPY --from=builder /usr/app/infrastructure/target/*.jar /opt/app/snackhubpay.jar

# Cria um usuário e um grupo
RUN addgroup -S spring && adduser -S spring -G spring

# Define o usuário padrão para o processo em execução
USER spring:spring

# Comando a ser executado ao iniciar o contêiner
CMD java -jar /opt/app/snackhubpay.jar
