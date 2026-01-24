package campus.models;

import java.time.LocalTime;
import java.util.List;

public class Course {
    private int id;
    private String name;
    private String instructorName;
    private int credits;
    private int durationWeeks;
    private int maxCapacity;
    private List<String> allowedFaculties;
    private LocalTime scheduleStart;
    private LocalTime scheduleEnd;
    private int scheduleDay;

    public Course(int id, String name,
                  String instructorName, int credits,
                  int durationWeeks,int maxCapacity,
                  List<String> allowedFaculties, LocalTime scheduleStart,
                  LocalTime scheduleEnd, int scheduleDay){
        setId(id);
        setName(name);
        setInstructorName(instructorName);
        setCredits(credits);
        setDurationWeeks(durationWeeks);
        setMaxCapacity(maxCapacity);
        setAllowedFaculties(allowedFaculties);
        setScheduleStart(scheduleStart);
        setScheduleEnd(scheduleEnd);
        setScheduleDay(scheduleDay);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getInstructorName() { return instructorName; }
    public int getCredits() { return credits; }
    public int getDurationWeeks() { return durationWeeks; }
    public int getMaxCapacity() { return maxCapacity; }
    public List<String> getAllowedFaculties() { return allowedFaculties; }
    public LocalTime getScheduleStart() {return scheduleStart; }
    public LocalTime getScheduleEnd() {return scheduleEnd; }
    public int getScheduleDay() {return scheduleDay; }


    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }

    public void setCredits(int credits) { this.credits = credits; }

    public void setDurationWeeks(int durationWeeks) { this.durationWeeks = durationWeeks; }

    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public void setAllowedFaculties(List<String> allowedFaculties) { this.allowedFaculties = allowedFaculties; }

    public void setScheduleStart(LocalTime scheduleStart) { this.scheduleStart = scheduleStart; }

    public void setScheduleEnd(LocalTime scheduleEnd) { this.scheduleEnd = scheduleEnd; }

    public void setScheduleDay(int scheduleDay) { this.scheduleDay = scheduleDay; }
}


