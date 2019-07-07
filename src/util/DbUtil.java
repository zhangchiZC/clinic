package util;

import javafx.beans.binding.NumberExpression;
import po.Display;
import po.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by ZC on 2019/6/24.
 */
public class DbUtil {
    private static Connection conn = null;
    private static Statement stmt = null;

    public DbUtil() {
        conn = this.getConnection();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/clinic?autoReconnect=true";
        String username = "root";
        String password = "root";

        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return conn;

    }

//    public void finalize() {
//        try {
//            if (stmt != null) {
//                stmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void addPatient(Patient patient) throws SQLException {
        String sql = "insert into patient(username,password) values ('" + patient.getUsername() + "','" + patient.getPassword() + "')";
        stmt.executeUpdate(sql);
    }

    public boolean findUser(Patient patient) throws SQLException {
        String sql = "select * from patient where username='" + patient.getUsername() + "'";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString("username");
            String pwd = rs.getString("password");
            if (name.equals(patient.getUsername()) && pwd.equals(patient.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public List<String> findAllDepartment() throws SQLException {
        ResultSet rs = stmt.executeQuery("select * FROM  department");
        List<String> departs = new ArrayList<>();
        while (rs.next()) {
            String departmentName = rs.getString("departmentName");
            departs.add(departmentName);
        }
        return departs;
    }

    public String findDepartmentNameById(Integer id) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT departmentName FROM department WHERE id='" + id + "'");
        String departmentName = null;
        while (rs.next()) {
            departmentName = rs.getString("departmentName");
        }
        return departmentName;
    }

    public List<String> findAllDepartmentId() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * from department");
        List<String> ids = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("id");
            ids.add(id);
        }
        return ids;
    }

    public String findIdByDepartmentName(String departmentName) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT id FROM department WHERE departmentName='" + departmentName + "'");
        String id = null;
        while (rs.next()) {
            id = rs.getString("id");
        }
        return id;

    }

    public List<String> findDoctorIdByDateTimeDepId(String date, String time, Integer depId) throws SQLException {
        List<String> doctorIds = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT doctor_id FROM workday WHERE id_department='" + depId + "' AND workTime='" + time + "' AND workDate='" + date + "'");
        String id_doctor = null;
        while (rs.next()) {
            id_doctor = rs.getString("doctor_id");
            doctorIds.add(id_doctor);
        }
        return doctorIds;

    }
//    public String findDoctorNameByTel(String tel){
//        ResultSet rs = stmt.executeQuery("SELECT ")
//    }

    public List<Doctor> findAllByDoctor_idJoinWorkday(String date, String time, Integer depId) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        List<String> doctorIds = new ArrayList<>();
        List<ResultSet> rss = new ArrayList<>();

        int doctorIdLength = findDoctorIdByDateTimeDepId(date, time, depId).size();


        for (int i = 0; i < doctorIdLength; i++) {
            ResultSet rs = stmt.executeQuery("" +
                    "select * from doctor  JOIN workday ON doctor.doctor_id=workday.doctor_id where doctor.doctor_id='" + findDoctorIdByDateTimeDepId(date, time, depId).get(i) + "'");
            rss.add(rs);

            while (rss.get(i).next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctor_id(Integer.parseInt(rs.getString("doctor_id")));
                doctor.setBirthday(rs.getString("birthday"));
                doctor.setDegree(rs.getString("degree"));
                doctor.setDepartment_id(Integer.parseInt(rs.getString("id_department")));
                doctor.setName(rs.getString("doctor_name"));
                doctor.setSex(rs.getString("sex"));
                doctor.setStatue(Integer.parseInt(rs.getString("statue")));
                doctor.setTel(rs.getString("tel"));
                doctor.setPhotoPath(rs.getString("photoPath"));
                //System.out.println(rs.getString("photoPath"));
                doctor.setIntroduction(rs.getString("introduction"));
                doctor.setPassword(rs.getString("password"));//////
                doctors.add(doctor);
            }


        }
        return doctors;
    }

    public void addOrder(String date, String doctor_name, String patient_name, String department_name, String time) throws SQLException {
        stmt.executeUpdate("INSERT INTO orders(orderDate,doctor_name,patient_name,department_name,orderTime) " +
                "VALUES ('" + date + "','" + doctor_name + "','" + patient_name + "','" + department_name + "','" + time + "')");

    }

    public void addOrderIncludeID(Integer id, String date, String doctor_name, String patient_name, String department_name, String time) throws SQLException {
        stmt.executeUpdate("INSERT INTO orders(id,orderDate,doctor_name,patient_name,department_name,orderTime) " +
                "VALUES ('" + id + "','" + date + "','" + doctor_name + "','" + patient_name + "','" + department_name + "','" + time + "')");

    }

    public int deleteOrder(String doctor_name, String patient_name, String department_name, String date, String time) throws SQLException {
        int n = stmt.executeUpdate("DELETE FROM orders WHERE doctor_name='" + doctor_name + "' AND patient_name='" + patient_name + "' AND department_name='" + department_name + "' AND  orderDate='" + date + "' AND orderTime='" + time + "'");
        return n;
    }

    public void addStaff(Doctor doctor) throws SQLException {
        stmt.executeUpdate("INSERT INTO doctor(doctor_id,departmentId,doctor_name,statue,degree,sex,birthday,tel,introduction,photoPath,password) VALUE ('" + doctor.getDoctor_id() + "','" + doctor.getDepartment_id() + "','" + doctor.getName() + "','" + doctor.getStatue() + "','" + doctor.getDegree() + "','" + doctor.getSex() + "','" + doctor.getBirthday() + "','" + doctor.getTel() + "','" + doctor.getIntroduction() + "','" + doctor.getPhotoPath() + "','" + doctor.getPassword() + "')");

    }

    public void addMedicine(Medicine medicine) throws SQLException {
        stmt.executeUpdate("INSERT INTO medicine(medicine_id,medicine_name,medicine_amount,medicine_price) VALUE ('" + medicine.getMedicine_id() + "','" + medicine.getMedicine_name() + "','" + medicine.getMedicine_amount() + "','" + medicine.getMedicine_price() + "')");
    }

    public void addDepartment(Department department) throws SQLException {
        stmt.executeUpdate("INSERT INTO department(id,departmentName) VALUES ('" + department.getDepartment_id() + "','" + department.getDepartment_name() + "')");
    }

    public Boolean isExistsOfStaff(Integer statue, Integer doctor_id, String password) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM doctor WHERE statue='" + statue + "' AND doctor_id='" + doctor_id + "' AND password='" + password + "'");
        if (!rs.next()) {
            return false;
        } else {
            return true;
        }
    }

    public void addWorkday(Workday workday) throws SQLException {
        stmt.executeUpdate("INSERT INTO workday(doctor_id,id_department,workTime,workDate) VALUES ('" + workday.getDoctor_id() + "','" + workday.getId_department() + "','" + workday.getWorkTime() + "','" + workday.getWorkDate() + "')");
    }

    public List<Workday> findAllWorkday() throws SQLException {
        List<Workday> workdays = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT doctor_id,id_department,workTime,workDate FROM workday");
        while (rs.next()) {
            Workday workday = new Workday();
            workday.setDoctor_id(rs.getInt("doctor_id"));
            workday.setId_department(rs.getInt("id_department"));
            workday.setWorkTime(rs.getString("workTime"));
            workday.setWorkDate(rs.getString("workDate"));
            workdays.add(workday);
        }
        return workdays;
    }

    public void addDisply(Display display) throws SQLException {

        stmt.executeUpdate("INSERT INTO display(doctor_name,department_name,code,patient_name) VALUES ('" + display.getDoctor_name() + "','" + display.getDepartment_name() + "','" + display.getCode() + "','" + display.getPatient_name() + "')");
    }

    public Orders findAllByIDfromOrders(Integer id) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * from orders WHERE id='" + id + "'");
        Orders orders = new Orders();
        while (rs.next()) {
            orders.setId(Integer.parseInt(rs.getString("id")));
            orders.setDepartment_name(rs.getString("department_name"));
            orders.setDoctor_name(rs.getString("doctor_name"));
            orders.setOrderDate(rs.getString("orderDate"));
            orders.setOrderTime(rs.getString("orderTime"));
            orders.setPatient_name(rs.getString("patient_name"));
        }
        return orders;
    }

    public List<Display> findAllfromDisplay() throws SQLException {
        List<Display> displays = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM display");
        while (rs.next()) {
            Display display = new Display();
            display.setDepartment_name(rs.getString("department_name"));
            display.setPatient_name(rs.getString("patient_name"));
            display.setDoctor_name(rs.getString("doctor_name"));
            display.setCode(rs.getString("code"));
            displays.add(display);
        }
        return displays;
    }

    public void truncateDoctor() throws SQLException {
        stmt.execute("TRUNCATE doctor");
    }

    public void truncateDepartment() throws SQLException {
        stmt.execute("TRUNCATE department");
    }
    public void truncateMedicine() throws SQLException {
        stmt.execute("TRUNCATE medicine");
    }
    public void truncateWorkday() throws SQLException {
        stmt.execute("TRUNCATE workday");
    }

    public List<Display2> createViewAndFindDisplayListByDepatmentName(String department_name) throws SQLException {
        List<Display2> display2s = new ArrayList<>();

//        stmt.execute("CREATE VIEW orderView (doctor_name,patient_name,deparment_name,patientAmount) AS SELECT doctor_name,group_concat(patient_name separator ',') as patient_name,department_name,(select count(*) from orders where doctor_name='zc') as amount FROM clinic.orders group by doctor_name;");
        stmt.execute("create view a(doctor_name,patientAmount) as select doctor_name,count(*) from orders group by doctor_name");
        stmt.execute("create view b(doctor_name,patient_names,department_name) as SELECT doctor_name,group_concat(patient_name separator '&') as patient_name,department_name FROM clinic.orders WHERE department_name='" + department_name + "'group by doctor_name");
        ResultSet rs = stmt.executeQuery("select b.*,a.patientAmount from a join b on a.doctor_name=b.doctor_name");
        while (rs.next()) {
            Display2 display2 = new Display2();
            display2.setPatient_names(rs.getString("patient_names"));
            display2.setPatientAmount(Integer.parseInt((rs.getString("patientAmount"))));
            display2.setDepartment_name(rs.getString("department_name"));
            display2.setDoctor_name(rs.getString("doctor_name"));
            display2s.add(display2);

        }
        stmt.execute("DROP view a");
        stmt.execute("DROP view b");
        return display2s;
    }

    public int returnRowNumFromDisplay(String department_name) throws SQLException {
        stmt.execute("create view a(doctor_name,patientAmount) as select doctor_name,count(*) from orders group by doctor_name");
        stmt.execute("create view b(doctor_name,patient_names,department_name) as SELECT doctor_name,group_concat(patient_name separator '&') as patient_name,department_name FROM clinic.orders WHERE department_name='" + department_name + "'group by doctor_name");
        ResultSet rs = stmt.executeQuery("select * from a join b on a.doctor_name=b.doctor_name");
        rs.last();
        int n = rs.getRow();
        rs.beforeFirst();
        stmt.execute("DROP view a");
        stmt.execute("DROP view b");
        return n;
    }

    public String findDoctorNameByID(Integer id) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT doctor_name FROM doctor WHERE doctor_id='" + id + "'");
        String doctor_name = "";
        while (rs.next()) {
            doctor_name = rs.getString("doctor_name");
        }
        return doctor_name;
    }

    public Display2 listForDoctorSystem(String doctor_name) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT distinct group_concat(patient_name separator ',') as patient_name,(select count(*) as amount from orders where doctor_name='" + doctor_name + "') as amount from clinic.orders where doctor_name='" + doctor_name + "' group by doctor_name");
        Display2 display2 = new Display2();
        while (rs.next()) {

            display2.setPatient_names(rs.getString("patient_name"));
            display2.setPatientAmount(Integer.parseInt(rs.getString("amount")));
        }
        return display2;
    }

    public void deletePatientFromOrders(String patient_name, String doctor_name) throws SQLException {
        stmt.executeUpdate("DELETE FROM orders WHERE patient_name='" + patient_name + "' AND doctor_name='" + doctor_name + "'");
    }

    public Orders findOrdersIDByPatientNameAndDoctorName(String patient_name, String doctor_name) throws SQLException {
        Orders orders = new Orders();
        ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE id IN (SELECT id FROM orders WHERE doctor_name='" + doctor_name + "' AND patient_name='" + patient_name + "')");
        while (rs.next()) {
            orders.setId(Integer.parseInt(rs.getString("id")));
            orders.setOrderDate(rs.getString("orderDate"));
            orders.setDoctor_name(rs.getString("doctor_name"));
            orders.setDepartment_name(rs.getString("department_name"));
            orders.setOrderTime(rs.getString("orderTime"));
            orders.setPatient_name(rs.getString("patient_name"));
        }
        return orders;
    }

    public List<String> findAllMedicineName() throws SQLException {
        List<String> medicineNames = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT medicine_name FROM medicine");
        while (rs.next()) {
            medicineNames.add(rs.getString("medicine_name"));
        }
        return medicineNames;
    }

    public void addToMedicalHistory(String doctor_name, String patient_name, String medicine_name, Integer medicine_amount, String cure_type, String patient_code) throws SQLException {
        stmt.executeUpdate("INSERT INTO medicalhistory(doctor_name,patient_name,medicine_name,medicine_amount,cure_type,patient_code) VALUES ('" + doctor_name + "','" + patient_name + "','" + medicine_name + "','" + medicine_amount + "','" + cure_type + "','" + patient_code + "')");
    }

    public MedicalList createViewAndFindMedicineNameAndAmount(String patient_code) throws SQLException, InterruptedException {
        //List<MedicalList> medicalLists = new ArrayList<>();
        stmt.execute("CREATE VIEW medicalList(patient_code,medicine_name,medicine_amount) AS select distinct patient_code,group_concat(medicine_name separator ',') as medicine_name,group_concat(medicine_amount separator ',') as medicine_amount from clinic.medicalhistory where patient_code='" + patient_code + "'");
        Thread.sleep(100);
        ResultSet rs = stmt.executeQuery("SELECT * FROM medicalList");
        MedicalList medicalList = new MedicalList();
        while (rs.next()) {
            //MedicalList medicalList = new MedicalList();
            medicalList.setMedicine_amount(rs.getString("medicine_amount"));
            medicalList.setMedicine_name(rs.getString("medicine_name"));
            medicalList.setPatient_code("patient_code");
            // medicalLists.add(medicalList);

        }
        stmt.execute("DROP VIEW medicalList");
        return medicalList;
    }

    public Integer findMedicinePriceByMedicineName(String medicine_name) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT medicine_price FROM medicine WHERE medicine_name='" + medicine_name + "'");
        Integer price = 0;
        while (rs.next()) {
            price = Integer.parseInt(rs.getString("medicine_price"));
        }
        return price;
    }

    public void updateMedicalHistoryPayStatue(String patient_code) throws SQLException {
        stmt.executeUpdate("update clinic.medicalhistory set pay_statue=1 where patient_code='" + patient_code + "'");
    }

    public List<MedicalList> findPatientsWhoPayed() throws SQLException {
        List<MedicalList> medicalLists = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select distinct patient_name,patient_code,group_concat(medicine_name separator ',') as medicine_name,group_concat(medicine_amount separator ',') as medicine_amount from clinic.medicalhistory where pay_statue=1 group by patient_name;");

        while (rs.next()) {
            MedicalList medicalList = new MedicalList();
            medicalList.setPatient_code(rs.getString("patient_code"));
            medicalList.setMedicine_name(rs.getString("medicine_name"));
            medicalList.setMedicine_amount(rs.getString("medicine_amount"));
            medicalList.setPatient_name(rs.getString("patient_name"));
            medicalLists.add(medicalList);
        }
        return medicalLists;
    }
    public List<MedicalList> findPatientsWhoPayedAndTransfusion() throws SQLException{
        List<MedicalList> medicalLists = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select distinct patient_name,patient_code,group_concat(medicine_name separator ',') as medicine_name,group_concat(medicine_amount separator ',') as medicine_amount from clinic.medicalhistory where pay_statue=1  AND  cure_type='输液' group by patient_name;");

        while (rs.next()) {
            MedicalList medicalList = new MedicalList();
            medicalList.setPatient_code(rs.getString("patient_code"));
            medicalList.setMedicine_name(rs.getString("medicine_name"));
            medicalList.setMedicine_amount(rs.getString("medicine_amount"));
            medicalList.setPatient_name(rs.getString("patient_name"));
            medicalLists.add(medicalList);
        }
        return medicalLists;
    }

    public MedicalList findPatientsWhoPayedByPatientCode(String patient_code) throws SQLException {
        // List<MedicalList> medicalLists = new ArrayList<>();
        MedicalList medicalList = new MedicalList();
        ResultSet rs = stmt.executeQuery("select distinct patient_code,group_concat(medicine_name separator ',') as medicine_name,group_concat(medicine_amount separator ',') as medicine_amount from medicalhistory where patient_code='" + patient_code + "'" + " group by patient_code;");
        while (rs.next()) {

            medicalList.setPatient_code(rs.getString("patient_code"));
            medicalList.setMedicine_name(rs.getString("medicine_name"));
            medicalList.setMedicine_amount(rs.getString("medicine_amount"));

        }
        return medicalList;
    }

    public void updateMedicineInventory(String patient_code) throws SQLException {
        stmt.execute("SET SQL_SAFE_UPDATES = 0");
        MedicalList medicalList = findPatientsWhoPayedByPatientCode(patient_code);


        for (int i = 0; i < medicalList.getMedicine_name().split(",").length; i++) {
            Integer total = findMedicineAmountByMedicineName(medicalList.getMedicine_name().split(",")[i]);
            Integer sellOut = Integer.parseInt(medicalList.getMedicine_amount().split(",")[i]);
            Integer result = total - sellOut;
            if (result >= 0) {
                stmt.executeUpdate("update medicine set medicine_amount=" + result + " where medicine_name = '" + medicalList.getMedicine_name().split(",")[i] + "'");
            }
        }


        stmt.execute("SET SQL_SAFE_UPDATES = 1");
    }


    public void deleteAfterConfirmMedicine(String patient_code) throws SQLException {
        stmt.execute("SET SQL_SAFE_UPDATES = 0");

        stmt.executeUpdate("delete from medicalhistory where patient_code='" + patient_code + "'");

        stmt.execute("SET SQL_SAFE_UPDATES = 1");
    }

    public void updateMedicineByPharmacyAdmin(String medicine_name, Integer add_amount) throws SQLException {
        stmt.execute("SET SQL_SAFE_UPDATES = 0");

        Integer nowAmount = findMedicineAmountByMedicineName(medicine_name);
        Integer result = nowAmount + add_amount;
        stmt.executeUpdate("UPDATE medicine SET medicine_amount=" + result + " WHERE medicine_name='" + medicine_name + "'");

        stmt.execute("SET SQL_SAFE_UPDATES = 1");
    }


    public Integer findMedicineAmountByMedicineName(String medicine_name) throws SQLException {
        ResultSet rs = stmt.executeQuery("select medicine_amount from medicine where medicine_name='" + medicine_name + "'");
        Integer total = 0;
        while (rs.next()) {
            total = Integer.parseInt(rs.getString("medicine_amount"));
        }
        return total;
    }

    public List<Medicine> findAllMedicne() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT medicine_id,medicine_name,medicine_amount from medicine");

        while (rs.next()) {
            Medicine medicine = new Medicine();
            medicine.setMedicine_amount(Integer.parseInt(rs.getString("medicine_amount")));
            medicine.setMedicine_name(rs.getString("medicine_name"));
            medicine.setMedicine_id(Integer.parseInt(rs.getString("medicine_id")));
            medicines.add(medicine);
        }


        return medicines;
    }


    public void applyMedicine(String medicine_name, String medicine_amount) throws SQLException {
        stmt.executeUpdate("INSERT INTO clinic.applymedicine(medicine_name,medicine_amount,approve_statue) VALUES ('" + medicine_name + "','" + medicine_amount + "'," + 0 + ")");
    }

    public List<ApplyMedicine> findAllApply() throws SQLException {
        List<ApplyMedicine> applyMedicines = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT id,medicine_name,medicine_amount,approve_statue FROM applymedicine");
        while (rs.next()) {
            ApplyMedicine applyMedicine = new ApplyMedicine();
            applyMedicine.setMedicine_id((Integer.parseInt(rs.getString("id"))));
            applyMedicine.setMedicine_name(rs.getString("medicine_name"));
            applyMedicine.setMedicine_amount(rs.getString("medicine_amount"));
            applyMedicine.setApprove_statue(Integer.parseInt(rs.getString("approve_statue")));
            applyMedicines.add(applyMedicine);
        }
        return applyMedicines;
    }

    public void updateApproveStatueByID(Integer id) throws SQLException {
        stmt.executeUpdate("UPDATE clinic.applymedicine SET approve_statue=1 WHERE id='" + id + "'");
    }


    //*****************************统计表的sql*****************************************//
    public List<Orders> chartOrderDateAndPatientAmount()throws SQLException{
        List<Orders> ordersList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select orderDate,count(*) as patient_amount from orders group by orderDate");
        while (rs.next()){
            Orders orders = new Orders();
            orders.setOrderDate(rs.getString("orderDate"));
            orders.setPatient_amount(Integer.parseInt(rs.getString("patient_amount")));
            ordersList.add(orders);
        }
        return ordersList;
    }

    public List<MedicalList> pieChartSaleVolume()throws SQLException{
        List<MedicalList> medicalLists = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select medicine_name,group_concat(medicine_amount separator ',') as medicine_amount  from medicalhistory group by medicine_name");
        while (rs.next()){
            MedicalList medicalList = new MedicalList();
            medicalList.setMedicine_name(rs.getString("medicine_name"));
            medicalList.setMedicine_amount(rs.getString("medicine_amount"));
            medicalLists.add(medicalList);
        }
        return medicalLists;
    }




    public static void main(String[] args) throws SQLException {
        DbUtil db = new DbUtil();
//        System.out.println(db.findAllDepartment());
//        String[] a = (String[]) db.findAllDepartment().toArray(new String[3]);
//        for (String i : a) {
//            System.out.println(i);
//        }
//        System.out.println(db.findIdByDepartmentName("骨科"));
//        System.out.println(db.findDoctorIdByDateTimeDepId("2019-06-27", "am1", 1));
//
//
//        for (Doctor doctor : db.findAllByDoctor_idJoinWorkday("2019-06-27", "am1", 1)) {
//            System.out.println(doctor);
//        }
//
//        System.out.println(db.findDepartmentNameById(1));
//        System.out.println(db.isExistsOfStaff(2, 1, "zc123"));


        //System.out.println(db.deleteOrder("zc","zc","心血管科","2019-6-27","am1"));
        // System.out.println(db.findAllWorkday().toArray()[0].toString().split("\\{")[1].replace("}","").replaceAll(",","   "));
        // System.out.println(db.findAllWorkday().toArray()[0]);
        // System.out.println(db.findAllByIDfromOrders(23));
        // System.out.println(db.createViewAndFindDisplayListByDepatmentName("心血管科").toArray()[0]);

        //System.out.println(db.returnRowNumFromDisplay("心血管科"));
        //System.out.println(db.findDoctorNameByID(5));
        //System.out.println(db.listForDoctorSystem("zc"));
        //System.out.println(db.findOrdersIDByPatientNameAndDoctorName("cc","zx"));
        //System.out.println(db.findAllMedicineName().toArray());
        //System.out.println(db.findPatientsWhoPayed());
        //System.out.println(db.findAllMedicne());

        //System.out.println(db.findAllApply());
        //db.updateMedicineByPharmacyAdmin("阿莫西林",2);
        System.out.println(db.pieChartSaleVolume().get(0).getMedicine_amount());
    }


}
