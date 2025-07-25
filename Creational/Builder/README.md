# Builder Design Pattern - Interface-Based Implementation

This project demonstrates a proper implementation of the Builder design pattern using interfaces, NOT static inner classes. The implementation follows best practices where the `build()` method is NOT included in the interface but only in concrete builder implementations.

## Architecture Overview

### 1. Builder Interface (`PlayerBuilder`)
- Defines all the building steps (name, health, weapons, etc.)
- **Does NOT include the `build()` method**
- Provides a common contract for all concrete builders
- Enables polymorphism and flexibility

### 2. Concrete Builder Implementations

#### `BasicPlayerBuilder`
- General-purpose builder for standard players
- Implements `PlayerBuilder` and `BuilderData` interfaces
- Contains the `build()` method with basic validation
- Default health: 100

#### `WarriorBuilder`
- Specialized builder for warrior characters
- Implements warrior-specific validation (must have at least one weapon)
- Default health: 200 (warriors are tougher)
- Contains the `build()` method with warrior-specific rules

#### `MageBuilder`
- Specialized builder for mage characters  
- Implements mage-specific validation (only staffs and wands allowed)
- Default health: 80 (mages are more fragile)
- Contains the `build()` method with mage-specific rules

### 3. BuilderData Interface
- Provides a way for the `Player` constructor to extract data from any concrete builder
- Ensures all builders can provide the necessary data to construct a `Player`

### 4. Player Class
- Has a constructor that accepts `BuilderData` interface
- Immutable once created
- Provides static factory methods for different builder types:
  - `Player.builder()` → returns `BasicPlayerBuilder`
  - `Player.warriorBuilder()` → returns `WarriorBuilder`  
  - `Player.mageBuilder()` → returns `MageBuilder`

### 5. PlayerDirector
- Demonstrates the Director pattern
- Uses concrete builders to create standardized character types
- Shows how different builders can be used for different purposes

## Key Design Principles

1. **Interface Segregation**: The `PlayerBuilder` interface only contains building steps, not the `build()` method
2. **Single Responsibility**: Each concrete builder has specific validation logic appropriate for its type
3. **Open/Closed Principle**: New builder types can be added without modifying existing code
4. **Polymorphism**: Director can work with any `PlayerBuilder` implementation
5. **Immutability**: Player objects cannot be modified after creation

## Benefits of This Approach

1. **Flexibility**: Different builders can have different default values and validation rules
2. **Type Safety**: The `build()` method is type-specific to each concrete builder
3. **Extensibility**: Easy to add new builder types without changing the interface
4. **Maintainability**: Clear separation of concerns between interface and implementation
5. **Validation**: Each builder can implement its own specific validation logic

## Usage Examples

```java
// Using concrete builders directly
Player warrior = new WarriorBuilder()
    .name("Aragorn")
    .health(250)
    .addWeapon(new Weapon("Sword", 50, WeaponType.SWORD))
    .build();

Player mage = new MageBuilder()
    .name("Gandalf")
    .health(120)
    .addWeapon(new Weapon("Staff", 30, WeaponType.STAFF))
    .build();

// Using Director for standardized creation
PlayerDirector director = new PlayerDirector();
Player newbie = director.createNewbie("Alice");
Player questGiver = director.createQuestGiver("Elder", "Dragon Quest");

// Using factory methods
Player basic = Player.builder().name("Hero").build();
Player warrior2 = Player.warriorBuilder().name("Thor").build(); // Will fail - no weapon
```

## Validation Examples

- **BasicPlayerBuilder**: Validates name and health
- **WarriorBuilder**: Additionally validates that at least one weapon is present
- **MageBuilder**: Additionally validates that only staffs and wands are used

This implementation properly demonstrates the Builder pattern using interfaces while maintaining the principle that the `build()` method should not be part of the builder interface.
