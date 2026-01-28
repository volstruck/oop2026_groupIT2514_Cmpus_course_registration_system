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


    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return repo.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return repo.findByCourseId(courseId);
    }
}
