package campus.repository.jdbc;

import campus.models.Student;
import edu.aitu.oop3.db.DbUtils;

public class JdbcStudentRepository {
    public void create (Student s){
        DbUtils.execQuery("INSERT INTO students (" +
                "  name, surname, password" +
                "  email, course_year, faculty" +
                ")" +
                "VALUES (?, ?, ?, ?, ?, ?)" +
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
                return null;
                });
    }

    public void delete(int id){
        DbUtils.execUpdate("DELETE FROM students WHERE id = ?",
                ps -> {
                    ps.setInt(1, id);
                });
    }
}
