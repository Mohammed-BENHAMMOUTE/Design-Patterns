package model;

import builders.BasicPlayerBuilder;
import builders.BuilderData;
import java.util.List;

public class Player {
    private String name;
    private Integer health;
    private Boolean isAlive;
    private List<Item> items;
    private List<Player> friends;
    private Level level;
    private List<Weapon> weapons;

    // Private constructor for builder pattern using BuilderData interface
    public Player(BuilderData builderData) {
        this.name = builderData.getName();
        this.health = builderData.getHealth();
        this.isAlive = builderData.getIsAlive();
        this.items = builderData.getItems();
        this.friends = builderData.getFriends();
        this.level = builderData.getLevel();
        this.weapons = builderData.getWeapons();
    }

    public boolean takeDamage(int damage){
        if (health.intValue() - damage >= 0) {
            this.health = Integer.valueOf(health.intValue() - damage);
            if (this.health == 0) {
                this.isAlive = false;
            }
            return true;
        }
        return false;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Integer getHealth() {
        return health;
    }

    public Boolean getIsAlive() {
        return isAlive;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Player> getFriends() {
        return friends;
    }

    public Level getLevel() {
        return level;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", isAlive=" + isAlive +
                ", items=" + items +
                ", friends=" + friends +
                ", level=" + level +
                ", weapons=" + weapons +
                '}';
    }

    // Static factory methods to get different types of builders
    public static BasicPlayerBuilder builder() {
        return new BasicPlayerBuilder();
    }
    
    public static builders.WarriorBuilder warriorBuilder() {
        return new builders.WarriorBuilder();
    }
    
    public static builders.MageBuilder mageBuilder() {
        return new builders.MageBuilder();
    }
}
