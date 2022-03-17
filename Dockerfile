## selecionando a imagem
FROM openjdk:11
## expondo porta 8100
EXPOSE 8100
## criando pasta para o app
WORKDIR /src
## copiando o arquivo jar para a pasta
COPY /target/*.jar /src/EmailSender.jar
## lista de comandos a serem execultados
ENTRYPOINT [ "java", "-jar", "/src/EmailSender.jar"]
