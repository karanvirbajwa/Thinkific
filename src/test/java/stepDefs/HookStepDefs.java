package stepDefs;

import java.io.*;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utilities.SuiteLogger;

public class HookStepDefs {
    static String TITLE ;
    static String FIRSTNAME;
    static String LASTNAME;
    static String EMAIL;
    static String EXCELFILE = System.getProperty("user.dir")+"\\src\\main\\java\\dataProviders\\Instructor.xlsx";

    /**
     * Following hook method is used to create a temp excel file to store UI data
     * @throws IOException
     */
    @Before("@CreateExcel")
    public void creatExcelFile() throws IOException {
        SuiteLogger.getGlobal().info("Creating temp excel file");
        Workbook wb = new HSSFWorkbook();
        OutputStream fileOut = new FileOutputStream(EXCELFILE);
        wb.write(fileOut);
    }

    /**
     * Following hook method is used to write the UI data in the temp excel file
     * @throws Exception
     */
    @After("@WriteExcel")
    public void writeExcelFile() throws Exception
    {
        SuiteLogger.getGlobal().info("Writing the Instructor data from UI to excel");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Instrutor_Data");

        XSSFRow row;
        Map<String, Object[]> instructorData = new TreeMap<String, Object[]>();

        String arr[] = ThinkificStepDefs.getData();
        EMAIL = arr[0]; FIRSTNAME = arr[1]; LASTNAME = arr[2]; TITLE = arr[3];
        instructorData.put("1", new Object[] { EMAIL, FIRSTNAME, LASTNAME, TITLE });

        Set<String> keyid = instructorData.keySet();
        int rowid = 0;
        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = instructorData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        FileOutputStream out = new FileOutputStream(EXCELFILE);
        workbook.write(out);
        out.close();
    }

    /**
     * Following hook method is used to read the UI data stored in UI task
     * @throws IOException
     */
    @Before("@ReadExcel")
    public void FetchData() throws IOException {
        SuiteLogger.getGlobal().info("Reading the data from excel file for comparison with API response");
        FileInputStream fis = new FileInputStream(EXCELFILE);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Student Data");

        EMAIL = sheet.getRow(0).getCell(0).getStringCellValue();
        FIRSTNAME = sheet.getRow(0).getCell(1).getStringCellValue();
        LASTNAME = sheet.getRow(0).getCell(2).getStringCellValue();
        TITLE = sheet.getRow(0).getCell(3).getStringCellValue();
    }

    /**
     * Following method is used to delete excel after UI data verification with API response
     */
    @After("@DeleteExcel")
    public void DeleteExcel() {
        SuiteLogger.getGlobal().info("Deleting the temp excel file after verification with API");
        File fis = new File(EXCELFILE);
        fis.delete();
    }

    /**
     * Following method is used to return all data fields from excel
     * @return = return all instructor fields for verifications
     */
    public static String[] getExcelData(){
        return new String[]{EMAIL, FIRSTNAME, LASTNAME, TITLE};
    }
}

