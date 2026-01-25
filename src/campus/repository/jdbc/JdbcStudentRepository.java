package campus.repository.jdbc;

import campus.models.Student;
import campus.repository.StudentRepository;
import edu.aitu.oop3.db.DbUtils;

import java.util.ArrayList;
import java.util.List;

public class JdbcStudentRepository implements StudentRepository {

    @Override
    public void create(Student s) {
        DbUtils.execQuery(
                "INSERT INTO students (" +
                        "  name, surname, password, email, course_year, faculty" +
                        ") VALUES (?, ?, ?, ?, ?, ?) " +
                        "RETURNING id",

                ps -> {
                    ps.setString(1, s.getName());
                    ps.setString(2, s.getSurname());
                    ps.setString(3, s.getPassword());
                    ps.setString(4, s.getEmail());
                    ps.setInt(5, s.getCourseYear());
                    ps.setString(6, s.getFaculty());
                },

                rs -> {
                    rs.next();
                    s.setId(rs.getInt("id"));
                    return s;
                }
        );
    }

    @Override
    public void delete(int id) {
        DbUtils.execUpdate(
                "DELETE FROM students WHERE id = ?",
                ps -> ps.setInt(1, id)
        );
    }

    @Override
    public Student findById(int id) {
        return DbUtils.execQuery(
                "SELECT * FROM students WHERE id = ?",
                ps -> ps.setInt(1, id),
                rs -> {
                    if (!rs.next()) return null;
                    return mapStudent(rs);
                }
        );
    }

    @Override
    public List<Student> findAll() {
        return DbUtils.execQuery(
                "SELECT * FROM students",
                ps -> {},
                rs -> {
                    List<Student> students = new ArrayList<>();
                    while (rs.next()) {
                        students.add(mapStudent(rs));
                    }
                    return students;
                }
        );
    }

    @Override
    public Student findByEmail(String email) {
        return DbUtils.execQuery(
                "SELECT * FROM students WHERE email = ?",
                ps -> ps.setString(1, email),
                rs -> {
                    if (!rs.next()) return null;
                    return mapStudent(rs);
                }
        );
    }

    // ---------- helper ----------
    private Student mapStudent(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("course_year"),
                rs.getString("faculty")
        );
    }
}
