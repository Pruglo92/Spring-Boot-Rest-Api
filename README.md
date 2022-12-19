# ***Spring-Boot-Rest-API***
-----------------------------------
-----------------------------------
# ***Stack используемых технологий:***
-----------------------------------
- Java 17
- Swagger
- Spring Boot
- Spring Web
- Spring Security
- JWT Token
- Spring Data
- PostgreSQL
- Hibernate ORM
- JPA
- Liquibase
- Mapstruct
- Lombok
- Maven
***

# ***Шаги запуска проекта:***
-----------------------------------
### 1. Скачиваем проект с помощью выполнения команды в терминале: 
git clone https://github.com/Pruglo92/Spring-Boot-Rest-Api.git

### 2. Открываем проект в IntelliJ IDEA через 
File -> New -> Progect from Existing Sourses...

### 3. Зпускаем приложение через Run/Debug Configurations

### 4. Приложение уже запущено, для выполнения HTTP запросов для удобства предоставлен Swagger:
 http://localhost:8080/swagger-ui/index.html 

### 5. Для возможности использования Rest запросов необходимо авторизоваться как user либо как admin
в authentication-rest-controller в теле запроса ввести:

Для прав админа
{
  "username": "test_username_admin",
  "password": "test_password_admin"
}

Для прав юзера 
{
  "username": "test_username_user",
  "password": "test_password_user"
}

### 6. Далее необходимо открыть Available authorizations(кнопка Authorize) 
и применить полученный jwt Токен для авторизации.

### 7. После авторизации нам будет доступна работа с бизнес логикой приложения через остальные контроллеры.

### Ветка master собирает jar file 
