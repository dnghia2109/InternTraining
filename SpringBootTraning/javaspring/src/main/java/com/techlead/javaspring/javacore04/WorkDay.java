package com.techlead.javaspring.javacore04;

import java.util.List;


public class WorkDay {
    private int date; //! ngay làm việc ( vd: ngày 1, ngày 2,... )
    private double hours;   //! gio lam viec
    private List<String> shifts; //! danh sach ca lam viec
    private double money; //! so tien



    public WorkDay(int date, double hours, List<String> shifts, double money) {
        this.date = date;
        this.hours = hours;
        this.shifts = shifts;
        this.money = money;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public List<String> getShifts() {
        return shifts;
    }

    public void setShifts(List<String> shifts) {
        this.shifts = shifts;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
