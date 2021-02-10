package com.karlofduty.NoVoidLoss;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NoVoidLoss extends JavaPlugin
{
    public static Map<UUID, ItemEntry> playerSkulls = new HashMap<>();
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
    }
}
