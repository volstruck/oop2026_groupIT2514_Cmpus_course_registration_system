CREATE TABLE courses (
                         id                 SERIAL PRIMARY KEY,
                         name               TEXT UNIQUE NOT NULL,
                         instructor_name    TEXT NOT NULL,
                         credits            INT NOT NULL,
                         weeks              INT NOT NULL,
                         max_capacity       INT NOT NULL,
                         allowed_faculties  TEXT[] NOT NULL,
                         day_of_week        INT NOT NULL CHECK (day_of_week BETWEEN 1 AND 7),
                         start_time         TIME NOT NULL,
                         end_time           TIME NOT NULL
);
