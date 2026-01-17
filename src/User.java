import java.text.SimpleDateFormat;
import java.util.Date;

class User extends Entity implements CloneableEntity, Statisticable, Auditable {
    private int win;
    private int lose;
    private int allplays;
    private String creationDate;
    private static int totalUsersCreated = 0;

    // Конструкторы
    public User() {
        super(0, "player");
        this.win = 0;
        this.lose = 0;
        this.allplays = 0;
        this.creationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        totalUsersCreated++;
    }

    public User(String name, int id_b) {
        super(id_b, name);
        this.win = 0;
        this.lose = 0;
        this.allplays = 0;
        this.creationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        totalUsersCreated++;
    }

    public User(int id, String name, int win, int lose, int allp) {
        super(id, name);
        this.win = win;
        this.lose = lose;
        this.allplays = allp;
        this.creationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        totalUsersCreated++;
    }

    // Конструктор копирования
    protected User(User other) {
        super(other.id, other.name);
        this.win = other.win;
        this.lose = other.lose;
        this.allplays = other.allplays;
        this.creationDate = other.creationDate;
    }

    // РЕАЛИЗАЦИЯ АБСТРАКТНОГО МЕТОДА из Entity - ЭТО ОБЯЗАТЕЛЬНО!
    @Override
    public String getInfo() {
        return "User ID: " + id + ", Name: " + name + ", Created: " + creationDate +
                ", Stats: " + win + "W/" + lose + "L/" + allplays + "T";
    }

    // Перегрузка метода getType()
    @Override
    public String getType() {
        return super.getType() + " -> User";
    }

    // Реализация интерфейса CloneableEntity
    @Override
    public Entity shallowClone() {
        try {
            return (Entity) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Entity deepClone() {
        return new User(this);
    }

    // Реализация интерфейса Statisticable
    @Override
    public void updateStatistics() {
        allplays++;
    }

    @Override
    public void resetStatistics() {
        win = lose = allplays = 0;
    }

    @Override
    public String getStatistics() {
        double winRate = allplays > 0 ? (win * 100.0 / allplays) : 0;
        return String.format("Wins: %d, Losses: %d, Total: %d, Win Rate: %.1f%%",
                win, lose, allplays, winRate);
    }

    // Реализация интерфейса Auditable
    @Override
    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(String date) {
        this.creationDate = date;
    }

    // Оригинальные методы
    public void userWin() {
        win++;
        allplays++;
        updateStatistics();
    }

    public void userLose() {
        lose++;
        allplays++;
        updateStatistics();
    }

    public void userDeadHeat() {
        allplays++;
        updateStatistics();
    }

    public int getWin() { return win; }
    public int getLose() { return lose; }
    public int getAllplays() { return allplays; }

    public static int getTotalUsersCreated() {
        return totalUsersCreated;
    }
}