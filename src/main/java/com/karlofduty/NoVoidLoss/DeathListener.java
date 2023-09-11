package com.karlofduty.NoVoidLoss;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(final PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        if (player.getLastDamageCause() != null && (player.getLastDamageCause().getCause().equals(DamageCause.SUFFOCATION) || player.getLastDamageCause().getCause().equals(DamageCause.VOID)))
        {
            event.getDrops().clear();
            event.setDroppedExp(0);
            event.setKeepInventory(true);
            event.setKeepLevel(true);
        }
        else
        {
            for (ItemStack item : player.getInventory())
            {
                if (item != null && (item.getType().equals(Material.PLAYER_HEAD) || item.getType().equals(Material.DRAGON_HEAD)))
                {
                    event.getDrops().remove(item);
                    event.getItemsToKeep().add(item);
                }
            }
        }
    }
}
