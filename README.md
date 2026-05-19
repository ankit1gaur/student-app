# 🎓 Student App — Spring Boot + Render PostgreSQL

A full-stack web application that connects to a **Render PostgreSQL** database and returns a list of random student names via a REST API.

---

## 📁 Project Structure

```
student-app/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/com/example/studentapp/
        │   ├── StudentAppApplication.java      ← Spring Boot entry point
        │   ├── controller/
        │   │   └── StudentController.java      ← REST API endpoints
        │   ├── model/
        │   │   └── Student.java                ← JPA Entity
        │   ├── repository/
        │   │   └── StudentRepository.java      ← JPA Repository (random query)
        │   └── service/
        │       └── StudentService.java         ← Business logic + DB seeding
        └── resources/
            ├── application.properties          ← DB config (edit this!)
            └── static/
                └── index.html                  ← Frontend (HTML + CSS + JS)
```

---

## 🛠️ Setup Instructions

### Step 1 — Create a Render PostgreSQL Database

1. Go to [https://render.com](https://render.com) and sign in.
2. Click **New +** → **PostgreSQL**.
3. Fill in the database name (e.g., `student_db`) and click **Create Database**.
4. Once created, go to the **Info** tab and copy:
   - **Host** (e.g., `dpg-xxxxx.oregon-postgres.render.com`)
   - **Database** name
   - **Username**
   - **Password**

### Step 2 — Configure application.properties

Open `src/main/resources/application.properties` and replace the placeholders:

```properties
spring.datasource.url=jdbc:postgresql://<YOUR_RENDER_HOST>:5432/<YOUR_DB_NAME>
spring.datasource.username=<YOUR_DB_USERNAME>
spring.datasource.password=<YOUR_DB_PASSWORD>
```

**Example:**
```properties
spring.datasource.url=jdbc:postgresql://dpg-abc123.oregon-postgres.render.com:5432/student_db
spring.datasource.username=student_db_user
spring.datasource.password=supersecretpassword123
```

> ⚠️ **Important:** Render's free PostgreSQL requires SSL. If you get an SSL error, add this to your JDBC URL:
> ```
> ?sslmode=require
> ```
> Full URL: `jdbc:postgresql://dpg-xxx.oregon-postgres.render.com:5432/student_db?sslmode=require`

### Step 3 — Run the Application

```bash
# Using Maven Wrapper
./mvnw spring-boot:run

# OR with Maven installed globally
mvn spring-boot:run
```

The app starts on **http://localhost:8080**

---

## 🔌 API Endpoints

| Method | Endpoint               | Description                        |
|--------|------------------------|------------------------------------|
| GET    | `/api/students/random` | Returns 10 random students from DB |
| GET    | `/api/students`        | Returns ALL students               |
| GET    | `/api/health`          | Health check                       |

### Sample Response — `/api/students/random`

```json
[
  { "id": 7,  "name": "Priya Patel",    "email": "priya.patel@college.in"  },
  { "id": 14, "name": "Meera Pillai",   "email": "meera.pillai@college.in" },
  { "id": 3,  "name": "Rohan Mehta",    "email": "rohan.mehta@college.in"  }
]
```

---

## 🌐 Frontend

Open **http://localhost:8080** in your browser. Click **"Fetch Random Students"** — the frontend calls `GET /api/students/random` and displays the results.

---

## 🗄️ Database

- The app uses **JPA / Hibernate** with `ddl-auto=update` — the `students` table is created automatically on first run.
- On startup, if the table is empty, **20 sample students** are seeded automatically.
- The `findRandomStudents()` query uses PostgreSQL's `ORDER BY RANDOM() LIMIT 10`.

---

## 🔧 Requirements

- Java 17+
- Maven 3.6+
- A Render PostgreSQL database (free tier works)
