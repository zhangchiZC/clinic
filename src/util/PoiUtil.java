package util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import po.Department;
import po.Doctor;
import po.Medicine;
import po.Workday;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by ZC on 2019/6/27.
 */

/**
 * excel里的图片一定要放在框内  不然报错
 */
public class PoiUtil {
    public static List<Workday> paresWorkday(String xlsPath) throws IOException {
        List<Workday> workdays = new ArrayList<>();
        FileInputStream fileInputStream = null;
        File file = new File(xlsPath);
        fileInputStream = new FileInputStream(file);
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(3);//获取第四个sheet

        try {
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                Workday workday = new Workday();
                for (int columnsNum = 0; columnsNum < row.getPhysicalNumberOfCells(); columnsNum++) {
                    DataFormatter dataFormatter = new DataFormatter();
                    dataFormatter.addFormat("###########", null);
                    switch (columnsNum) {
                        case 0:
                            String doctor_id = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            workday.setDoctor_id(Integer.parseInt(doctor_id));
                            break;
                        case 1:
                            String id_department = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            workday.setId_department(Integer.parseInt(id_department));
                            break;
                        case 2:
                            String workTime = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            workday.setWorkTime(workTime);
                            break;
                        case 3:
                            String workDate = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            workday.setWorkDate(workDate);
                            break;
                    }
                }
                workdays.add(workday);
            }
        } catch (NullPointerException e) {
            System.out.println();
            e.printStackTrace();
        }

        return workdays;
    }

    public static List<Department> paresDepartment(String xlsPath) throws IOException {
        List<Department> departments = new ArrayList<>();
        FileInputStream fileInputStream = null;
        File file = new File(xlsPath);
        fileInputStream = new FileInputStream(file);
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(2);//获取第三个sheet

        try {
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                Department department = new Department();
                for (int columnsNum = 0; columnsNum < row.getPhysicalNumberOfCells(); columnsNum++) {
                    DataFormatter dataFormatter = new DataFormatter();
                    dataFormatter.addFormat("###########", null);
                    switch (columnsNum) {
                        case 0:
                            String department_id = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            department.setDepartment_id(Integer.parseInt(department_id));
                            break;
                        case 1:
                            String department_name = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            department.setDepartment_name(department_name);
                            break;
                    }
                }
                departments.add(department);
            }
        } catch (NullPointerException e) {
            System.out.println();
            e.printStackTrace();
        }
        return departments;
    }

    public static List<Medicine> paresMedicine(String xlsPath) throws IOException {
        List<Medicine> medicines = new ArrayList<>();
        FileInputStream fileInputStream = null;
        File file = new File(xlsPath);
        fileInputStream = new FileInputStream(file);
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(1);//获取第二个sheet

        try {
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                Medicine medicine = new Medicine();
                for (int columnsNum = 0; columnsNum < row.getPhysicalNumberOfCells(); columnsNum++) {
                    DataFormatter dataFormatter = new DataFormatter();
                    dataFormatter.addFormat("###########", null);
                    switch (columnsNum) {
                        case 0:
                            String medicine_id = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            medicine.setMedicine_id(Integer.parseInt(medicine_id));
                            break;
                        case 1:
                            String medicine_name = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            medicine.setMedicine_name(medicine_name);
                            break;
                        case 2:
                            String medicine_amount = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            medicine.setMedicine_amount(Integer.parseInt(medicine_amount));
                            break;
                        case 3:
                            String medicine_price = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            medicine.setMedicine_price(Double.parseDouble(medicine_price));
                            break;
                    }
                }

                medicines.add(medicine);

            }
        } catch (NullPointerException e) {
            System.out.println();
            e.printStackTrace();
        }
        return medicines;
    }


    public static List<Doctor> paresDoctor(String xlsPath) throws IOException {
        List<Doctor> doctors = new ArrayList<>();
        FileInputStream fileInputStream = null;
        File file = new File(xlsPath);
        fileInputStream = new FileInputStream(file);
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        HSSFSheet hssfSheet = (HSSFSheet) sheet;
        Map<String, PictureData> map = new LinkedHashMap<String, PictureData>();
        List<HSSFShape> list = hssfSheet.getDrawingPatriarch().getChildren();
        for (HSSFShape shape : list) {
            if (shape instanceof HSSFPicture) {
                HSSFPicture picture = (HSSFPicture) shape;
                HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
                PictureData pdata = picture.getPictureData();
                String key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
                System.out.println(key.toString());/////////
                map.put(key, pdata);

            }
        }
        Object key[] = map.keySet().toArray();
        for (int i=0;i<key.length;i++){
            System.out.println(key[i]);
        }
        System.out.println("key length="+key.length);
        System.out.println("map.size="+map.size());

        try {
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                System.out.println(sheet.getLastRowNum()+"hang");
                Row row = sheet.getRow(rowNum);
                Doctor doctor = new Doctor();
                for (int columnsNum = 0; columnsNum <= row.getPhysicalNumberOfCells(); columnsNum++) {////////////////////////<=
                    System.out.println(row.getPhysicalNumberOfCells()+"lie");
                    DataFormatter dataFormatter = new DataFormatter();
                    dataFormatter.addFormat("###########", null);
                    switch (columnsNum) {
                        case 0:
                            String doctor_id = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setDoctor_id(Integer.parseInt(doctor_id.trim()));
                            break;
                        case 1:
                            String department_id = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setDepartment_id(Integer.parseInt(department_id.trim()));
                            break;
                        case 2:
                            String name = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setName(name.trim());
                            break;
                        case 3:
                            String statue = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setStatue(Integer.parseInt(statue.trim()));
                            break;
                        case 4:
                            String degree = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setDegree(degree.trim());
                            break;
                        case 5:
                            String sex = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setSex(sex.trim());
                            break;
                        case 6:
                            String birthday = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setBirthday(birthday.trim());
                            break;
                        case 7:
                            String tel = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setTel(tel.trim());
                            break;
                        case 8:
                            String introduction = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setIntroduction(introduction.trim());
                            break;
                        case 9:
                           // System.out.println(map.size());
                            //for (int i = 0; i < map.size(); i++) {
                                // 获取图片流
                                PictureData pic = map.get(key[rowNum]);
                                // 获取图片索引
                                String picName = key[rowNum].toString();
                                // 获取图片格式
                                String ext = pic.suggestFileExtension();

                                byte[] data = pic.getData();

                                //图片保存路径
                                File file1 = new File("D:\\img\\pic" + picName + "." + ext);
                                //String photoPath = "image\\" + file1.getAbsolutePath().split("\\\\")[2];
                                // String d = "D:\\img\\pic" + picName + "." + ext;
                                //doctor.setPhotoPath(d);
                                System.out.println("image\\" + file1.getAbsolutePath().split("\\\\")[2]);
                                FileOutputStream out = new FileOutputStream(file1);
                                out.write(data);
                                out.close();
                           // }
                            doctor.setPhotoPath("image\\\\" + "pic" + key[rowNum].toString() + "." + "jpeg");
                            break;
                        case 10:
                            String password = dataFormatter.formatCellValue(row.getCell(columnsNum));
                            doctor.setPassword(password);

                    }
                }
                //doctor.setPhotoPath("image\\\\" + "pic" + key[rowNum].toString() + "." + "jpeg");
                System.out.println(doctors);

                doctors.add(doctor);

            }
        } catch (NullPointerException e) {
            System.out.println();
            e.printStackTrace();
        }

        return doctors;
    }

    public static void main(String[] args) throws IOException {
//        DbUtil db = new DbUtil();
//        for (Doctor doctor : paresDoctor("C:\\Users\\lenovo\\Desktop\\test.xls")) {
//            try {
//                db.addStaff(doctor);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        //System.out.println(paresDoctor("C:\\Users\\lenovo\\Desktop\\test.xls"));
//        System.out.println(paresMedicine("C:\\Users\\lenovo\\Desktop\\test.xls"));
//        System.out.println(paresDepartment("C:\\Users\\lenovo\\Desktop\\test.xls"));
        System.out.println(paresWorkday("C:\\Users\\lenovo\\Desktop\\test.xls").toArray()[0]);

    }

}
