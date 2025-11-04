package com.Day4;
class Student {
    private int rno;
    private String name;
    private int marks;

    public Student(int rno, String name, int marks) {
        this.rno = rno;
        this.name = name;
        this.marks = marks;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getRno() {
        return rno;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void display() {
        System.out.println("Roll No: " + rno + ", Name: " + name + ", Marks: " + marks);
    }
}

public class EncapsulationDemo {
    public static void main(String[] args) {

        Student s1 = new Student(101, "Jannath", 85);
        s1.display();

        s1.setMarks(90);
        System.out.println("After marks update:");
        s1.display();
    }
}

