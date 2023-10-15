package Objects;

public class ClinicData {

    Integer clinic_id;
    Integer patient_id;
    String age;
    String sex;
    String chest_pain;
    Double resting_bp;
    Double cholesterol;
    String fasting_bs;
    String resting_ecg;
    Double max_hr;
    String exercise_angina;
    Double old_peak;
    String st_slope;
    String created_on;
    String created_by;

    public ClinicData(Integer clinic_id, Integer patient_id, String age, String sex, String chest_pain, Double resting_bp, Double cholesterol, String fasting_bs, String resting_ecg, Double max_hr, String exercise_angina, Double old_peak, String st_slope, String created_on, String created_by) {
        this.clinic_id = clinic_id;
        this.patient_id = patient_id;
        this.age = age;
        this.sex = sex;
        this.chest_pain = chest_pain;
        this.resting_bp = resting_bp;
        this.cholesterol = cholesterol;
        this.fasting_bs = fasting_bs;
        this.resting_ecg = resting_ecg;
        this.max_hr = max_hr;
        this.exercise_angina = exercise_angina;
        this.old_peak = old_peak;
        this.st_slope = st_slope;
        this.created_on = created_on;
        this.created_by = created_by;
    }

    public ClinicData() {
    }

    public ClinicData(int clinicId, int patientId, String age, String sex, String chestPain, Double restingBp, Double cholesterol, String fastingBs, String restingEcg, Double maxHr, String exerciseAngina, Double oldPeak, String stSlope) {
        this.clinic_id = clinicId;
        this.patient_id = patientId;
        this.age = age;
        this.sex = sex;
        this.chest_pain = chestPain;
        this.resting_bp = restingBp;
        this.cholesterol = cholesterol;
        this.fasting_bs = fastingBs;
        this.resting_ecg = restingEcg;
        this.max_hr = maxHr;
        this.exercise_angina = exerciseAngina;
        this.old_peak = oldPeak;
        this.st_slope = stSlope;
    }

    public Integer getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(Integer clinic_id) {
        this.clinic_id = clinic_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getChest_pain() {
        return chest_pain;
    }

    public void setChest_pain(String chest_pain) {
        this.chest_pain = chest_pain;
    }

    public Double getResting_bp() {
        return resting_bp;
    }

    public void setResting_bp(Double resting_bp) {
        this.resting_bp = resting_bp;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getFasting_bs() {
        return fasting_bs;
    }

    public void setFasting_bs(String fasting_bs) {
        this.fasting_bs = fasting_bs;
    }

    public String getResting_ecg() {
        return resting_ecg;
    }

    public void setResting_ecg(String resting_ecg) {
        this.resting_ecg = resting_ecg;
    }

    public Double getMax_hr() {
        return max_hr;
    }

    public void setMax_hr(Double max_hr) {
        this.max_hr = max_hr;
    }

    public String getExercise_angina() {
        return exercise_angina;
    }

    public void setExercise_angina(String exercise_angina) {
        this.exercise_angina = exercise_angina;
    }

    public Double getOld_peak() {
        return old_peak;
    }

    public void setOld_peak(Double old_peak) {
        this.old_peak = old_peak;
    }

    public String getSt_slope() {
        return st_slope;
    }

    public void setSt_slope(String st_slope) {
        this.st_slope = st_slope;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
