package com.techlead.javaspring.javacore04;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String file = "/Users/nghialai/Desktop/TechLead/InternTraining/JavaCore04_excel/src/main/resources/BangCong.xlsx";
        try {
            List<Employee> employeeList = workDayAnalysis(file);
            for (Employee employee : employeeList) {
                Map<Integer, Double> dailyTotalMoney = new HashMap<>(); // Lưu tổng số tiền cho mỗi ngày

                System.out.println("Nhan vien : " + employee.getName());
                System.out.println("Ngay lam viec : ");
                for (WorkDay workDay : employee.getWorkDays()) {
                    int date = workDay.getDate();
                    double money = workDay.getMoney();

                    System.out.println("Ngay : " + workDay.getDate());
                    System.out.println("Gio : " + workDay.getHours());
                    System.out.println("Ca : " + workDay.getShifts());
                    System.out.printf("Tong tien ca lam viec : %.2f \n", workDay.getMoney());
                    // Tính tổng số tiền cho mỗi ngày
                    if (dailyTotalMoney.containsKey(date)) {
                        dailyTotalMoney.put(date, dailyTotalMoney.get(date) + money);
                    } else {
                        dailyTotalMoney.put(date, money);
                    }

                }

                System.out.println("Tong tien moi ngay:");
                for (Map.Entry<Integer, Double> entry : dailyTotalMoney.entrySet()) {
                    System.out.println("Ngay " + entry.getKey() + ": " + entry.getValue());
                }


                System.out.println("-------------------");
                System.out.println("Tong tien cac ngay : " + employee.getTotalsMoney());
                System.out.println("So sanh : " + employee.getCompareTotal());
                System.out.println("So sanh voi so tong : " + compareDouble(employee.getCompareTotal(), employee.getTotalsMoney(), 0.01));
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean compareDouble(double num1, double num2, double epsilon) {
        return Math.abs(num1 - num2) < epsilon;
    }

    public static List<Employee> workDayAnalysis(String file) throws IOException {
        List<Employee> employees = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(new File(file));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0); // Trang tính đầu tiên trong tệp Excel
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();
        Map<String, Integer> map3 = new HashMap<>();

        boolean firstDate = true;
        int index = 0;
        // Lấy thông tin về ngày công
        Row row3 = sheet.getRow(3);
        int day = 0;
        for (int i = 0; i <= row3.getLastCellNum(); i++) {
            Cell cell = row3.getCell(i);
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                day = (int) cell.getNumericCellValue();
                if (firstDate) {
                    index = i;
                    firstDate = false;
                }
            }
            map1.put(i, day);
        }

        // Lấy thông tin về ca làm việc
        Row row5 = sheet.getRow(5);
        String idShift = "";
        for (int i = index; i < row5.getLastCellNum(); i++) {
            Cell cell = row5.getCell(i);
            if (cell != null && cell.getCellType() == CellType.STRING) {
                idShift = cell.getStringCellValue();
            }
            map2.put(i, idShift);
        }

        // Lấy thông tin về mức lương tương ứng với từng ca làm việc
        List<String> shifts = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            Cell cell = row5.getCell(i);
            if (cell != null && cell.getCellType() == CellType.STRING) {
                if (cell.getStringCellValue().equals("$")) {
                    new ArrayList<>(shifts);
                    for (String shift : shifts) {
                        map3.put(shift, i);
                    }
                    shifts.clear();
                } else {
                    String shift = cell.getStringCellValue();
                    shifts.add(shift);
                }
            }
        }

        // Xử lý thông tin của từng nhân viên
        for (int i = 6; i <= 9; i++) {
            Row row = sheet.getRow(i);
            String name = row.getCell(2).getStringCellValue();
            double compareTotal = row.getCell(16).getNumericCellValue();
            Employee employee = new Employee(name, compareTotal, new ArrayList<>(), 0);

            for (int j = index; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    double hours = cell.getNumericCellValue();
                    double amount = 0.0;
                    List<String> dayShifts = new ArrayList<>();

                    if (hours > 0) {
                        int date = map1.get(j);
                        String shift = map2.get(j);
                        dayShifts.add(shift);

                        if (map3.containsKey(shift)) {
                            int indexMoney = map3.get(shift);
                            double money = row.getCell(indexMoney).getNumericCellValue();
                            amount += hours * money;
                        }

                        WorkDay workDay = new WorkDay(date, hours, dayShifts, amount);
                        employee.addWorkDay(workDay);
                    }
                }
            }
            employees.add(employee);
        }

        workbook.close();
        fileInputStream.close();

        return employees;
    }
}
