/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universityregistrationsystem;

class Course {

    int courseID;
    EnrolledStudentsList enrolledStudents;
    Course next;


    public Course(int id) {
        this.courseID = id;
        this.enrolledStudents = new EnrolledStudentsList();
        this.next = null;
    }
}

class CourseNode {
    int courseID;
    CourseNode next;
    
    // Constructor
    public CourseNode(int id) {
        this.courseID = id;
        this.next = null;
    }
}

public class CourseList {
    Course head;
    Course lastAdded;
    

    public CourseList() {
        this.head = null;
        this.lastAdded = null;
    }

    public void addCourse(int id) {
        Course newCourse = new Course(id);
        
        // If list is empty
        if (head == null) {
            head = newCourse;
        } else {
            // Add to the end of the list
            Course current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newCourse;
        }
        
        lastAdded = newCourse;
    }
    
    public Course findCourse(int id) {
        Course current = head;
        
        while (current != null) {
            if (current.courseID == id) {
                return current;
            }
            current = current.next;
        }
        
        return null;
    }

    public void removeCourse(int id) {
        // If list is empty
        if (head == null) {
            return;
        }
        
        // If head is the course to be removed
        if (head.courseID == id) {
            head = head.next;
            return;
        }
        
        // Find the course to be removed
        Course current = head;
        while (current.next != null && current.next.courseID != id) {
            current = current.next;
        }
        
        // If course found
        if (current.next != null) {
            if (current.next == lastAdded) {
                lastAdded = current; // Update lastAdded reference
            }
            current.next = current.next.next;
        }
    }
}