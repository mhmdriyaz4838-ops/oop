package com.Day3;

class Student{
    String name;
    int rno;
    String dept;
    static String college;
    public void display(){
        System.out.println("Name: "+name+"\nRno: "+rno+"\nDept: "+dept+"\nCollege Name: "+college);
    }
}
public class ClassDemo {
    public static void main(String[] args) {
        Student s1=new Student();
        s1.name="Riyaz";
        s1.rno=999;
        s1.dept="MBBS";
        Student.college="SChool";
        s1.display();
        System.out.println("----------------------");
        Student s2=new Student();
        s2.name="Farhan";
        s2.rno=420;
        s2.dept="BA.Tamil";
        s2.display();

    }
}
