package com.karlofduty.NoVoidLoss;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class DeathListener implements Listener
{
    private NoVoidLoss plugin;

    public DeathListener(final NoVoidLoss plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(final PlayerDeathEvent event)
    {
        if (event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID || event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.SUFFOCATION)
        {
            final ItemEntry entry = new ItemEntry();
            for (int i = 0; i < event.getEntity().getInventory().getSize(); ++i)
            {
                final ItemStack stack = event.getEntity().getInventory().getItem(i);
                if (stack != null && !stack.getType().equals(Material.AIR))
                {
                    entry.getItems().put(i, stack);
                }
            }
            event.getDrops().clear();
            NoVoidLoss.items.put(event.getEntity().getUniqueId().toString(), entry);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRespawn(final PlayerRespawnEvent event)
    {
        if (NoVoidLoss.items.containsKey(event.getPlayer().getUniqueId().toString()))
        {
            for (final Map.Entry<Integer, ItemStack> entry : NoVoidLoss.items.get(event.getPlayer().getUniqueId().toString()).getItems().entrySet())
            {
                event.getPlayer().getInventory().setItem(entry.getKey(), entry.getValue());
            }
            NoVoidLoss.items.remove(event.getPlayer().getUniqueId().toString());
        }
    }
}
