
class User {
    private int id;
    private String name;
    private int win;
    private int lose;
    private int allplays;

    public User() {
        this.id = 0;
        this.name = "player";
        this.win = 0;
        this.lose = 0;
        this.allplays = 0;
    }

    public User(String name, int id_b) {
        this.id = 0;
        this.name = name;
        this.win = 0;
        this.lose = 0;
        this.allplays = 0;
        if (id_b != 0) {
            this.id = id_b;
        }
    }

    public User(int id, String name, int win, int lose, int allp) {
        this.id = id;
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.allplays = allp;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getWin() { return win; }
    public int getLose() { return lose; }
    public int getAllplays() { return allplays; }

    public void userWin() {
        win++;
        allplays++;
    }

    public void userLose() {
        lose++;
        allplays++;
    }

    public void userDeadHeat() {
        allplays++;
    }
}