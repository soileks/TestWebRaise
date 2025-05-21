# Subscription Service - REST API для управления пользователями и подписками
## Описание проекта
Микросервис на Spring Boot 3, предоставляющий REST API для:

Управления пользователями (CRUD операции)

Управления подписками пользователей на цифровые сервисы

Получения аналитики по популярным подпискам

Технологический стек:

Java 17

Spring Boot 3

PostgreSQL

Docker

Maven

## Запуск приложения

Вариант 1: Запуск с помощью Docker
Склонируйте репозиторий:

git clone https://github.com/soileks/TestWebRaise

Соберите и запустите контейнеры:

docker-compose up --build

Приложение будет доступно по адресу:

http://localhost:8080


Вариант 2: Локальный запуск (без Docker)
Установите и запустите PostgreSQL:

Создайте БД:

sql
CREATE DATABASE subscription_db;
CREATE USER postgres WITH PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE subscription_db TO postgres;
Настройте приложение:
Убедитесь, что application.yml содержит правильные параметры подключения (указать свои username и password от БД):

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/subscription_db
    username: postgres
    password: postgres
Запустите приложение:

mvn spring-boot:run

## API Endpoints
Пользователи
POST /users - Создать пользователя

GET /users/{id} - Получить пользователя

PUT /users/{id} - Обновить пользователя

DELETE /users/{id} - Удалить пользователя

Подписки
POST /users/{id}/subscriptions - Добавить подписку

GET /users/{id}/subscriptions - Получить подписки пользователя

DELETE /users/{id}/subscriptions/{sub_id} - Удалить подписку

GET /subscriptions/top - ТОП-3 популярных подписок
