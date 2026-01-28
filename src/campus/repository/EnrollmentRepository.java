package campus.repository;

import campus.models.Enrollment;
import java.util.List;

public interface EnrollmentRepository extends Repository<Enrollment> {

    boolean exists(int studentId, int courseId);

    List<Enrollment> findByStudentId(int studentId);

    List<Enrollment> findByCourseId(int courseId);
}

