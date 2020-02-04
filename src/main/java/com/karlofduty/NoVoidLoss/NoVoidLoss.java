package com.karlofduty.NoVoidLoss;

import org.bukkit.plugin.java.*;
import org.bukkit.*;
import java.util.*;

public class NoVoidLoss extends JavaPlugin
{
    public static Map<String, ItemEntry> items = new HashMap<>();
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new DeathListener(this), this);
    }
}
