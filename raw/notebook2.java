/*

A college is developing an Online Course Management System.
The Course class has the following requirements:
1.	Course ID should never be changed after creation.
2.	College name should be common to all courses, rather than stored 
separately for every object.
3.	Every course should provide a method to calculate course fee, but 
the calculation differs for each type of course.
4.	A method that displays the course ID should be available without
creating a Course object.
5.	A course should not be allowed to be inherited further.

expected output:
College: 
Course ID: 
Course Name: 
Java Course Fee: */


// Solution
// Course is abstract because every course calculates fee differently.
// JavaCourse is final so no class can inherit it further.

class notebook2 {
    public static void main(String[] args) {
        JavaCourse java = new JavaCourse(101, "Core Java", 3);

        System.out.println("College: " + Course.collegeName);
        Course.displayCourseId(java.courseId);
        System.out.println("Course Name: " + java.courseName);
        System.out.println("Java Course Fee: " + java.calculateFee());
    }
}

abstract class Course {
    final int courseId; // cannot be changed after creation
    String courseName;
    static String collegeName = "ABC Engineering College"; // common for all courses

    Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    abstract double calculateFee();

    static void displayCourseId(int courseId) {
        System.out.println("Course ID: " + courseId);
    }
}

final class JavaCourse extends Course {
    int months;

    JavaCourse(int courseId, String courseName, int months) {
        super(courseId, courseName);
        this.months = months;
    }

    @Override
    double calculateFee() {
        return months * 5000;
    }
}
