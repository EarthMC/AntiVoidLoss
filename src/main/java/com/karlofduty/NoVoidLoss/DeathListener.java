package com.karlofduty.NoVoidLoss;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(final PlayerDeathEvent event)
    {
        if (event.getEntity().getLocation().getY() < 50)
        {
            event.getDrops().clear();
            event.setDroppedExp(0);
            event.setKeepInventory(true);
            event.setKeepLevel(true);
        }
    }
}
