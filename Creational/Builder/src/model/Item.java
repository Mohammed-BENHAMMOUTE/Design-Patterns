package model;

import java.util.List;
import java.util.Optional;

public class Item {
    private String name;
    private ItemType itemType;
    private Optional<List<Description>> itemDescription;
    private int value;
    private int weight;
    private boolean isStackable;

    // Private constructor for builder
    private Item(ItemBuilder builder) {
        this.name = builder.name;
        this.itemType = builder.itemType;
        this.itemDescription = builder.itemDescription;
        this.value = builder.value;
        this.weight = builder.weight;
        this.isStackable = builder.isStackable;
    }

    // Getters
    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Optional<List<Description>> getItemDescription() {
        return itemDescription;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isStackable() {
        return isStackable;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", itemType=" + itemType +
                ", itemDescription=" + itemDescription +
                ", value=" + value +
                ", weight=" + weight +
                ", isStackable=" + isStackable +
                '}';
    }

    // Static method to get builder
    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    // Builder class
    public static class ItemBuilder {
        private String name;
        private ItemType itemType;
        private Optional<List<Description>> itemDescription = Optional.empty();
        private int value = 0;
        private int weight = 1;
        private boolean isStackable = false;

        public ItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder itemType(ItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public ItemBuilder itemDescription(List<Description> descriptions) {
            this.itemDescription = Optional.ofNullable(descriptions);
            return this;
        }

        public ItemBuilder value(int value) {
            this.value = value;
            return this;
        }

        public ItemBuilder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public ItemBuilder isStackable(boolean isStackable) {
            this.isStackable = isStackable;
            return this;
        }

        public Item build() {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalStateException("Item name cannot be null or empty");
            }
            if (itemType == null) {
                throw new IllegalStateException("Item type cannot be null");
            }
            return new Item(this);
        }
    }
}
