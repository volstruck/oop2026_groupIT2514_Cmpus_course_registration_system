package campus.repository;

import campus.models.Student;

public interface StudentRepository extends Repository<Student>{
    Student findByEmail(String email);
}
