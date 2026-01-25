package campus.repository.jdbc;

import campus.models.Course;
import campus.repository.CourseRepository;
import edu.aitu.oop3.db.DbUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseRepository implements CourseRepository {

    public void create(Course c){
        DbUtils.execQuery("INSERT INTO courses (" +
                        "  name, instructor_name, credits, weeks, max_cap," +
                        "  allowed_faculties, schedule_start, schedule_end, schedule_day" +
                        ")" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                        "RETURNING id",

                ps -> {
                    ps.setString(1, c.getName());
                    ps.setString(2, c.getInstructorName());
                    ps.setInt(3, c.getCredits());
                    ps.setInt(4, c.getDurationWeeks());
                    ps.setInt(5, c.getMaxCapacity());
                    ps.setArray(6, ps.getConnection()
                            .createArrayOf("text", c.getAllowedFaculties().toArray()));
                    ps.setTime(7, Time.valueOf(c.getScheduleStart()));
                    ps.setTime(8, Time.valueOf(c.getScheduleEnd()));
                    ps.setInt(9, c.getScheduleDay());
                },

                rs -> {
                    return null;
                });
    }

    public void delete(int id){
        DbUtils.execUpdate("DELETE FROM courses WHERE id = ?",
                ps -> {
            ps.setInt(1, id);
                });
    }

    @Override
    public Course findById(int id) {
        return DbUtils.execQuery(
                "SELECT * FROM courses WHERE id = ?",
                ps -> ps.setInt(1, id),
                rs -> {
                    if (!rs.next()) return null;
                    return new Course(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("instructor_name"),
                            rs.getInt("credits"),
                            rs.getInt("weeks"),
                            rs.getInt("max_cap"),
                            List.of((String[]) rs.getArray("allowed_faculties").getArray()),
                            rs.getTime("schedule_start").toLocalTime(),
                            rs.getTime("schedule_end").toLocalTime(),
                            rs.getInt("schedule_day")
                    );
                }
        );
    }

    @Override
    public List<Course> findAll() {
        return DbUtils.execQuery(
                "SELECT * FROM courses",
                ps -> {}, // no parameters
                rs -> {
                    List<Course> courses = new ArrayList<>();

                    while (rs.next()) {
                        courses.add(new Course(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("instructor_name"),
                                rs.getInt("credits"),
                                rs.getInt("weeks"),
                                rs.getInt("max_cap"),
                                List.of((String[]) rs.getArray("allowed_faculties").getArray()),
                                rs.getTime("schedule_start").toLocalTime(),
                                rs.getTime("schedule_end").toLocalTime(),
                                rs.getInt("schedule_day")
                        ));
                    }
                    return courses;
                }
        );
    }

}
