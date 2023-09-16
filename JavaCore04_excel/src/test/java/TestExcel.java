import org.example.Employee;
import org.example.Main;
import org.example.MainV2;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class TestExcel {
    private final static String PATH_NOT_FOUND = "src/main/resources/1/BangCong.xlsx";
    private final static String PATH_EXCEL_02 = "src/main/resources/BangCong.xlsx";
    private final static String PATH_EXCEL_03 = "src/main/resources/BangCong3.xlsx";

    @Test
    public void testWithInputFilePathNotFound() {
        assertThrows(FileNotFoundException.class, () -> {
            List<Employee> result = Main.getData(PATH_NOT_FOUND);
        });

        assertThrows(FileNotFoundException.class, () -> {
            List<Employee> result = MainV2.getData(PATH_NOT_FOUND);
        });
    }

    @Test
    public void testWithNormalFileFromDay1ToDay31() throws Exception {
        List<Employee> result = Main.getData(PATH_EXCEL_02);
        Employee employee1 = result.get(0);
        Employee employee2 = result.get(1);
        Employee employee3 = result.get(2);
        Employee employee4 = result.get(3);
        Employee employee5 = result.get(4);

        Assert.assertEquals(5, result.size());

        // Check name
        Assert.assertEquals("Nguyen Van A", employee1.getName());
        Assert.assertEquals("Nguyen Van B", employee2.getName());
        Assert.assertEquals("Nguyen Van C", employee3.getName());
        Assert.assertEquals("Nguyen Van D", employee4.getName());
        Assert.assertEquals("Nguyen Van E", employee5.getName());

        // Compare totalMoney vs CompareTotal
        Assert.assertTrue(Main.compareTotalMoney(employee1.getCompareTotal(), employee1.getTotalsMoney()));

    }

    @Test
    public void testWithNormalFileFromDay10ThisMonthToDay9NextMonth() throws Exception {
        List<Employee> result = Main.getData(PATH_EXCEL_03);
        Assert.assertEquals(4, result.size());
        Assert.assertEquals( result.get(0).getCompareTotal() , result.get(0).getTotalsMoney(), 0.001);

        // ==> Output của code chưa ra đúng với yc của đề: Chưa hiển thị được danh sách ca theo ngày
    }

    @Test
    public void testWithNormalFileFromDay1ToDay31WithMainV2() throws Exception {
        List<Employee> result = MainV2.getData(PATH_EXCEL_02);
        Employee employee1 = result.get(0);
        Employee employee2 = result.get(1);
        Employee employee3 = result.get(2);
        Employee employee4 = result.get(3);
        Employee employee5 = result.get(4);

        Assert.assertEquals(5, result.size());

        // Check name
        Assert.assertEquals("Nguyen Van A", employee1.getName());
        Assert.assertEquals("Nguyen Van B", employee2.getName());
        Assert.assertEquals("Nguyen Van C", employee3.getName());
        Assert.assertEquals("Nguyen Van D", employee4.getName());
        Assert.assertEquals("Nguyen Van E", employee5.getName());

        // Compare totalMoney vs CompareTotal
        Assert.assertTrue(MainV2.compareTotalMoney(employee1.getCompareTotal(), employee1.getTotalsMoney()));

        // Get list shifts per day
        Assert.assertEquals(new ArrayList<>(List.of("GC", "GC1")), employee1.getWorkDays().get(0).getShifts());
        Assert.assertEquals(new ArrayList<>(List.of("GC")), employee2.getWorkDays().get(3).getShifts());

        // Get workDay date: format date/month/year
        Assert.assertEquals("31/12/2021", employee1.getWorkDays().get(12).getDate());
        Assert.assertEquals("1/12/2021", employee2.getWorkDays().get(0).getDate());
        Assert.assertEquals("4/12/2021", employee2.getWorkDays().get(3).getDate());
    }

    @Test
    public void testWithNormalFileFromDay10ThisMonthToDay9NextMonthWithMainV2() throws Exception {
        List<Employee> result = MainV2.getData(PATH_EXCEL_03);
        Employee employee1 = result.get(0);
        Employee employee2 = result.get(1);
        // Get workDay date: format date/month/year
        Assert.assertEquals("1/1/2022", employee1.getWorkDays().get(14).getDate());
    }
}
