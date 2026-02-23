/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.universityregistrationsystem;

public class UniversityRegistrationSystem {

    private StudentList students;
    private CourseList courses;
    private UndoRedoManager undoRedoManager;

    
    public UniversityRegistrationSystem() {
        students = new StudentList();
        courses = new CourseList();
        undoRedoManager = new UndoRedoManager();
    }

    
    public boolean addStudent(int studentID) {
        if (students.findStudent(studentID) != null) {
            System.out.println("Student with ID " + studentID + " already exists!");
            return false;
        }
        students.addStudent(studentID);
        System.out.println("Student with ID " + studentID + " added successfully.");
        return true;
    }

    
    public boolean addCourse(int courseID) {
        if (courses.findCourse(courseID) != null) {
            System.out.println("Course with ID " + courseID + " already exists!");
            return false;
        }
        courses.addCourse(courseID);
        System.out.println("Course with ID " + courseID + " added successfully.");
        return true;
    }

    
    public boolean removeStudent(int studentID) {
        Student student = students.findStudent(studentID);
        if (student == null) {
            System.out.println("Student with ID " + studentID + " does not exist!");
            return false;
        }

        
        CourseNode courseNode = student.enrolledCourses.head;
        while (courseNode != null) {
            Course course = courses.findCourse(courseNode.courseID);
            if (course != null) {
                course.enrolledStudents.removeStudent(studentID);
            }
            courseNode = courseNode.next;
        }

        
        students.removeStudent(studentID);
        System.out.println("Student with ID " + studentID + " removed successfully.");
        return true;
    }

    
    public boolean removeCourse(int courseID) {
        Course course = courses.findCourse(courseID);
        if (course == null) {
            System.out.println("Course with ID " + courseID + " does not exist!");
            return false;
        }

        
        StudentNode studentNode = course.enrolledStudents.head;
        while (studentNode != null) {
            Student student = students.findStudent(studentNode.studentID);
            if (student != null) {
                student.enrolledCourses.removeCourse(courseID);
            }
            studentNode = studentNode.next;
        }

        
        courses.removeCourse(courseID);
        System.out.println("Course with ID " + courseID + " removed successfully.");
        return true;
    }

    
    public int getLastStudentAdded() {
        if (students.lastAdded == null) {
            System.out.println("No students have been added yet.");
            return -1;
        }
        return students.lastAdded.studentID;
    }

    
    public int getLastCourseAdded() {
        if (courses.lastAdded == null) {
            System.out.println("No courses have been added yet.");
            return -1;
        }
        return courses.lastAdded.courseID;
    }

    
    public boolean enrollStudent(int studentID, int courseID) {
        Student student = students.findStudent(studentID);
        Course course = courses.findCourse(courseID);

        if (student == null) {
            System.out.println("Student with ID " + studentID + " does not exist!");
            return false;
        }

        if (course == null) {
            System.out.println("Course with ID " + courseID + " does not exist!");
            return false;
        }

        
        if (student.enrolledCourses.containsCourse(courseID)) {
            System.out.println("Student " + studentID + " is already enrolled in course " + courseID);
            return false;
        }

        
        if (!isNormalStudent(studentID)) {
            System.out.println("Student " + studentID + " has reached the maximum number of courses (7)");
            return false;
        }

        
        if (isFullCourse(courseID)) {
            System.out.println("Course " + courseID + " is full (30 students)");
            return false;
        }

        
        student.enrolledCourses.addCourse(courseID);
        course.enrolledStudents.addStudent(studentID);
        
        undoRedoManager.addAction("enroll", studentID, courseID);

        System.out.println("Student " + studentID + " enrolled in course " + courseID + " successfully.");
        return true;
    }


    public boolean removeEnrollment(int studentID, int courseID) {
        Student student = students.findStudent(studentID);
        Course course = courses.findCourse(courseID);

        if (student == null) {
            System.out.println("Student with ID " + studentID + " does not exist!");
            return false;
        }

        if (course == null) {
            System.out.println("Course with ID " + courseID + " does not exist!");
            return false;
        }

        
        if (!student.enrolledCourses.containsCourse(courseID)) {
            System.out.println("Student " + studentID + " is not enrolled in course " + courseID);
            return false;
        }

        
        student.enrolledCourses.removeCourse(courseID);
        course.enrolledStudents.removeStudent(studentID);

        
        undoRedoManager.addAction("unenroll", studentID, courseID);

        System.out.println("Student " + studentID + " removed from course " + courseID + " successfully.");
        return true;
    }


    public void listCoursesByStudent(int studentID) {
        Student student = students.findStudent(studentID);

        if (student == null) {
            System.out.println("Student with ID " + studentID + " does not exist!");
            return;
        }

        System.out.println("Courses enrolled by Student " + studentID + ":");
        CourseNode current = student.enrolledCourses.head;

        if (current == null) {
            System.out.println("  No courses found.");
            return;
        }

        while (current != null) {
            System.out.println("  Course ID: " + current.courseID);
            current = current.next;
        }
    }


    public void listStudentsByCourse(int courseID) {
        Course course = courses.findCourse(courseID);

        if (course == null) {
            System.out.println("Course with ID " + courseID + " does not exist!");
            return;
        }

        System.out.println("Students enrolled in Course " + courseID + ":");
        StudentNode current = course.enrolledStudents.head;

        if (current == null) {
            System.out.println("  No students found.");
            return;
        }

        while (current != null) {
            System.out.println("  Student ID: " + current.studentID);
            current = current.next;
        }
    }


    public void sortStudentsByID(int courseID) {
        Course course = courses.findCourse(courseID);

        if (course == null) {
            System.out.println("Course with ID " + courseID + " does not exist!");
            return;
        }

       

        StudentNode current = course.enrolledStudents.head;
        if (current == null) {
            System.out.println("  No students found.");
            return;
        }

        while (current != null) {
            System.out.println("  Student ID: " + current.studentID);
            current = current.next;
        }
    }


    public void sortCoursesByID(int studentID) {
        Student student = students.findStudent(studentID);

        if (student == null) {
            System.out.println("Student with ID " + studentID + " does not exist!");
            return;
        }

       

        CourseNode current = student.enrolledCourses.head;
        if (current == null) {
            System.out.println("  No courses found.");
            return;
        }

        while (current != null) {
            System.out.println("  Course ID: " + current.courseID);
            current = current.next;
        }
    }


    public boolean isFullCourse(int courseID) {
        Course course = courses.findCourse(courseID);

        if (course == null) {
            System.out.println("Course with ID " + courseID + " does not exist!");
            return false;
        }

        int count = course.enrolledStudents.countStudents();
        return count >= 30;
    }


    public boolean isNormalStudent(int studentID) {
        Student student = students.findStudent(studentID);

        if (student == null) {
            System.out.println("Student with ID " + studentID + " does not exist!");
            return false;
        }

        int count = student.enrolledCourses.countCourses();
        return count < 7; 
    }


    public boolean undo() {
        return undoRedoManager.undo(this);
    }

    
    public boolean redo() {
        return undoRedoManager.redo(this);
    }

    
    protected boolean directEnroll(int studentID, int courseID) {
        Student student = students.findStudent(studentID);
        Course course = courses.findCourse(courseID);

        if (student == null || course == null) {
            return false;
        }

    
        student.enrolledCourses.addCourse(courseID);
        course.enrolledStudents.addStudent(studentID);

        return true;
    }

    
    protected boolean directUnenroll(int studentID, int courseID) {
        Student student = students.findStudent(studentID);
        Course course = courses.findCourse(courseID);

        if (student == null || course == null) {
            return false;
        }

    
        student.enrolledCourses.removeCourse(courseID);
        course.enrolledStudents.removeStudent(studentID);

        return true;
    }

    public static void main(String[] args) {
        UniversityRegistrationSystem system = new UniversityRegistrationSystem();

    
        system.addStudent(1001);
        system.addStudent(1002);
        system.addStudent(1003);

    
        system.addCourse(101);
        system.addCourse(102);
        system.addCourse(103);

    
        system.enrollStudent(1001, 101);
        system.enrollStudent(1001, 102);
        system.enrollStudent(1002, 101);
        system.enrollStudent(1003, 102);
        system.enrollStudent(1003, 103);

    
        system.listCoursesByStudent(1001);
        system.listStudentsByCourse(101);

    

    
        System.out.println("\nTesting Undo/Redo functionality:");
        system.removeEnrollment(1001, 101);
        system.listCoursesByStudent(1001);

        System.out.println("\nAfter undo:");
        system.undo();
        system.listCoursesByStudent(1001);

        System.out.println("\nAfter redo:");
        system.redo();
        system.listCoursesByStudent(1001);
    }
}


class Action {

    String type; 
    int studentID;
    int courseID;

    
    public Action(String type, int studentID, int courseID) {
        this.type = type;
        this.studentID = studentID;
        this.courseID = courseID;
    }
}


class UndoRedoManager {

    private Stack undoStack;
    private Stack redoStack;

    
    public UndoRedoManager() {
        undoStack = new Stack();
        redoStack = new Stack();
    }

   
    public void addAction(String type, int studentID, int courseID) {
        Action action = new Action(type, studentID, courseID);
        undoStack.push(action);
        redoStack.clear();
    }

   
    public boolean undo(UniversityRegistrationSystem system) {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return false;
        }

        Action action = undoStack.pop();
        boolean success = false;

        if (action.type.equals("enroll")) {
            
            success = system.directUnenroll(action.studentID, action.courseID);
            if (success) {
                System.out.println("Undone: Student " + action.studentID + " enrollment from course " + action.courseID);
                redoStack.push(action);
            }
        } else if (action.type.equals("unenroll")) {
            
            success = system.directEnroll(action.studentID, action.courseID);
            if (success) {
                System.out.println("Undone: Student " + action.studentID + " removal from course " + action.courseID);
                redoStack.push(action);
            }
        }

        return success;
    }

    
    public boolean redo(UniversityRegistrationSystem system) {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return false;
        }

        Action action = redoStack.pop();
        boolean success = false;

        if (action.type.equals("enroll")) {
            success = system.directEnroll(action.studentID, action.courseID);
            if (success) {
                System.out.println("Redone: Student " + action.studentID + " enrollment to course " + action.courseID);
                undoStack.push(action);
            }
        } else if (action.type.equals("unenroll")) {
            success = system.directUnenroll(action.studentID, action.courseID);
            if (success) {
                System.out.println("Redone: Student " + action.studentID + " removal from course " + action.courseID);
                undoStack.push(action);
            }
        }

        return success;
    }
}


class Stack {

    private class Node {

        Action data;
        Node next;

        public Node(Action data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;


    public Stack() {
        this.top = null;
    }


    public void push(Action action) {
        Node newNode = new Node(action);
        newNode.next = top;
        top = newNode;
    }


    public Action pop() {
        if (isEmpty()) {
            return null;
        }

        Action data = top.data;
        top = top.next;
        return data;
    }

    
    public boolean isEmpty() {
        return top == null;
    }

    
    public void clear() {
        top = null;
    }
}
