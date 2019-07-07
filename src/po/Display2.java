package po;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZC on 2019/6/30.
 */
public class Display2 {
    private String doctor_name;
    private String patient_names;
    private String department_name;
    private Integer patientAmount;

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getPatient_names() {
        return patient_names;
    }

    public void setPatient_names(String patient_names) {
        this.patient_names = patient_names;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public Integer getPatientAmount() {
        return patientAmount;
    }

    public void setPatientAmount(Integer patientAmount) {
        this.patientAmount = patientAmount;
    }

//    @Override
//    public String toString() {
//        return "\""+doctor_name + "\",\"" + patient_names + "\",\"" + department_name + "\",\"" + patientAmount+"\"";
//    }


    @Override
    public String toString() {
        return "Display2{" +
                "doctor_name='" + doctor_name + '\'' +
                ", patient_names='" + patient_names + '\'' +
                ", department_name='" + department_name + '\'' +
                ", patientAmount=" + patientAmount +
                '}';
    }

    public static void main(String[] args) {
        List<Display2> display2s = new ArrayList<>();
        Display2 display2 = new Display2();
        display2.setDepartment_name("1");
        display2.setDoctor_name("1");
        display2.setPatient_names("2");
        display2.setPatientAmount(2);
        display2s.add(display2);


        Display2 display21 = new Display2();
        display21.setDepartment_name("1");
        display21.setDoctor_name("1");
        display21.setPatient_names("2");
        display21.setPatientAmount(2);
        display2s.add(display21);

        System.out.println(display2s.toString().split(",")[0].replaceAll("\\[",""));
//        Object[][] data;
//        data = display2s.toArray()
      //  System.out.println("1,2,3".split(",").length);
    }
}
