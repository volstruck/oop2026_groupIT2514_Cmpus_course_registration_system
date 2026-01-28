package campus.repository.jdbc;


import campus.models.Enrollment;
import campus.repository.EnrollmentRepository;
import edu.aitu.oop3.db.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcEnrollmentRepository implements EnrollmentRepository {

    @Override
    public void create(Enrollment e) {
        DbUtils.execUpdate(
                "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)",
                ps -> {
                    ps.setInt(1, e.getStudentId());
                    ps.setInt(2, e.getCourseId());
                }
        );
    }

    @Override
    public void delete(int id) {
        DbUtils.execUpdate(
                "DELETE FROM enrollments WHERE id = ?",
                ps -> ps.setInt(1, id)
        );
    }


    @Override
    public Enrollment findById(int id) {
        return DbUtils.execQuery(
                "SELECT * FROM enrollments WHERE id = ?",
                ps -> ps.setInt(1, id),
                rs -> rs.next() ? map(rs) : null
        );
    }


    @Override
    public List<Enrollment> findAll() {
        return DbUtils.execQuery(
                "SELECT * FROM enrollments",
                ps -> {},
                rs -> {
                    List<Enrollment> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(map(rs));
                    }
                    return list;
                }
        );
    }

    @Override
    public List<Enrollment> findByStudentId(int studentId) {
        return DbUtils.execQuery(
                "SELECT * FROM enrollments WHERE student_id = ?",
                ps -> ps.setInt(1, studentId),
                rs -> {
                    List<Enrollment> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(map(rs));
                    }
                    return list;
                }
        );
    }

    @Override
    public List<Enrollment> findByCourseId(int courseId) {
        return DbUtils.execQuery(
                "SELECT * FROM enrollments WHERE course_id = ?",
                ps -> ps.setInt(1, courseId),
                rs -> {
                    List<Enrollment> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(map(rs));
                    }
                    return list;
                }
        );
    }

    @Override
    public boolean exists(int studentId, int courseId) {
        return DbUtils.execQuery(
                "SELECT 1 FROM enrollments WHERE student_id = ? AND course_id = ?",
                ps -> {
                    ps.setInt(1, studentId);
                    ps.setInt(2, courseId);
                },
                ResultSet::next
        );
    }

    private Enrollment map(ResultSet rs) throws SQLException {
        return new Enrollment(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("course_id")
        );
    }
}