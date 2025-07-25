package model;

import builders.BasicPlayerBuilder;
import builders.MageBuilder;
import builders.WarriorBuilder;
import java.util.Arrays;

/**
 * Director class for creating different types of Players using the Builder pattern.
 * This demonstrates the importance of the Director in the Builder pattern by
 * encapsulating complex construction logic and providing standardized player types.
 * 
 * The Director now works with the PlayerBuilder interface, allowing flexibility
 * in which concrete builder implementation to use.
 */
public class PlayerDirector {

    /**
     * Creates a new player character with basic starting equipment using BasicPlayerBuilder
     */
    public Player createNewbie(String name) {
        Level beginnerLevel = new Level("Beginner", 1);

        Weapon basicSword = new Weapon("Training Sword", 10, WeaponType.SWORD);

        Item healthPotion = Item.builder()
                .name("Health Potion")
                .itemType(ItemType.CONSUMABLE)
                .value(50)
                .weight(1)
                .isStackable(true)
                .build();

        Item basicArmor = Item.builder()
                .name("Leather Armor")
                .itemType(ItemType.ARMOR)
                .value(100)
                .weight(5)
                .isStackable(false)
                .build();

        return ((BasicPlayerBuilder) new BasicPlayerBuilder()
                .name(name)
                .health(100)
                .level(beginnerLevel)
                .addWeapon(basicSword)
                .addItem(healthPotion)
                .addItem(basicArmor)).build();
    }

    /**
     * Creates an experienced warrior with advanced equipment using WarriorBuilder
     */
    public Player createWarrior(String name) {
        Level warriorLevel = new Level("Warrior", 25);

        Weapon enchantedSword = new Weapon("Flame Sword", 45, WeaponType.SWORD, true);
        Weapon battleAxe = new Weapon("War Axe", 50, WeaponType.AXE);

        Description swordLore = new Description("Ancient Weapon",
                "A sword forged in dragon fire, imbued with ancient magic",
                "Master Smith Thorin");

        Item magicArmor = Item.builder()
                .name("Enchanted Plate Mail")
                .itemType(ItemType.ARMOR)
                .itemDescription(Arrays.asList(swordLore))
                .value(1500)
                .weight(20)
                .isStackable(false)
                .build();

        Item manaPotion = Item.builder()
                .name("Greater Mana Potion")
                .itemType(ItemType.CONSUMABLE)
                .value(200)
                .weight(1)
                .isStackable(true)
                .build();

        return ((WarriorBuilder) new WarriorBuilder()
                .name(name)
                .health(250)
                .level(warriorLevel)
                .addWeapon(enchantedSword)
                .addWeapon(battleAxe)
                .addItem(magicArmor)
                .addItem(manaPotion)).build();
    }

    /**
     * Creates a mage character specialized in magic using MageBuilder
     */
    public Player createMage(String name) {
        Level mageLevel = new Level("Archmage", 30);

        Weapon magicStaff = new Weapon("Staff of Elements", 35, WeaponType.STAFF, true);
        Weapon crystalWand = new Weapon("Crystal Wand", 25, WeaponType.WAND, true);

        Description spellbookDesc = new Description("Ancient Knowledge",
                "Contains the most powerful spells known to wizardkind");

        Item spellbook = Item.builder()
                .name("Grimoire of Power")
                .itemType(ItemType.MAGIC)
                .itemDescription(Arrays.asList(spellbookDesc))
                .value(5000)
                .weight(3)
                .isStackable(false)
                .build();

        Item magicRobe = Item.builder()
                .name("Robes of the Arcane")
                .itemType(ItemType.ARMOR)
                .value(800)
                .weight(2)
                .isStackable(false)
                .build();

        return ((MageBuilder) new MageBuilder()
                .name(name)
                .health(150)
                .level(mageLevel)
                .addWeapon(magicStaff)
                .addWeapon(crystalWand)
                .addItem(spellbook)
                .addItem(magicRobe)).build();
    }

    /**
     * Creates a quest NPC with specific quest items using BasicPlayerBuilder
     */
    public Player createQuestGiver(String name, String questName) {
        Level npcLevel = new Level("Quest Giver", 50);

        Description questDesc = new Description("Important Quest",
                "This item is crucial for completing " + questName,
                "Quest Master");

        Item questItem = Item.builder()
                .name("Quest Token: " + questName)
                .itemType(ItemType.QUEST)
                .itemDescription(Arrays.asList(questDesc))
                .value(0)
                .weight(0)
                .isStackable(false)
                .build();

        Item mysteriousKey = Item.builder()
                .name("Ancient Key")
                .itemType(ItemType.KEY)
                .value(1000)
                .weight(1)
                .isStackable(false)
                .build();

        return ((BasicPlayerBuilder) new BasicPlayerBuilder()
                .name(name)
                .health(500)
                .level(npcLevel)
                .addItem(questItem)
                .addItem(mysteriousKey)).build();
    }
}
