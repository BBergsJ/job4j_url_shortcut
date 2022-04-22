[![Build Status](https://app.travis-ci.com/BBergsJ/job4j_url_shortcut.svg?branch=master)](https://app.travis-ci.com/BBergsJ/job4j_url_shortcut)

# Описание проекта
Проект представляет REST сервис по сбору статистики url адресов.
При регистрации на ресурсе, пользователю предоставляется уникальный логин и пароль.
С помощью них, можно авторизоваться. - /registration и /auth/login.
После чего пользователь может регистрировать url адреса получая специальный код перехода - /convert, а также /statistic для получения статистики переходов.
Переход по специальному коду происходит через /redirect и возможен без токена.

### Используемые технологии
* Java 17
* Spring boot 2
* Spring Security & JWT Autorization
* Spring Data JPA
* PostgreSQL

### Рекомендации для запуска
* Java 17 и выше
* db PostgreSQL

# Запуск проекта

````
mvn install
````

````
java -jar target/job4j_url_shortcut-0.0.1-SNAPSHOT.jar
````
или с вашими настройками db
````
java -jar target/job4j_url_shortcut-0.0.1-SNAPSHOT.jar --db=shortcut --user=postgres --password=password --port=your_port
````

### Docker образ
````
docker pull jfatfox/job4j_shortcut
````

````
mvn package -Dmaven.test.skip && docker build -t shortcut . && docker-compose up
````

[Dockerhub](https://hub.docker.com/r/jfatfox/job4j_shortcut)


### Работа с REST API

#### Регистрация сайта в системе

````
curl --location --request POST 'http://localhost:8080/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "site": "vk.com"
}'
````

#### Логин, получение токена

````
curl --location --request POST 'http://localhost:8080/login' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "login": "your_login",
    "password": "your_password"
}'
````

#### Конвертация ссылки, получение уникального кода

````
curl --location --request POST 'http://localhost:8080/convert' \
--header 'Authorization: Bearer your_token\
--header 'Content-Type: application/json' \
--data-raw '{
    "url": "http://job4j.ru:8888/TrackStudio/staticframeset.html#253134"
}'
````

#### Получение статистики

````
curl --location --request GET 'http://localhost:8080/statistic' \
--header 'Authorization: Bearer your_token\
````

#### Получение URL по уникальному коду

````
curl --location --request GET 'http://localhost:8080/redirect/{code}' \
--header 'Content-Type: application/json' \
````