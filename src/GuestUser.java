import java.text.SimpleDateFormat;
import java.util.Date;

class GuestUser extends Entity implements Auditable {
    private int sessionCount;
    private String lastLogin;

    public GuestUser(int id, String name) {
        super(id, name);
        this.sessionCount = 1;
        this.lastLogin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    // ОБЯЗАТЕЛЬНАЯ реализация абстрактного метода
    @Override
    public String getInfo() {
        return "GuestUser ID: " + id + ", Name: " + name +
                ", Sessions: " + sessionCount + ", Last login: " + lastLogin;
    }

    // Перегрузка без вызова метода базового класса
    @Override
    public String getType() {
        return "Guest User";
    }

    // Реализация интерфейса Auditable
    @Override
    public String getCreationDate() {
        return lastLogin;
    }

    @Override
    public void setCreationDate(String date) {
        this.lastLogin = date;
    }

    public void incrementSession() {
        sessionCount++;
        lastLogin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public int getSessionCount() { return sessionCount; }
    public String getLastLogin() { return lastLogin; }
}