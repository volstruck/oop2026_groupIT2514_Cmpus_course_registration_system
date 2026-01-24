CREATE TABLE students (
                          id            SERIAL PRIMARY KEY,
                          name          TEXT NOT NULL,
                          surname       TEXT NOT NULL,
                          password      TEXT NOT NULL,
                          email         TEXT UNIQUE NOT NULL,
                          course_year   INT NOT NULL CHECK (course_year BETWEEN 1 AND 6),
                          faculty       TEXT NOT NULL
);
