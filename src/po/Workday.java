package po;

/**
 * Created by ZC on 2019/6/28.
 */
public class Workday {
    private Integer doctor_id;
    private Integer id_department;
    private String workTime;
    private String workDate;

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Integer getId_department() {
        return id_department;
    }

    public void setId_department(Integer id_department) {
        this.id_department = id_department;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    @Override
    public String toString() {
//        return "Workday{" +
//                "doctor_id=" + doctor_id +
//                ", id_department=" + id_department +
//                ", workTime='" + workTime + '\'' +
//                ", workDate='" + workDate + '\'' +
//                '}';
        return "        "+doctor_id+"                "+id_department+"                "+workTime+"                "+workDate;
    }
}
