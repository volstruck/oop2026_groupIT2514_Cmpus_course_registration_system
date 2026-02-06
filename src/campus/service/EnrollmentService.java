package campus.service;

import campus.exceptions.CapacityExceededException;
import campus.exceptions.ScheduleConflictException;
import campus.exceptions.EntityNotFoundException;
import campus.models.Course;
import campus.models.Enrollment;
import campus.repository.CourseRepository;
import campus.repository.EnrollmentRepository;

import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final CourseRepository courseRepo;

    public EnrollmentService(EnrollmentRepository enrollmentRepo,
                             CourseRepository courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.courseRepo = courseRepo;
    }

    public void enroll(int studentId, int courseId) {

        // already enrolled
        if (enrollmentRepo.exists(studentId, courseId)) {
            throw new CapacityExceededException("Student already enrolled in this course");
        }

        Course newCourse = courseRepo.findById(courseId);
        if (newCourse == null) {
            throw new EntityNotFoundException("Course not found");
        }

        // capacity check
        int enrolled = enrollmentRepo.findByCourseId(courseId).size();
        if (enrolled >= newCourse.getMaxCapacity()) {
            throw new CapacityExceededException("Course capacity exceeded");
        }

        // schedule conflict check
        List<Enrollment> current = enrollmentRepo.findByStudentId(studentId);
        for (Enrollment e : current) {
            Course existing = courseRepo.findById(e.getCourseId());

            boolean sameDay =
                    existing.getScheduleDay() == newCourse.getScheduleDay();

            boolean overlap =
                    newCourse.getScheduleStart().isBefore(existing.getScheduleEnd()) &&
                            existing.getScheduleStart().isBefore(newCourse.getScheduleEnd());

            if (sameDay && overlap) {
                throw new ScheduleConflictException("Schedule conflict with another course");
            }
        }

        enrollmentRepo.create(new Enrollment(0, studentId, courseId));
    }

    public void drop(int studentId, int courseId) {
        Enrollment e = enrollmentRepo.findByStudentId(studentId).stream()
                .filter(x -> x.getCourseId() == courseId)
                .findFirst()
                .orElseThrow(() ->
                        new EntityNotFoundException("Enrollment not found"));

        enrollmentRepo.delete(e.getId());
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollmentRepo.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return enrollmentRepo.findByCourseId(courseId);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }
}
