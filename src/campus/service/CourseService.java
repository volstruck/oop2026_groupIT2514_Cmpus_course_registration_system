package campus.service;

import campus.models.Course;
import campus.repository.CourseRepository;

import java.util.List;

public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public void createCourse(Course c) {
        // validation can go here later
        repo.create(c);
    }

    // DELETE
    public void deleteCourse(int id) {
        repo.delete(id);
    }

    // FIND BY ID
    public Course getCourseById(int id) {
        return repo.findById(id);
    }

    // FIND ALL
    public List<Course> getAllCourses() {
        return repo.findAll();
    }
}
