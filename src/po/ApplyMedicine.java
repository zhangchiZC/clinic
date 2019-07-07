package po;

/**
 * Created by ZC on 2019/7/5.
 */
public class ApplyMedicine {
    private Integer medicine_id;
    private String medicine_name;
    private String medicine_amount;
    private Integer approve_statue;

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
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

    public Integer getApprove_statue() {
        return approve_statue;
    }

    public void setApprove_statue(Integer approve_statue) {
        this.approve_statue = approve_statue;
    }

    @Override
    public String toString() {
//        return "ApplyMedicine{" +
//                "medicine_name='" + medicine_name + '\'' +
//                ", medicine_amount='" + medicine_amount + '\'' +
//                ", approve_statue=" + approve_statue +
//                '}';

        if (approve_statue==0){
            return medicine_id+"       "+medicine_name+"       "+medicine_amount+"       "+"未批准";
        }else {
            return medicine_id+"       "+medicine_name+"       "+medicine_amount+"       "+"已批准";
        }
    }
}
