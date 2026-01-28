package campus.service;

import campus.models.Course;
import campus.repository.CourseRepository;

import java.util.List;

public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }


    public void createCourse(Course c) {
        // validation can go here later
        repo.create(c);
    }


    public void deleteCourse(int id) {
        repo.delete(id);
    }


    public Course getCourseById(int id) {
        return repo.findById(id);
    }


    public List<Course> getAllCourses() {
        return repo.findAll();
    }
}
