package campus.Main;

import campus.models.Course;
import campus.repository.jdbc.JdbcCourseRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JdbcCourseRepository courseRepo = new JdbcCourseRepository();

        System.out.println("=== Create Course ===");

        System.out.print("Course name: ");
        String name = sc.nextLine();

        System.out.print("Instructor name: ");
        String instructor = sc.nextLine();

        System.out.print("Credits: ");
        int credits = Integer.parseInt(sc.nextLine());

        System.out.print("Duration (weeks): ");
        int weeks = Integer.parseInt(sc.nextLine());

        System.out.print("Max capacity: ");
        int maxCap = Integer.parseInt(sc.nextLine());

        System.out.print("Allowed faculties (comma separated): ");
        List<String> faculties =
                List.of(sc.nextLine().split(","));

        System.out.print("Schedule day (1=Mon .. 7=Sun): ");
        int day = Integer.parseInt(sc.nextLine());

        System.out.print("Start time (HH:mm): ");
        LocalTime start = LocalTime.parse(sc.nextLine());

        System.out.print("End time (HH:mm): ");
        LocalTime end = LocalTime.parse(sc.nextLine());

        Course course = new Course(
                0,
                name,
                instructor,
                credits,
                weeks,
                maxCap,
                faculties,
                start,
                end,
                day
        );

       courseRepo.create(course);

        System.out.println("Course inserted with ID: " + course.getId());
    }
}
