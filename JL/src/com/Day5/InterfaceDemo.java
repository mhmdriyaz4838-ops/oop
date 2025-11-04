package com.Day5;
interface Payable {
    void generatePayslip();
}

interface Bonus {
    void calculateBonus();
}

class Contractor implements Payable, Bonus {
    double dailyRate = 1000;
    int daysWorked = 20;

    @Override
    public void generatePayslip() {
        double salary = dailyRate * daysWorked;
        System.out.println("Contractor salary: " + salary);
    }

    @Override
    public void calculateBonus() {
        System.out.println("Contractor bonus: ₹5000 (fixed)");
    }
}

class FullTimeEmployee implements Payable, Bonus {
    double monthlySalary = 50000;

    @Override
    public void generatePayslip() {
        System.out.println("Full-time employee salary: " + monthlySalary);
    }

    @Override
    public void calculateBonus() {
        double bonus = monthlySalary * 0.1;
        System.out.println("Full-time employee bonus: " + bonus);
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        Payable contractor = new Contractor();
        contractor.generatePayslip();
        ((Bonus) contractor).calculateBonus();

        System.out.println();

        Payable fte = new FullTimeEmployee();
        fte.generatePayslip();
        ((Bonus) fte).calculateBonus();
    }
}


