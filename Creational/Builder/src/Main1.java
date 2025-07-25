import builders.*;
import model.*;

/**
 * Demonstration of the Builder Design Pattern with Director and Interface-based Builders
 *
 * This example showcases the proper implementation of the Builder pattern using interfaces:
 * 1. Builder Interface - PlayerBuilder defines the building steps but NOT the build() method
 * 2. Concrete Builders - BasicPlayerBuilder, WarriorBuilder, MageBuilder implement the interface
 * 3. Build Method - Only in concrete builders, not in the interface
 * 4. Director Pattern - Uses concrete builders to create standardized objects
 * 5. Polymorphism - Director can work with different builder implementations
 */
public class Main1 {
    public static void main(String[] args) {
        System.out.println("=== Builder Design Pattern with Interface Demo ===\n");

        // Create a director to manage complex constructions
        PlayerDirector director = new PlayerDirector();

        // 1. Using Different Concrete Builders - Each with specific behavior
        System.out.println("1. Using Different Concrete Builders:");
        
        // BasicPlayerBuilder - general purpose
        BasicPlayerBuilder basicBuilder = new BasicPlayerBuilder();
        Player customPlayer = ((BasicPlayerBuilder) basicBuilder
                .name("CustomHero")
                .health(150)
                .level(new Level("Adventurer", 10))
                .addWeapon(new Weapon("Magic Bow", 30, WeaponType.BOW, true))
                .addItem(Item.builder()
                        .name("Healing Crystal")
                        .itemType(ItemType.MAGIC)
                        .value(300)
                        .weight(2)
                        .build()))
                .build();

        System.out.println("Custom Player (BasicBuilder): " + customPlayer.getName());
        System.out.println("Health: " + customPlayer.getHealth());
        System.out.println("Level: " + customPlayer.getLevel().getName());
        System.out.println("Weapons: " + customPlayer.getWeapons().size());
        System.out.println("Items: " + customPlayer.getItems().size());
        System.out.println();

        // 2. Director Usage - Standardized character creation
        System.out.println("2. Director Pattern Usage:");
        System.out.println("Creating different character types using Director...\n");

        // Create a newbie player
        Player newbie = director.createNewbie("Alice");
        System.out.println("NEWBIE CHARACTER:");
        printPlayerSummary(newbie);

        // Create a warrior
        Player warrior = director.createWarrior("Thor");
        System.out.println("WARRIOR CHARACTER:");
        printPlayerSummary(warrior);

        // Create a mage
        Player mage = director.createMage("Gandalf");
        System.out.println("MAGE CHARACTER:");
        printPlayerSummary(mage);

        // Create a quest giver
        Player questGiver = director.createQuestGiver("Elder Sage", "Dragon's Treasure");
        System.out.println("QUEST GIVER NPC:");
        printPlayerSummary(questGiver);

        // 3. Demonstrate Builder Pattern Benefits
        System.out.println("3. Builder Pattern Benefits Demonstration:");
        demonstrateBuilderBenefits();

        // 4. Demonstrate validation
        System.out.println("4. Validation Demonstration:");
        demonstrateValidation();

        // 5. Demonstrate Interface Benefits
        System.out.println("5. Interface-Based Builder Benefits:");
        demonstrateInterfaceBenefits();
    }

    private static void printPlayerSummary(Player player) {
        System.out.println("Name: " + player.getName());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Level: " + player.getLevel().getName() + " (Code: " + player.getLevel().getCode() + ")");
        System.out.println("Weapons: " + player.getWeapons().size());
        if (!player.getWeapons().isEmpty()) {
            player.getWeapons().forEach(weapon ->
                System.out.println("  - " + weapon.getName() + " (" + weapon.getDamage() + " damage)"));
        }
        System.out.println("Items: " + player.getItems().size());
        if (!player.getItems().isEmpty()) {
            player.getItems().forEach(item ->
                System.out.println("  - " + item.getName() + " (Value: " + item.getValue() + ")"));
        }
        System.out.println();
    }

    private static void demonstrateBuilderBenefits() {
        System.out.println("Benefits of Builder Pattern:");
        System.out.println("✓ Flexible parameter order");
        System.out.println("✓ Optional parameters with defaults");
        System.out.println("✓ Readable, fluent interface");
        System.out.println("✓ Immutable objects once built");
        System.out.println("✓ Complex validation logic");
        System.out.println();

        // Show different ways to build the same type of object
        Player minimalPlayer = ((BasicPlayerBuilder) new BasicPlayerBuilder()
                .name("Minimal"))
                .build();

        Player complexPlayer = ((BasicPlayerBuilder) new BasicPlayerBuilder()
                .name("Complex")
                .health(200)
                .level(new Level("Expert", 20))
                .addWeapon(new Weapon("Excalibur", 100, WeaponType.SWORD, true))
                .addItem(Item.builder().name("Crown").itemType(ItemType.ARMOR).value(10000).build()))
                .build();

        System.out.println("Minimal Player: " + minimalPlayer.getName() + " (Health: " + minimalPlayer.getHealth() + ")");
        System.out.println("Complex Player: " + complexPlayer.getName() + " (Health: " + complexPlayer.getHealth() + ")");
        System.out.println();
    }

    private static void demonstrateValidation() {
        System.out.println("Builder Validation Examples:");

        try {
            // This will fail - no name provided
             new BasicPlayerBuilder().build();
        } catch (IllegalStateException e) {
            System.out.println("✓ Basic Builder validation caught: " + e.getMessage());
        }

        try {
            // This will fail - invalid health
            ((BasicPlayerBuilder) new BasicPlayerBuilder().name("Invalid").health(-10)).build();
        } catch (IllegalStateException e) {
            System.out.println("✓ Basic Builder validation caught: " + e.getMessage());
        }

        try {
            // This will fail - warrior without weapons
            ((WarriorBuilder) new WarriorBuilder().name("Weaponless Warrior")).build();
        } catch (IllegalStateException e) {
            System.out.println("✓ Warrior Builder validation caught: " + e.getMessage());
        }

        System.out.println();
    }

    private static void demonstrateInterfaceBenefits() {
        System.out.println("Interface-Based Builder Benefits:");
        System.out.println("✓ Common interface (PlayerBuilder) for all builders");
        System.out.println("✓ Build method is NOT in the interface - only in concrete classes");
        System.out.println("✓ Each concrete builder can have specific validation logic");
        System.out.println("✓ Polymorphism allows flexible usage in Director methods");
        System.out.println("✓ New builder types can be added without changing existing code");
        System.out.println();

        System.out.println("=== Builder Pattern with Interface Demo Complete ===");
    }
}

