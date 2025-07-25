package model;

public class Weapon {
    private String name;
    private int damage;
    private WeaponType weaponType;
    private boolean isMagical;

    public Weapon() {}

    public Weapon(String name, int damage, WeaponType weaponType) {
        this.name = name;
        this.damage = damage;
        this.weaponType = weaponType;
        this.isMagical = false;
    }

    public Weapon(String name, int damage, WeaponType weaponType, boolean isMagical) {
        this.name = name;
        this.damage = damage;
        this.weaponType = weaponType;
        this.isMagical = isMagical;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public boolean isMagical() {
        return isMagical;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setMagical(boolean magical) {
        isMagical = magical;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", damage=" + damage +
                ", weaponType=" + weaponType +
                ", isMagical=" + isMagical +
                '}';
    }
}
