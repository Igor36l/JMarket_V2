# JMARKET V2_0. 
   
Проект на микросервисной архитектуре с балансировкой нагрузки между микросервисами с помощью Spring Cloud away, 
централизированным администрированием всех модулей с помощью Spring Boot Admin.   
Используемые технологии: Java 21, Maven, Spring Boot 3.2.4, Data JPA, Data, Web, Security OAUTH 2.0, Keycloack, 
PostgresQL, Flyway, Thymeleaf, lombok, Docker, redis.

## Модули  

### 1. ***client-view-app:***  
MVC модуль для динамической генерации представления с использованием thymeleaf.
OAUTH2,0 аутентификация с провайдером keycloak.
Выступает в роли клиента для других модулей, в которые вынесена логика взаимодействия с базами данных. 
Соответствует принципам проектирования RESTfull сервисов.

