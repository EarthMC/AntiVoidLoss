package com.karlofduty.NoVoidLoss;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NoVoidLoss extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
    }
}
