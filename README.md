[![Java CI with Maven](https://github.com/renaner123/Parking-Control-Api/actions/workflows/maven.yml/badge.svg)](https://github.com/renaner123/Parking-Control-Api/actions/workflows/maven.yml)

## Controle de veículos em condomínio

Criando uma API Rest com Spring Boot. Código base se encontra neste [repositório](https://www.youtube.com/watch?v=LXRU-Z36GEU&t=9s). 

Projeto consite em fazer uma API para ter controle de estacionamento de um condomínio. Foram e/ou estão sendo feitas as seguintes melhorias no projeto (para fins de estudo).

- [x] Usar CustomValidator - Verificar se o parâmetro passado (POST) já existe no banco
- [ ] Receber argumentos no método findAll para listar por argumento
- [x] Fazer relacionamento entre as classes - OneToOne entre Car de ParkingSpot
- [ ] Usar meios de conversões para alterar dados com o PUT - Converter
- [ ] Inserir testes de unidade com JUnit 5
- [x] Inserir Logger com slf4j
- [ ] Usar MapSruct e BeansUtils para converter entidades (testar com ambos) - BeanUtils OK
- [ ] Consumir uma API externa - Spring WebClient 
- [x] Usar container Docker para o banco de dados PostgreSQL
- [ ] Gerar a documentação com OpenAPI.
## Como compilar e executar esse projeto 

Para executar a aplicação é necessário fazer o clone deste repositório com o comando abaixo.

```shell
git clone https://github.com/renaner123/Parking-Control-Api.git
```

## Pré-requisitos

* Ferramentas de desenvolvimento
  * Java JDK 17 
  * Maven
  * pgAdmin 4
  * Postman ou Insomnia
* Serviços
  * Servidor PostgreSQL

## conexão com banco de dados 

A configuração da conexão com o banco de dados é feita através do arquivo [properties](src\main\resources\application.properties) e deve ser conforme abaixo.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=banco123
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.jdbc.lab.nan_contextual_creation=true
spring.jpa.show-sql = false
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQL9Dialect
```

## Container Docker PostgresSQL

Caso não tenha um servidor Postgres é possível subir um container usando Docker com o seguinte comando:

```shell
  sudo docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=your-password -d postgres
```
Caso queira gerenciar o banco de dados com pgAdmin, é possível usar um container para isso, por exemplo:

```shell
  docker run --rm -p 5050:5050 thajeztah/pgadmin4
```
Após executar e acessar o endereço gerado pelo container pgadmin4 `(http://0.0.0.0:5050)` é necessário adicionar o servidor em `Add New Server`. 

Passos:
1. Em General é necessário configurar um Name.
2. Em Connection:
   1. Host name/addres -> é necessário colocar o endereço IP do host (não pode ser localhost)
   2. password -> inserir o password usado na criação do container
   3. Port e username devem ser alterados caso não tenha usado o padrão

## Executando a aplicação

Para compilar o projeto e gerar o artefato `jar` é necessário executar o comando abaixo
```shell
mvn package -DskipTests
```

Com o projeto compilado para executar a aplicação deve-se usar o comando a seguir.

```
java -jar target/parking-control-0.0.1-SNAPSHOT.jar
```
A aplicação vai estar disposnível na porta 8080 e poderá ser acessado no seguinte endereço: http://localhost:8080

## Serviços

|Método| Endpoint| Serviço|
|-------|---------|--------|
|GET|localhost:8080/parking-spot/| Lista todas as vagas de estacionamento
|GET|localhost:8080/parking-spot/{id}|Lista as informações de uma vaga pelo id
|GET|localhost:8080/parking-spot/block/{block}|Lista as informações de uma vaga pelo id
|POST|localhost:8080/parking-spot| Cadastra uma nova vaga. Recebe json no body
|DELETE|localhost:8080/parking-spot/{id} | Deleta uma vaga pelo id
|PUT|localhost:8080/parking-spot/id{} | Altera informações no cadastro. Recebe json no body

Json para enviar no cadastro e para alteração de dados deve ser conforme abaixo:

```json
{
    "parkingSpotNumber" : "208H",
    "licensePlateCar" : "RRS7565",
    "brandCar": "audi",
    "modelCar": "q5",
    "colorCar": "black",
    "responsibleName" : "Renan Rodolfo",
    "apartment" : "208",
    "block": "B"
}
```

## Biblioteca externas

Abaixo as bibliotecas que auxiliaram no desenvolvimento do projeto.

Biblioteca |Função| Licença 
-|-|-
[Spring Boot](https://spring.io/projects/spring-boot)|Framework ORM|[Apache 2.0](https://github.com/spring-projects/spring-boot/blob/main/LICENSE.txt)
[junit](https://junit.org/junit5/)|Testes unitários|[EPL 2.0](http://www.eclipse.org/legal/epl-v20.html)
[slf4j](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core/2.6.2)|Mensagens de log|[MIT](https://www.slf4j.org/license.html)
[PostgreSQL](https://github.com/redis/jedis)|Conexão com PostgreSQL|[PostgreSQL](https://www.postgresql.org/about/licence/)







