package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.manager.GameManager;
import eu.flowtex.bingo.utils.Team;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "-" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + e.getPlayer().getName());
        if (Main.getBingoManager().getTeamManager().getTeamFromPlayer(e.getPlayer()) != Team.SPECTATOR && Main.getBingoManager().gameState != GameManager.GameState.INGAME) {
            Main.getBingoManager().getTeamManager().setTeam(e.getPlayer(), Team.SPECTATOR);
        }
    }
}