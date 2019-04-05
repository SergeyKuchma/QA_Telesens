//Тестирование считывания данных из файла XLS

package com.academy.automationpractice;

import com.academy.automationpractice.ex.TestScenario;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReadFromXLS {
    private static String automationpracticeProperties = "D:\\Java\\Program\\javapart\\src\\main\\resources\\automationpractice.properties";
    private static String pathExcData;//="d:/Java/Program/javapart/data/automationpractice-auth-data.xlsx";

    // ----------------------------------  читаем из excel   ----------------------------------------
    public static void main(String[] args) {

        pathExcData=getPathExcData();

        TestScenario testscenario = new TestScenario();

        //List для наполнения из xls
        List<TestScenario> testscenarioList= new ArrayList<>();


        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(pathExcData))) {
            XSSFSheet sheet = workbook.getSheet("Лист1");
            for (int r=1; r<=sheet.getLastRowNum(); r++) {
                testscenario = new TestScenario();

                XSSFRow row = sheet.getRow(r); //r будет приходящий индекс на вариант ТС

                testscenario.setLogin((row.getCell(0) == null) ? "": row.getCell(0).getStringCellValue());
                testscenario.setPassword((row.getCell(1) == null) ? "": row.getCell(1).getStringCellValue());
                testscenario.setExpectedMessage((row.getCell(2) == null) ? "": row.getCell(2).getStringCellValue());
                testscenario.setTypeScenario((row.getCell(3) == null) ? "": row.getCell(3).getStringCellValue());

                /*
                if (row.getCell(0)==null) testscenario.setLogin("");
                else testscenario.setLogin(row.getCell(0).getStringCellValue());

                if (row.getCell(1)==null) testscenario.setPassword("");
                else testscenario.setPassword(row.getCell(1).getStringCellValue());

                if (row.getCell(2)==null) testscenario.setExpectedMessage("");
                else testscenario.setExpectedMessage(row.getCell(2).getStringCellValue());

                if (row.getCell(3)==null) testscenario.setTypeScenario("");
                else testscenario.setTypeScenario(row.getCell(3).getStringCellValue());
*/
                testscenarioList.add(testscenario);// запись в List
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Считанные данные из XLS в виде List:");
        System.out.println(testscenarioList);

    }

    // считывание пути из файла
    private static String getPathExcData(){
        // Cчитывание пути к файлу конфигурации: 'automationpractice.common', где находится путь к xls
        String commonProperties = System.getProperty("automationpractice.cfg"); //automationpractice.cfg
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(commonProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("automation.auth.data.exc");
    }
}
