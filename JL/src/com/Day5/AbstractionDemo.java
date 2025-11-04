package com.Day5;
abstract class Shape {
    String color;
    abstract void draw();
}

class Rectangle extends Shape {
    Rectangle(String color) {
        this.color = color;
    }

    void draw() {
        System.out.println("Drawing a " + color + " Rectangle...");
    }
}

class Square extends Shape {
    Square(String color) {
        this.color = color;
    }

    void draw() {
        System.out.println("Drawing a " + color + " Square...");
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle("Red");
        rectangle.draw();

        Shape square = new Square("Blue");
        square.draw();
    }
}

