package Objects;

public class Management {

    Integer id;
    String email;
    String password;
    String name;
    String salt;

    public Management(Integer id, String email, String password, String name,String salt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.salt = salt;
    }

    public Management(String name, String userEmail, String password) {
        this.email = userEmail;
        this.password = password;
        this.name = name;
    }

    public Management() {

    }

    public Management(int id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
