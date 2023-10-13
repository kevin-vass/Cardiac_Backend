package Objects;

public class Patient {

    Integer patient_id;
    String name;
    String address;
    Integer telephone;
    String birthdate;
    String gender;
    String crated_by;
    String crated_on;


    public Patient(Integer patient_id, String name, String address, Integer telephone, String birthdate, String gender, String crated_by, String crated_on) {
        this.patient_id = patient_id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.birthdate = birthdate;
        this.gender = gender;
        this.crated_by = crated_by;
        this.crated_on = crated_on;
    }

    public Patient(String name, String address, Integer telephone, String birthdate, String gender, String crated_by, String crated_on) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.birthdate = birthdate;
        this.gender = gender;
        this.crated_by = crated_by;
        this.crated_on = crated_on;
    }

    public Patient() {
    }

    public Patient(int id, String name, String address, Integer telephone, String age, String gender) {
        this.patient_id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.birthdate = age;
        this.gender = gender;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCrated_by() {
        return crated_by;
    }

    public void setCrated_by(String crated_by) {
        this.crated_by = crated_by;
    }

    public String getCrated_on() {
        return crated_on;
    }

    public void setCrated_on(String crated_on) {
        this.crated_on = crated_on;
    }
}
