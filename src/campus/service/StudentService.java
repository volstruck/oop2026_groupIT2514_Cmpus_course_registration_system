package campus.service;

import campus.models.Student;
import campus.repository.StudentRepository;

import java.util.List;

public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student login(String email, String password) {
        Student s = repo.findByEmail(email);
        if (s == null) {
            throw new IllegalStateException("Student not found");
        }
        if (!s.getPassword().equals(password)) {
            throw new IllegalStateException("Wrong password");
        }
        return s;
    }


    public void createStudent(Student s) {
        repo.create(s);
    }

    public void deleteStudent(int id) {
        repo.delete(id);
    }

    public Student getStudentById(int id) {
        return repo.findById(id);
    }

    public Student getStudentByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
