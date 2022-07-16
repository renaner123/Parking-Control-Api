# Parking-Control-Api
Exemplo de utilização de Spring Boot

## Controle de veículos em condomínio
## Dependências

* Spring Web
* Spring Data JPA
* Validation
* PostgreSQL Driver
* Lombok

## Instalar dependências 

mvn package -DskipTests

## conexão com banco de dados 

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/parking-control-db
spring.datasource.username=postgres
spring.datasource.password=banco123
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.jdbc.lab.nan_contextual_creation=true
#ddl-auto on first time to need use create instead none
```