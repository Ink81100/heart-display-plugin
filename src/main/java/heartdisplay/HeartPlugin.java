package heartdisplay;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Criteria;

/**
 * Classe principale du plugin
 */
public class HeartPlugin extends JavaPlugin implements Listener {

  private Scoreboard scoreboard;

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    getLogger().info("HeartDsiplay initialiser");
    setupScoreboard();
    getLogger().info("HeartDisplay démarrer avec succées");
  }

  /**
   * Initialise le ScoreBoard qui contiendrat les PV du joueurs
   */
  private void setupScoreboard() {
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    scoreboard = manager.getMainScoreboard();

    Objective objective = scoreboard.getObjective("health");

    if (objective == null) {
      objective = scoreboard.registerNewObjective(
          "health",
          Criteria.HEALTH,
          Component.text("❤").color(NamedTextColor.RED));
    } else {
      objective.displayName( Component.text("❤").color(NamedTextColor.RED)); // On met à jours le texte
    }

    // Affiche les coeurs sous le nom du joueur
    objective.setDisplaySlot(DisplaySlot.BELOW_NAME);

  }

  /**
   * Applique à un joueur le plugin
   * 
   * @param player Le joueur
   */
  private void applyToPlayer(Player player) {
    player.setScoreboard(scoreboard);
  }

  /**
   * Applique l'affichage des coeur lorsque un joueur rejoint le serveur
   */
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    applyToPlayer(event.getPlayer());
  }
}