package campus.service;

import campus.models.Enrollment;
import campus.repository.EnrollmentRepository;

import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository repo;

    public EnrollmentService(EnrollmentRepository repo) {
        this.repo = repo;
    }

    public void enroll(int studentId, int courseId) {
        if (repo.exists(studentId, courseId)) {
            throw new IllegalStateException("Student already enrolled in this course");
        }
        repo.create(new Enrollment(0, studentId, courseId));
    }

    public void unenroll(int enrollmentId) {
        repo.delete(enrollmentId);
    }

    public List<Enrollment> getAllEnrollments() {
        return repo.findAll();
    }

    public void drop(int studentId, int courseId) {
        Enrollment e = repo.findByStudentId(studentId).stream()
                .filter(x -> x.getCourseId() == courseId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Enrollment not found"));

        repo.delete(e.getId());
    }


    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return repo.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return repo.findByCourseId(courseId);
    }
}
