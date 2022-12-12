package guru.qa;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {
    ClassLoader cl = SelenideFilesTest.class.getClassLoader();




    @Test
    @DisplayName("Check dates from zip ")
    void zipHomeParsingTest() throws Exception {
        try (
                InputStream resources = cl.getResourceAsStream("TestFiles.zip");
                ZipInputStream zis = new ZipInputStream(resources)
        )
        {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();

                if (entryName.contains(".xlsx")){
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(5).getCell(0).getStringCellValue()).contains("test  yar");
                    assertThat(content.excel.getSheetAt(0).getRow(5).getCell(1).getStringCellValue()).contains("ylin@gmail.com");
                    assertThat(content.excel.getSheetAt(0).getRow(5).getCell(2).getStringCellValue()).contains("+79999999999");
                    assertThat(content.excel.getSheetAt(0).getRow(5).getCell(3).getStringCellValue()).contains("INVITED");
                }

                else if (entryName.contains(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Тестовый PDF файл");
                }
                else if (entryName.contains(".csv")) {
                    CSVReader reader = new CSVReader (new InputStreamReader(zis));
                    List<String[]> content =reader.readAll();
                    assertThat(content.get(1)[0]).contains("OU001");
                }
 }
 }
   }
}
