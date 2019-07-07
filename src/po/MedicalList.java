package po;

/**
 * Created by ZC on 2019/7/2.
 */
public class MedicalList {
    private String patient_code;
    private String medicine_name;
    private String medicine_amount;

    private String patient_name;//后加的

    public String getPatient_name() {
        return patient_name;
    }//后加的

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }//后加的

    public String getPatient_code() {
        return patient_code;
    }

    public void setPatient_code(String patient_code) {
        this.patient_code = patient_code;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getMedicine_amount() {
        return medicine_amount;
    }

    public void setMedicine_amount(String medicine_amount) {
        this.medicine_amount = medicine_amount;
    }

    @Override
    public String toString() { //后加的
//        return "MedicalList{" +
//                "patient_code='" + patient_code + '\'' +
//                ", patient_name='" + patient_name + '\'' +
//                ", medicine_name='" + medicine_name + '\'' +
//                ", medicine_amount='" + medicine_amount + '\'' +
//
//                '}';

        //return patient_code+"       "+patient_name+"       "+medicine_name+"       "+medicine_amount;
        return patient_code+"       "+patient_name;

    }
}
