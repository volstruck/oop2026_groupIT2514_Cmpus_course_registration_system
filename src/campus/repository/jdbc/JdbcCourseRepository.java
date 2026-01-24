package campus.repository.jdbc;

import campus.models.Course;
import edu.aitu.oop3.db.DbUtils;

import java.sql.Time;

public class JdbcCourseRepository {
//    public String findById(int id) {
//        String Course = DbUtils.execQuery("SELECT  FROM courses WHERE id = " + id,
//                rs -> {
//            rs.next();
//            String name =  rs.getString("name");
//
//            return Course( );
//                });
//
//        return course;
//
//    }

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


}
