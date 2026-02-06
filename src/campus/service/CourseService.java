package campus.service;

import campus.exceptions.CourseHasEnrollmentsException;
import campus.exceptions.EntityNotFoundException;
import campus.models.Course;
import campus.repository.CourseRepository;
import campus.repository.EnrollmentRepository;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;

    public CourseService(CourseRepository courseRepo,
                         EnrollmentRepository enrollmentRepo) {
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public void createCourse(Course c) {
        courseRepo.create(c);
    }

    public void deleteCourse(int courseId) {

        Course c = courseRepo.findById(courseId);
        if (c == null) {
            throw new EntityNotFoundException("Course not found");
        }

        if (!enrollmentRepo.findByCourseId(courseId).isEmpty()) {
            throw new CourseHasEnrollmentsException(
                    "Cannot delete course with active enrollments"
            );
        }

        courseRepo.delete(courseId);
    }

    public Course getCourseById(int id) {
        return courseRepo.findById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }
}
