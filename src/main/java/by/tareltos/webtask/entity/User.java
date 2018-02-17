package by.tareltos.webtask.entity;

public class User {

    private String mail;
    private String fName;
    private String lName;
    private String password;

    public User(String mail, String fName, String lName, String password) {
        this.mail = mail;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
