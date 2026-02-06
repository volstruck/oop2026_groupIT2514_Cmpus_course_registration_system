CREATE TABLE enrollments (
                             id SERIAL PRIMARY KEY,
                             student_id INT NOT NULL REFERENCES students(id) ON DELETE CASCADE,
                             course_id INT NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
                             UNIQUE (student_id, course_id)
);
