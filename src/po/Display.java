package po;

import util.DbUtil;

/**
 * Created by ZC on 2019/6/29.
 */
public class Display {
    private String doctor_name;
    private String department_name;
    private String code;
    private String patient_name;



    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    @Override
    public String toString() {
//        return "DisplayScreen{" +
//                "doctor_name='" + doctor_name + '\'' +
//                ", department_name='" + department_name + '\'' +
//                ", code='" + code + '\'' +
//                ", patient_name='" + patient_name + '\'' +
//                '}';
        return doctor_name+"  "+department_name+"  "+code+"  "+patient_name;
    }
}
