package model;

public class Level {
    private String name;
    private int code;

    public Level() {}

    public Level(String name, int code) {
        this.name = name;
        this.code = code;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Level{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
