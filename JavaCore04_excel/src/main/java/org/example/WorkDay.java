package org.example;

import java.util.List;


public class WorkDay {
    private String date; //! ngay làm việc ( vd: ngày 1, ngày 2,... )
    private double hours;   //! gio lam viec
    private List<String> shifts; //! danh sach ca lam viec
    private double totalMoney; //! so tien

    public WorkDay() {
    }

    public WorkDay(String date, double hours, List<String> shifts, double totalMoney) {
        this.date = date;
        this.hours = hours;
        this.shifts = shifts;
        this.totalMoney = totalMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
        return totalMoney;
    }

    public void setMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
