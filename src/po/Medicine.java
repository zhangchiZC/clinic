package po;

/**
 * Created by ZC on 2019/6/28.
 */
public class Medicine {
    private Integer medicine_id;
    private String medicine_name;
    private Integer medicine_amount;
    private Double medicine_price;

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

    public Integer getMedicine_amount() {
        return medicine_amount;
    }

    public void setMedicine_amount(Integer medicine_amount) {
        this.medicine_amount = medicine_amount;
    }

    public Double getMedicine_price() {
        return medicine_price;
    }

    public void setMedicine_price(Double medicine_price) {
        this.medicine_price = medicine_price;
    }

    @Override
    public String toString() {
//        return "Medicine{" +
//                "medicine_id=" + medicine_id +
//                ", medicine_name='" + medicine_name + '\'' +
//                ", medicine_amount=" + medicine_amount +
//                ", medicine_price=" + medicine_price +
//                '}';


        return medicine_id+"       "+medicine_name+"       "+medicine_amount;
    }
}
