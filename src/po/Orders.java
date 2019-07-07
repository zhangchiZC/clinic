package po;

/**
 * Created by ZC on 2019/6/29.
 */
public class Orders {
    private String orderDate;
    private Integer id;
    private String doctor_name;
    private String patient_name;
    private String department_name;
    private String orderTime;

    private Integer patient_amount;//////////后加的

    public Integer getPatient_amount() {//////////后加的
        return patient_amount;
    }

    public void setPatient_amount(Integer patient_amount) {//////////后加的
        this.patient_amount = patient_amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Orders{" +
                " id=" + id + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", doctor_name='" + doctor_name + '\'' +
                ", patient_name='" + patient_name + '\'' +
                ", department_name='" + department_name + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }

}
