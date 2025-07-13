FROM ubuntu:latest AS build

# Instala dependências necessárias
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    git \
    unzip \
    wget \
    curl

# Define o diretório de trabalho no container
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Garante permissão de execução ao Gradle Wrapper
RUN chmod +x ./gradlew

# Executa o build usando o wrapper
RUN ./gradlew build --no-daemon

# Imagem final mais leve
FROM openjdk:17-jdk-slim
EXPOSE 8080

# Copia o JAR gerado do estágio de build
COPY --from=build /app/build/libs/*.jar app.jar

# Define o ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
