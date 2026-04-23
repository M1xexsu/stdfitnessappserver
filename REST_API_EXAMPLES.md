# Тестирование REST API

## 1. Регистрация пользователя

### POST /auth/register
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123"
  }'
```

**Ответ:**
```json
{
  "user_id": 1,
  "username": "testuser",
  "message": "User registered successfully"
}
```

---

## 2. Вход пользователя

### POST /auth/login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

**Ответ:**
```json
{
  "user_id": 1,
  "username": "testuser",
  "message": "Login successful"
}
```

---

## 3. Получить все активности

### GET /activities
```bash
curl http://localhost:8080/activities
```

**Ответ:**
```json
[
  {
    "activity_id": 1,
    "user": {
      "user_id": 1,
      "username": "testuser",
      "email": "test@example.com"
    },
    "activity_date": "2026-04-24T10:30:00.000+00:00",
    "steps": 5000,
    "burnt": 250,
    "goal_achieved": true
  }
]
```

---

## 4. Создать активность

### POST /activities
```bash
curl -X POST http://localhost:8080/activities \
  -H "Content-Type: application/json" \
  -d '{
    "user": {
      "user_id": 1
    },
    "activity_date": "2026-04-24T10:30:00Z",
    "steps": 5000,
    "burnt": 250,
    "goal_achieved": true
  }'
```

**Ответ:**
```json
{
  "activity_id": 1,
  "user": {
    "user_id": 1,
    "username": "testuser"
  },
  "activity_date": "2026-04-24T10:30:00.000+00:00",
  "steps": 5000,
  "burnt": 250,
  "goal_achieved": true
}
```

---

## 5. Получить активность по ID

### GET /activities/{id}
```bash
curl http://localhost:8080/activities/1
```

---

## 6. Получить активности пользователя

### GET /activities/user/{userId}
```bash
curl http://localhost:8080/activities/user/1
```

---

## 7. Обновить активность

### PUT /activities/{id}
```bash
curl -X PUT http://localhost:8080/activities/1 \
  -H "Content-Type: application/json" \
  -d '{
    "activity_date": "2026-04-24T10:30:00Z",
    "steps": 6000,
    "burnt": 300,
    "goal_achieved": false
  }'
```

---

## 8. Удалить активность

### DELETE /activities/{id}
```bash
curl -X DELETE http://localhost:8080/activities/1
```

**Ответ:**
```json
{
  "message": "Activity deleted successfully"
}
```

---

## Структура проекта

```
src/main/java/github/m1xexsu/stdfitnessappserver/
├── controller/
│   ├── AuthController.java       # Регистрация и вход
│   └── ActivityController.java   # CRUD операции для активности
├── service/
│   ├── UserService.java          # Бизнес-логика пользователя
│   └── ActivityService.java      # Бизнес-логика активности
├── repository/
│   ├── UserRepository.java       # Доступ к БД (User)
│   ├── ActivityRepository.java   # Доступ к БД (Activity)
│   └── ProfileRepository.java    # Доступ к БД (Profile)
├── entity/
│   ├── UserEntity.java           # Таблица User
│   ├── ActivityEntity.java       # Таблица Activity
│   └── ProfileEntity.java        # Таблица Profile
├── dto/
│   ├── RegisterRequest.java      # DTO для регистрации
│   ├── LoginRequest.java         # DTO для входа
│   └── LoginResponse.java        # DTO для ответа входа
└── config/
    └── SecurityConfig.java       # Spring Security конфигурация
```

---

## Как запустить

1. **Убедитесь, что MariaDB запущена**
   - Хост: localhost:3306
   - БД: stdfitness
   - Пользователь: root
   - Пароль: your_password (обновите в application.properties)

2. **Запустите приложение**
   ```bash
   ./gradlew bootRun
   ```

3. **Приложение будет доступно на** `http://localhost:8080`

---

## Для Postman

Импортируйте эту коллекцию (сохраните как `postman_collection.json`):

```json
{
  "info": {
    "name": "STD Fitness API",
    "version": "1.0.0"
  },
  "item": [
    {
      "name": "Auth",
      "item": [
        {
          "name": "Register",
          "request": {
            "method": "POST",
            "header": [{"key": "Content-Type", "value": "application/json"}],
            "body": {
              "mode": "raw",
              "raw": "{\"username\":\"testuser\",\"email\":\"test@example.com\",\"password\":\"password123\"}"
            },
            "url": {"raw": "http://localhost:8080/auth/register"}
          }
        },
        {
          "name": "Login",
          "request": {
            "method": "POST",
            "header": [{"key": "Content-Type", "value": "application/json"}],
            "body": {
              "mode": "raw",
              "raw": "{\"username\":\"testuser\",\"password\":\"password123\"}"
            },
            "url": {"raw": "http://localhost:8080/auth/login"}
          }
        }
      ]
    },
    {
      "name": "Activities",
      "item": [
        {
          "name": "Get All",
          "request": {
            "method": "GET",
            "url": {"raw": "http://localhost:8080/activities"}
          }
        },
        {
          "name": "Create",
          "request": {
            "method": "POST",
            "header": [{"key": "Content-Type", "value": "application/json"}],
            "body": {
              "mode": "raw",
              "raw": "{\"user\":{\"user_id\":1},\"activity_date\":\"2026-04-24T10:30:00Z\",\"steps\":5000,\"burnt\":250,\"goal_achieved\":true}"
            },
            "url": {"raw": "http://localhost:8080/activities"}
          }
        }
      ]
    }
  ]
}
```

---

## Note

- **Авторизация**: На этом этапе авторизация максимально простая (plain text пароль, без JWT).
  Для production нужно добавить:
  - Хеширование паролей (BCrypt)
  - JWT токены
  - Валидация токенов
  
- **CSRF**: Отключен для разработки. Для production нужно включить.

- **CORS**: Разрешены все источники для разработки.

