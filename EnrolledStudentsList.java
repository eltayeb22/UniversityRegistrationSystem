/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.universityregistrationsystem;


class EnrolledStudentsList {
    StudentNode head;
    
    public EnrolledStudentsList() {
        this.head = null;
    }
    public void addStudent(int id) {
        StudentNode newStudent = new StudentNode(id);
        
       
        if (head == null) {
            head = newStudent;
        } else {
          
            StudentNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newStudent;
        }
    }
    public boolean containsStudent(int id) {
        StudentNode current = head;
        
        while (current != null) {
            if (current.studentID == id) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    public void removeStudent(int id) {
        if (head == null) {
            return;
        }             
        if (head.studentID == id) {
            head = head.next;
            return;
        }
        
        StudentNode current = head;
        while (current.next != null && current.next.studentID != id) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }
    public int countStudents() {
        int count = 0;
        StudentNode current = head;
        
        while (current != null) {
            count++;
            current = current.next;
        }        
        return count;
    }
    

    
}