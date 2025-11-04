package com.Day3;

class Shape{
    int length;
    int width;
    int radius;
    public double areaOfRectangle(){
        int area=(length*width);
        return area;
    }
    public double areaOfCircle(){
        double pi=3.147;
        double area=2*pi*(radius*radius);
        return area;
    }
}
public class VariablesDemo {
    public static void main(String[] args) {
        Shape rectangle=new Shape();
        rectangle.length=77;
        rectangle.width=58;
        System.out.println(rectangle.areaOfRectangle());
        Shape circle=new Shape();
        circle.radius=9;
        System.out.println(circle.areaOfCircle());
    }
}