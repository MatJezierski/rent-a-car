package matjez.rentacarapp.extras;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class XLSCreator<T> {

    private Class clazz;

    public XLSCreator(Class clazz) {
        this.clazz = clazz;
    }

    public void createFile(List<T> series, String path, String fileName) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, IOException {

        // tworzenie obiektu reprezentującego cały plik Excel na podstawie klas z Apache POI
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileName);

        //todo - dodać style arkusza

        List<String> columns = new ArrayList<>();

        for (Field field: clazz.getDeclaredFields()){
            columns.add(field.getName());
        }

        Row header = sheet.createRow(0);

        for (int i=0; i<columns.size(); i++){
            Cell cell = header.createCell(i);
            cell.setCellValue(columns.get(i));
            // todo - dodać style komórki
        }

        for (int i=0; i<series.size(); i++){
            HSSFRow row = sheet.createRow(i+1);

            for (int j=0; j<columns.size(); j++){
                HSSFCell cell = row.createCell(j);

                if(columns.get(j).startsWith("is")){
                    Method method = series.get(i)
                            .getClass()
                            .getMethod(columns.get(j));

                    Object result = method.invoke(series.get(i));
                    cell.setCellValue(String.valueOf(result));

                } else {
                    Method method = series.get(i)
                            .getClass()
                            .getMethod("get" + columns.get(j)
                                    .substring(0, 1)
                                    .toUpperCase() + columns.get(j)
                                    .substring(1));

                    Object result = method.invoke(series.get(i));
                    cell.setCellValue(String.valueOf(result));
                }
            }
        }

        // moduł do zapisywania kolejnych plików, bez nadpisywania poprzedniego

        long mills = System.currentTimeMillis();
        String file = path + fileName + "_" + mills + ".xls";

        workbook.write(new File(file));
        workbook.close();
    }
}
