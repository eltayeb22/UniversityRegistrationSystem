/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universityregistrationsystem;

class EnrolledCoursesList {
    CourseNode head;
    
    
    public EnrolledCoursesList() {
        this.head = null;
    }
    
   
    public void addCourse(int id) {
        CourseNode newCourse = new CourseNode(id);
        if (head == null) {
            head = newCourse;
        } else {
            
            CourseNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newCourse;
        }
    }

    public boolean containsCourse(int id) {
        CourseNode current = head;
        
        while (current != null) {
            if (current.courseID == id) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }

    public void removeCourse(int id) {
        if (head == null) {
            return;
        }
        if (head.courseID == id) {
            head = head.next;
            return;
        }

        CourseNode current = head;
        while (current.next != null && current.next.courseID != id) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }
    public int countCourses() {
        int count = 0;
        CourseNode current = head;
        
        while (current != null) {
            count++;
            current = current.next;
        }
        
        return count;
    }
}