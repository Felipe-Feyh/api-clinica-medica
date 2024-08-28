exemplo de comando para subir a API no server:

`java "-Dspring.profiles.active=prod" "-DDATASOURCE_URL=jdbc:mysql://localhost:3306/clinica" "-DDATASOURCE_USERNAME=root" "-DDATASOURCE_PASSWORD=root" -jar target/api-0.0.1-SNAPSHOT.jar`

Lembrando que deverá ser modificada a URL, username e password.