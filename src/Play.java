class Play {
    private int id;
    private String userWin;
    private String userLose;

    public Play(int id, String userWin, String userLose) {
        this.id = id;
        this.userWin = userWin;
        this.userLose = userLose;
    }

    public int getId() { return id; }
    public String getUserWin() { return userWin; }
    public String getUserLose() { return userLose; }
}