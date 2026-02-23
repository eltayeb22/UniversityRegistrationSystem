/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universityregistrationsystem;



class Student {
    int studentID;
    EnrolledCoursesList enrolledCourses;
    Student next;
    
   
    public Student(int id) {
        this.studentID = id;
        this.enrolledCourses = new EnrolledCoursesList();
        this.next = null;
    }
}

class StudentNode {
    int studentID;
    StudentNode next;
    
   
    public StudentNode(int id) {
        this.studentID = id;
        this.next = null;
    }
}

class StudentList {
    Student head;
    Student lastAdded;
    
 
    public StudentList() {
        this.head = null;
        this.lastAdded = null;
    }
    
   
    public void addStudent(int id) {
        Student newStudent = new Student(id);
        
        
        if (head == null) {
            head = newStudent;
        } else {
          
            Student current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newStudent;
        }
        
        lastAdded = newStudent;
    }
    
   
    public Student findStudent(int id) {
        Student current = head;
        
        while (current != null) {
            if (current.studentID == id) {
                return current;
            }
            current = current.next;
        }
        
        return null;
    }
    
    
    public void removeStudent(int id) {
       
        if (head == null) {
            return;
        }
        
        
        if (head.studentID == id) {
            head = head.next;
            return;
        }
        
        
        Student current = head;
        while (current.next != null && current.next.studentID != id) {
            current = current.next;
        }
        
      
        if (current.next != null) {
            if (current.next == lastAdded) {
                lastAdded = current; 
            }
            current.next = current.next.next;
        }
    }
}
