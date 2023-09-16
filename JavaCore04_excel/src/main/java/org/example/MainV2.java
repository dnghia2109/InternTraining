package org.example;

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

public class MainV2 {

    public static void main(String[] args) throws FileNotFoundException {
        String file = "/Users/nghialai/Desktop/TechLead/InternTraining/JavaCore04_excel/src/main/resources/BangCong.xlsx";
        try {
            List<Employee> employees = getData(file);
            if (employees.isEmpty()) {
                throw new Exception("Không tìm thấy nhân viên trong file!");
            }

            for (Employee employee : employees) {
                System.out.println("Nhân viên - " + employee.getName());
                System.out.println("DS các ngày làm việc : ");
                for (WorkDay workDay : employee.getWorkDays()) {
                    System.out.println("Ngày : " + workDay.getDate());
                    System.out.println("\tTổng số giờ làm: " +  workDay.getHours());
                    System.out.println("\tDS các ca làm: " + workDay.getShifts());
                    System.out.printf("\tTổng tiền công ngày: %.3f \n ", workDay.getMoney());
                }
                System.out.println("------------------------");
                System.out.printf("- Tổng tiền công của tháng: %.3f \n", employee.getTotalsMoney());
                System.out.printf("- Tổng tiền công của tháng trong file excel: %.3f \n", employee.getCompareTotal());

                System.out.println("- So sánh tiền công tính được với cột tổng lương (Q) trong file excel: " +
                        (compareTotalMoney(employee.getCompareTotal(), employee.getTotalsMoney()) ? "Trùng khớp" : "Không khớp"));
                System.out.println("========================================================\n");
            }
        } catch (Exception e) {
            throw new FileNotFoundException("Không tìm thấy file!");
        }
    }

    public static List<Employee> getData(String filePath) throws Exception {
        List<Employee> employees = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, Integer> map1 = new HashMap<>(); // lưu ngày , index cột
        Map<Integer, String> map2 = new HashMap<>(); // lưu index cột, tên ca
        Map<String, Integer> map3 = new HashMap<>(); // lưu tên ca , index cột chỉ giá tiền của ca -> muốn lấy giá tiền của ca thì row.getCell(value)

        boolean isFirstDate = true;
        int index = 0; // đánh dấu số chỉ cột ngày đầu tiên trong tháng.
        // Lấy thông tin về ngày công
        Row row3 = sheet.getRow(3);
        int day = 0;
        for (int i = 0; i <= row3.getLastCellNum(); i++) {
            Cell cell = row3.getCell(i);
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                day = (int) cell.getNumericCellValue();
                if (isFirstDate) {
                    index = i;
                    isFirstDate = false;
                }
            }
            map1.put(i, day);
        }
        int firstDay = map1.get(index); // lấy ra ngày đầu tiên của tháng


        // Lấy thông tin về ca làm việc
        Row row5 = sheet.getRow(5);
        String idShift = "";
        for (int i = index; i <= row5.getLastCellNum(); i++) {
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
        for (int i = 6; i <= sheet.getLastRowNum(); i++) {
            Row rowMonthAndYear = sheet.getRow(1);
            int month = Integer.parseInt(( rowMonthAndYear.getCell(0).getStringCellValue().substring(6,8)));
            int year = Integer.parseInt(( rowMonthAndYear.getCell(0).getStringCellValue().substring(13,17)));
            System.out.println(year);
            Row row = sheet.getRow(i);

            if (row.getCell(0).getNumericCellValue() != 0) {
                String name = row.getCell(2).getStringCellValue();
                double compareTotal = row.getCell(index - 1).getNumericCellValue(); // cột tổng lương kế cột ngày đầu
                Employee employee = new Employee();
                employee.setName(name);
                employee.setCompareTotal(compareTotal);

                WorkDay workDay = null;
                int currentDate = -1;
                double totalHourPerDay = 0;
                double totalAmountPerDay = 0;
                List<String> dayShifts = new ArrayList<>(); // DS các ca làm trong ngày

                // Xử lý ngày tháng khi mà file có ngày bắt đầu là ngày của tháng này nhưng ngày kết thúc là sang tháng khác
                // VD: ngày bắt đầu 5/3/2021 -> ngày kthuc 6/4/2021
                boolean hasEncounteredFirstDay = false; // check xem đã duyệt được ngày đầu tiên của tháng mới hay chưa
                for (int j = index; j <= row.getLastCellNum(); j++) {
                    int indexDay = map1.get(j);

                    if (firstDay != 1) {
                        // check là ngày của tháng mới
                        if (indexDay == 1 && !hasEncounteredFirstDay) {
                            if (month == 12) {
                                month = 1;
                                year++;
                            } else {
                                month++;
                            }
                            hasEncounteredFirstDay = true; // đánh dấu đã duyệt được ngày đầu tiên của tháng mới
                        }
                    }

                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        // check ô đó có số giờ la việc của ca hay k
                        if (cell.getCellType() == CellType.NUMERIC && cell.getNumericCellValue() > 0.0) {
                            // Lấy ra ngày đang duyệt
                            int date = map1.get(j);

                            // Nếu duyệt sang ngày khác thì add wworkDay của ngaày trc cho employee rồi làm mới data
                            if (date != currentDate) {
                                if (workDay != null) {
                                    employee.addWorkDay(workDay);
                                }
                                totalHourPerDay = 0;
                                totalAmountPerDay = 0;
                                dayShifts = new ArrayList<>();
                                workDay = new WorkDay();
                            }

                            String shift = map2.get(j);
                            dayShifts.add(shift); // Thêm ca duyệt được vào list ca của ngày đó (date)
                            double hours = cell.getNumericCellValue();
                            totalHourPerDay += hours; // Update lại số giờ làm việc của ngày

                            if (map3.containsKey(shift)) {
                                int indexMoney = map3.get(shift);
                                double money = row.getCell(indexMoney).getNumericCellValue();
                                totalAmountPerDay += hours * money; //
                            }

                            workDay.setDate(date + "/" + month + "/" + year);
                            workDay.setHours(totalHourPerDay);
                            workDay.setMoney(totalAmountPerDay);
                            workDay.setShifts(dayShifts);

                            currentDate = date;
                        }
                    }
                }
                // Sau khi kết thúc vòng lặp, thêm workDay vào cho employee
                if (workDay != null) {
                    employee.addWorkDay(workDay);
                }
                employees.add(employee);
            }
        }
        workbook.close();
        fileInputStream.close();
        return employees;
    }

    public static boolean compareTotalMoney(double num1, double num2) {
        return Math.abs(num1 - num2) < 0.001; // cho sai số nhỏ hơn 0,001
    }
}
