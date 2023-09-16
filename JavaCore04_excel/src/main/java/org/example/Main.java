package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String file = "/Users/nghialai/Desktop/TechLead/InternTraining/JavaCore04_excel/src/main/resources/BangCong3.xlsx";
        try {
            List<Employee> employeeList = getData(file);
            for (Employee employee : employeeList) {
                //Map<Integer, Double> dailyTotalMoney = new HashMap<>(); // Lưu tổng số tiền cho mỗi ngày

                System.out.println("Nhân viên - " + employee.getName());
                System.out.println("DS các ngày làm việc : ");
                for (WorkDay workDay : employee.getWorkDays()) {
                    System.out.println("Ngày : " + workDay.getDate());
                    System.out.println("Giờ : " + workDay.getHours());
                    System.out.println("Ca : " + workDay.getShifts());
                    System.out.printf("Tổng tiền của ca làm việc: %.2f \n ------ \n", workDay.getMoney());
                }

                Map<String, Double> dailyTotalMoney = getDailyTotalMoney(employee);
                System.out.println("===== Tổng tiền công mỗi ngày =====");
                for (Map.Entry<String, Double> entry : dailyTotalMoney.entrySet()) {
                    System.out.printf("Ngày %s : %.2f \n" , entry.getKey() , entry.getValue());
                }

                System.out.println("============================");
                System.out.println("- Tổng tiền công tính được : " + employee.getTotalsMoney());
                System.out.println("- Tổng tiền công trong file exel : " + employee.getCompareTotal());
                System.out.println("- So sánh tiền công tính được với cột tổng lương trong file excel : " +
                        (compareTotalMoney(employee.getCompareTotal(), employee.getTotalsMoney()) ? "Trùng khớp" : "Không khớp"));
                System.out.println("============================");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Không tìm thấy file!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

//    public static Map<String, String> getShiftsPerDay(Employee employee) {
//        Map<String, List<String>> shiftsPerDay = new HashMap<>();
//        List<WorkDay> workDayList = employee.getWorkDays();
//        for (WorkDay workDay : workDayList) {
//            String date = workDay.getDate();
//            String shiftName = workDay.getShifts().get(0);
//            List<String> value = shiftsPerDay.get(date).stream().toList();
//            if (shiftsPerDay.containsKey(date)) {
//                shiftsPerDay.put(date, value.add(shiftName));
//            } else {
//                dailyTotalMoney.put(date, money);
//            }
//        }
//        return shiftsPerDay;
//    }

    public static Map<String, Double> getDailyTotalMoney(Employee employee) {
        Map<String, Double> dailyTotalMoney = new HashMap<>();
        List<WorkDay> workDayList = employee.getWorkDays();
        for (WorkDay workDay : workDayList) {
            String date = workDay.getDate();
            double money = workDay.getMoney();
            // Tính tổng số tiền cho mỗi ngày
            if (dailyTotalMoney.containsKey(date)) {
                dailyTotalMoney.put(date, dailyTotalMoney.get(date) + money);
            } else {
                dailyTotalMoney.put(date, money);
            }
        }
        return dailyTotalMoney;
    }

    public static boolean compareTotalMoney(double num1, double num2 ) {
        return Math.abs(num1 - num2) < 0.001;
    }

    public static List<Employee> getData(String file) throws Exception {
        List<Employee> employees = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(new File(file));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("Chấm công");
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
                        map3.put(shift, i); // lưu tên ca và cột i chứa value công của ca đó VD: { GC : 6(là index cell chứa value lương)}
                    }
                    shifts.clear();
                } else {
                    String shift = cell.getStringCellValue();
                    shifts.add(shift);
                }
            }
        }

        // Xử lý thông tin của từng nhân viên ver1
        for (int i = 6; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(0).getNumericCellValue() != 0 && row.getCell(0).getCellType() != CellType.BLANK) {
                String name = row.getCell(2).getStringCellValue();
                double compareTotal = row.getCell(index - 1).getNumericCellValue();
                Employee employee = new Employee(name, compareTotal, new ArrayList<>(), 0);

                for (int j = index; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                        double hours = cell.getNumericCellValue();
                        double amount = 0.0;
                        List<String> dayShifts = new ArrayList<>();

                        if (hours > 0) {
                            String date = String.valueOf(map1.get(j));
                            String shift = map2.get(j); // lấy ra tên ca (idShift ở map2) theo key là index j
                            dayShifts.add(shift);

                            if (map3.containsKey(shift)) {
                                int indexMoney = map3.get(shift); // lấy ra cell chứa thông tin lương của 1 ca
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
        }

        workbook.close();
        fileInputStream.close();

        return employees;
    }
}
