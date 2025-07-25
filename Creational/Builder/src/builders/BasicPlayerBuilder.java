package builders;

import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 * Basic concrete implementation of PlayerBuilder for creating standard players.
 * This class implements the PlayerBuilder interface and provides the build() method.
 */
public class BasicPlayerBuilder implements PlayerBuilder, BuilderData {
    private String name;
    private Integer health = 100; // Basic players have standard health
    private Boolean isAlive = true;
    private List<Item> items = new ArrayList<>();
    private List<Player> friends = new ArrayList<>();
    private Level level;
    private List<Weapon> weapons = new ArrayList<>();

    @Override
    public PlayerBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public PlayerBuilder health(Integer health) {
        this.health = health;
        return this;
    }

    @Override
    public PlayerBuilder isAlive(Boolean isAlive) {
        this.isAlive = isAlive;
        return this;
    }

    @Override
    public PlayerBuilder items(List<Item> items) {
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
        return this;
    }

    @Override
    public PlayerBuilder addItem(Item item) {
        if (item != null) {
            this.items.add(item);
        }
        return this;
    }

    @Override
    public PlayerBuilder friends(List<Player> friends) {
        this.friends = friends != null ? new ArrayList<>(friends) : new ArrayList<>();
        return this;
    }

    @Override
    public PlayerBuilder addFriend(Player friend) {
        if (friend != null) {
            this.friends.add(friend);
        }
        return this;
    }

    @Override
    public PlayerBuilder level(Level level) {
        this.level = level;
        return this;
    }

    @Override
    public PlayerBuilder weapons(List<Weapon> weapons) {
        this.weapons = weapons != null ? new ArrayList<>(weapons) : new ArrayList<>();
        return this;
    }

    @Override
    public PlayerBuilder addWeapon(Weapon weapon) {
        if (weapon != null) {
            this.weapons.add(weapon);
        }
        return this;
    }

    /**
     * Builds the Player object with basic validations.
     * The build method is NOT in the interface - it's specific to this concrete builder.
     */
    public Player build() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Player name cannot be null or empty");
        }
        if (health <= 0) {
            throw new IllegalStateException("Player health must be positive");
        }
        
        return new Player(this);
    }

    // Getters for Player constructor
    @Override
    public String getName() { return name; }
    @Override
    public Integer getHealth() { return health; }
    @Override
    public Boolean getIsAlive() { return isAlive; }
    @Override
    public List<Item> getItems() { return items; }
    @Override
    public List<Player> getFriends() { return friends; }
    @Override
    public Level getLevel() { return level; }
    @Override
    public List<Weapon> getWeapons() { return weapons; }
}
