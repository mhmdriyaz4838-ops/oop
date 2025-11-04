package com.Day4;
class Engine {
    void start() {
        System.out.println("Engine Started");
    }

    void stop() {
        System.out.println("Engine Stopped");
    }
}

class Car {
    Engine engine = new Engine();

    void startCar() {
        engine.start();
        System.out.println("Car Started");
    }

    void stopCar() {
        System.out.println("Car Stopping...");
        engine.stop();
        System.out.println("Car Stopped");
    }
}

public class Composition {
    public static void main(String[] args) {
        Car car = new Car();
        car.startCar();
        System.out.println("Car is running...");
        car.stopCar();
    }
}

