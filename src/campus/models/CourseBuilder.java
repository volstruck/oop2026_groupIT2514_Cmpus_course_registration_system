package campus.models;

import java.time.LocalTime;
import java.util.List;

public class CourseBuilder {

    private int id = 0;
    private String name;
    private String instructorName;
    private int credits;
    private int durationWeeks;
    private int maxCapacity;
    private List<String> allowedFaculties;
    private LocalTime scheduleStart;
    private LocalTime scheduleEnd;
    private int scheduleDay;

    public CourseBuilder id(int id) {
        this.id = id;
        return this;
    }

    public CourseBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder instructor(String instructorName) {
        this.instructorName = instructorName;
        return this;
    }

    public CourseBuilder credits(int credits) {
        this.credits = credits;
        return this;
    }

    public CourseBuilder weeks(int durationWeeks) {
        this.durationWeeks = durationWeeks;
        return this;
    }

    public CourseBuilder maxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        return this;
    }

    public CourseBuilder faculties(List<String> faculties) {
        this.allowedFaculties = faculties;
        return this;
    }

    public CourseBuilder schedule(LocalTime start, LocalTime end, int day) {
        this.scheduleStart = start;
        this.scheduleEnd = end;
        this.scheduleDay = day;
        return this;
    }

    public Course build() {
        return new Course(
                id,
                name,
                instructorName,
                credits,
                durationWeeks,
                maxCapacity,
                allowedFaculties,
                scheduleStart,
                scheduleEnd,
                scheduleDay
        );
    }
}
