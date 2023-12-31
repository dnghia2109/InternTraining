package com.techlead.javaspring.javacore04;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name; //! ten
    private double compareTotal; //! so sanh
    private List<WorkDay> workDays = new ArrayList<>(); //! danh sach ngay cong
    private double totalsMoney; //! tong so tien
//    private double moneyPerDay;

    public Employee() {
    }

    public Employee(String name, double compareTotal, List<WorkDay> workDays, double totalsMoney) {
        this.name = name;
        this.compareTotal = compareTotal;
        this.workDays = workDays;
        this.totalsMoney = totalsMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCompareTotal() {
        return compareTotal;
    }

    public void setCompareTotal(double compareTotal) {
        this.compareTotal = compareTotal;
    }

    public List<WorkDay> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<WorkDay> workDays) {
        this.workDays = workDays;
    }

    public double getTotalsMoney() {
        return totalsMoney;
    }

    public void setTotalsMoney(double totalsMoney) {
        this.totalsMoney = totalsMoney;
    }

    public void addWorkDay(WorkDay workDay) {
        this.workDays.add(workDay);
        totalsMoney += workDay.getMoney();

    }

//    public double getMoneyPerDay() {
//        return moneyPerDay;
//    }
//
//    public void setMoneyPerDay(double moneyPerDay) {
//        this.moneyPerDay = moneyPerDay;
//    }
//
//    public double totalPerDay(List<WorkDay> workDays) {
//        for (int i = 0; i < workDays.size(); i++) {
//            this.setMoneyPerDay(this.getMoneyPerDay() + workDays.get(i).getMoney());
//            for (int j = 0; j < workDays.size(); j++) {
//                if (workDays.get(i).getDate() == workDays.get(j).getDate()) {
//                    this.setMoneyPerDay(this.getMoneyPerDay() + workDays.get(j).getMoney());
//                }
//            }
//
//        }
//        return this.getMoneyPerDay();
//    }
}
