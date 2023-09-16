package org.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private double compareTotal;
    private List<WorkDay> workDays = new ArrayList<>();
    private double totalMoney;

    public Employee(String name, double compareTotal) {
    }
//    private double moneyPerDay;

//    public Employee() {
//    }
//
//    public Employee(String name, double compareTotal, List<WorkDay> workDays, double totalMoney) {
//        this.name = name;
//        this.compareTotal = compareTotal;
//        this.workDays = workDays;
//        this.totalMoney = totalMoney;
//    }

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
        return totalMoney;
    }

    public void setTotalsMoney(double totalsMoney) {
        this.totalMoney = totalsMoney;
    }

    public void addWorkDay(WorkDay workDay) {
        this.workDays.add(workDay);
        totalMoney += workDay.getMoney();
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
