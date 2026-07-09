package model;

/**
 * Model data User (buat login dan CRUD Data User).
 * Extends Person supaya bisa pakai atribut id dan nama yang udah ada di parent class.
 */
public class User extends Person {

    private String username;
    private String password;
    private String level;

    public User() {
        super();
    }

    public User(int id, String nama, String username, String password, String level) {
        super(id, nama);
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String getInfo() {
        return "User: " + getNama() + " (" + username + ") - Level: " + level;
    }
}
