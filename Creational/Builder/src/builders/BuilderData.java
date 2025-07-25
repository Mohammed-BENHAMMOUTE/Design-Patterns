package builders;

import java.util.List;
import model.*;

/**
 * Interface for extracting data from concrete builders.
 * This allows the Player constructor to get data from any concrete builder implementation.
 */
public interface BuilderData {
    String getName();
    Integer getHealth();
    Boolean getIsAlive();
    List<Item> getItems();
    List<Player> getFriends();
    Level getLevel();
    List<Weapon> getWeapons();
}
