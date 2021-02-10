package com.karlofduty.NoVoidLoss;

import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(final PlayerDeathEvent event)
    {
        if (event.getEntity().getLocation().getY() < 50 || event.getEntity().getLastDamageCause().getCause() == DamageCause.SUFFOCATION)
        {
            event.getDrops().clear();
            event.setDroppedExp(0);
            event.setKeepInventory(true);
            event.setKeepLevel(true);
        }
        else if (event.getEntity().getInventory().contains(Material.PLAYER_HEAD) || event.getEntity().getInventory().getHelmet().getType().equals(Material.PLAYER_HEAD))
        {
            ItemEntry entry = new ItemEntry();
            for (int i = 0; i < event.getEntity().getInventory().getSize(); i++)
            {
                ItemStack item = event.getEntity().getInventory().getItem(i);
                if (item == null || item.getType() == Material.AIR)
                    continue;
                
                if (item.getType().equals(Material.PLAYER_HEAD))
                {
                    event.getDrops().remove(item);
                    entry.getItems().put(i, item);
                }
            }
            if (!entry.getItems().isEmpty())
                NoVoidLoss.playerSkulls.put(event.getEntity().getUniqueId(), entry);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        if (NoVoidLoss.playerSkulls.containsKey(event.getPlayer().getUniqueId()))
        {
            for (Entry<Integer, ItemStack> item : NoVoidLoss.playerSkulls.get(event.getPlayer().getUniqueId()).getItems().entrySet())
            {
                event.getPlayer().getInventory().setItem(item.getKey(), item.getValue());
            }
            NoVoidLoss.playerSkulls.remove(event.getPlayer().getUniqueId());
        }
    }
}
