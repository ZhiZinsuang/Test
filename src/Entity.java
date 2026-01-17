abstract class Entity {
    protected int id;
    protected String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract String getInfo();

    public String getType() {
        return "Base Entity";
    }

    public static void printEntityType(Entity entity) {
        System.out.println("Type via static method: " + entity.getType());
    }

    public int getId() { return id; }
    public String getName() { return name; }
}