package builders;

import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 * Concrete implementation of PlayerBuilder specialized for creating mage characters.
 * This class implements the PlayerBuilder interface and provides the build() method.
 */
public class MageBuilder implements PlayerBuilder, BuilderData {
    private String name;
    private Integer health = 80; // Mages have less health by default
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
     * Builds the Player object with mage-specific validations.
     * The build method is NOT in the interface - it's specific to this concrete builder.
     */
    public Player build() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Mage name cannot be null or empty");
        }
        if (health <= 0) {
            throw new IllegalStateException("Mage health must be positive");
        }
        
        // Mage-specific validation: weapons should be magical
        for (Weapon weapon : weapons) {
            if (weapon.getWeaponType() != WeaponType.STAFF && 
                weapon.getWeaponType() != WeaponType.WAND) {
                throw new IllegalStateException("Mages can only use staffs and wands");
            }
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
