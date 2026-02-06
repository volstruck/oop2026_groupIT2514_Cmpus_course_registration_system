Course Enrollment System (JDBC, PostgreSQL)
Overview

This project is a Java console application that models a course enrollment system using PostgreSQL and plain JDBC.
It demonstrates clean separation between domain models, repository layer, and database utilities, without using external frameworks (Spring, Hibernate, ORM).

The goal of the project is to practice:

JDBC fundamentals

SQL interaction from Java

clean architecture

type-safe domain modeling

Technologies

Java (JDK 17+ recommended)

PostgreSQL (Supabase)

JDBC (PostgreSQL driver)

IntelliJ IDEA

No frameworks or ORMs are used.
````
Project Structure
edu.aitu.oop3
├── db
│   ├── DbUtils
│   ├── JdbcConfig
│   ├── ResultSetHandler
│   ├── StatementBinder
│   └── DatabaseConnection
│
├── models
│   ├── Course
│   ├── Student
│   └── Enrollment
│
├── repository
│   └── jdbc
│       ├── JdbcCourseRepository
│       ├── JdbcStudentRepository
│       └── JdbcEnrollmentRepository
│
└── campus.Main.Main

````

Database Design
````
Courses
CREATE TABLE courses (
id SERIAL PRIMARY KEY,
name TEXT UNIQUE NOT NULL,
instructor_name TEXT NOT NULL,
credits INT NOT NULL,
weeks INT NOT NULL,
max_cap INT NOT NULL,
allowed_faculties TEXT[] NOT NULL,
schedule_start TIME NOT NULL,
schedule_end TIME NOT NULL,
schedule_day INT NOT NULL
);
````

````
Students
CREATE TABLE students (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
faculty TEXT NOT NULL
);
````

````
Enrollments
CREATE TABLE enrollments (
id SERIAL PRIMARY KEY,
student_id INT NOT NULL,
course_id INT NOT NULL,
UNIQUE (student_id, course_id)
);
````
User input from the terminal is parsed explicitly using LocalTime.parse(...).

Security Notes

Password hashing is intentionally not implemented

This project is educational and not production-facing

The code structure allows hashing to be added later without refactoring

Limitations

No connection pooling

No transactions

No concurrency handling

No ORM

These tradeoffs are intentional to focus on core JDBC understanding.

Purpose

This project is intended for:

learning JDBC deeply

understanding SQL ↔ Java interaction

practicing clean repository design

academic coursework
