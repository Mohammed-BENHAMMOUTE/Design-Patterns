package builders;

import java.util.List;
import model.*;

/**
 * Builder interface for constructing Player objects.
 * This interface defines all the steps for building a player,
 * but does NOT include the build() method - that's left to concrete implementations.
 */
public interface PlayerBuilder {
    PlayerBuilder name(String name);
    PlayerBuilder health(Integer health);
    PlayerBuilder isAlive(Boolean isAlive);
    PlayerBuilder items(List<Item> items);
    PlayerBuilder addItem(Item item);
    PlayerBuilder friends(List<Player> friends);
    PlayerBuilder addFriend(Player friend);
    PlayerBuilder level(Level level);
    PlayerBuilder weapons(List<Weapon> weapons);
    PlayerBuilder addWeapon(Weapon weapon);
}
