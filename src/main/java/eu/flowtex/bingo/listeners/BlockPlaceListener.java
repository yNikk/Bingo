package eu.flowtex.bingo.listeners;

import eu.flowtex.bingo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    private Main main;

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e) {
        if (Main.getTimer().isPaused()) {
            //if (this.main.getBuildMode().contains(e.getPlayer().getUniqueId())) {
            //    e.setCancelled(false);
            //} else {
                e.setCancelled(true);
            //}
        } else {
            e.setCancelled(false);
        }
    }
}