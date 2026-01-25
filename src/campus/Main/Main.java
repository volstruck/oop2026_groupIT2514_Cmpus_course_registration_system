package campus.Main;

import campus.models.Course;
import campus.models.Student;
import campus.repository.CourseRepository;
import campus.repository.StudentRepository;
import campus.repository.jdbc.JdbcCourseRepository;
import campus.repository.jdbc.JdbcStudentRepository;
import campus.service.CourseService;
import campus.service.StudentService;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        CourseService courseService =
                new CourseService(new JdbcCourseRepository());
        StudentService studentService =
                new StudentService(new JdbcStudentRepository());

        while (true) {
            System.out.println("\n=== CAMPUS SYSTEM ===");
            System.out.println("1. Create course");
            System.out.println("2. List courses");
            System.out.println("3. Create student");
            System.out.println("4. List students");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> createCourse(courseService);
                case 2 -> listCourses(courseService);
                case 3 -> createStudent(studentService);
                case 4 -> listStudents(studentService);
                case 0 -> {
                    System.out.println("Bye.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // ---------- COURSE UI ----------

    private static void createCourse(CourseService service) {
        System.out.print("Course name: ");
        String name = sc.nextLine();

        System.out.print("Instructor: ");
        String instructor = sc.nextLine();

        System.out.print("Credits: ");
        int credits = Integer.parseInt(sc.nextLine());

        System.out.print("Weeks: ");
        int weeks = Integer.parseInt(sc.nextLine());

        System.out.print("Max capacity: ");
        int cap = Integer.parseInt(sc.nextLine());

        System.out.print("Faculties (comma separated): ");
        List<String> faculties = List.of(sc.nextLine().split(","));

        System.out.print("Day (1–7): ");
        int day = Integer.parseInt(sc.nextLine());

        System.out.print("Start time (HH:mm): ");
        LocalTime start = LocalTime.parse(sc.nextLine());

        System.out.print("End time (HH:mm): ");
        LocalTime end = LocalTime.parse(sc.nextLine());

        Course c = new Course(
                0, name, instructor, credits, weeks,
                cap, faculties, start, end, day
        );

        service.createCourse(c);
        System.out.println("Course created.");
    }

    private static void listCourses(CourseService service) {
        List<Course> courses = service.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses.");
            return;
        }
        for (Course c : courses) {
            System.out.printf(
                    "%d | %s | %s | %s %s-%s%n",
                    c.getId(),
                    c.getName(),
                    c.getInstructorName(),
                    c.getScheduleDay(),
                    c.getScheduleStart(),
                    c.getScheduleEnd()
            );
        }
    }

    // ---------- STUDENT UI ----------

    private static void createStudent(StudentService service) {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Surname: ");
        String surname = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Course year: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Faculty: ");
        String faculty = sc.nextLine();

        Student s = new Student(
                0, name, surname, email, password, year, faculty
        );

        service.createStudent(s);
        System.out.println("Student created.");
    }

    private static void listStudents(StudentService service) {
        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        for (Student s : students) {
            System.out.printf(
                    "%d | %s %s | %s | %s%n",
                    s.getId(),
                    s.getName(),
                    s.getSurname(),
                    s.getEmail(),
                    s.getFaculty()
            );
        }
    }
}
