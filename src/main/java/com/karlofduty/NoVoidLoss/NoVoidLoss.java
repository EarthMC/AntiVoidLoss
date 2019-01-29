package com.karlofduty.NoVoidLoss;

import org.bukkit.plugin.java.*;
import org.bukkit.plugin.*;
import org.bukkit.*;
import java.util.*;

public class NoVoidLoss extends JavaPlugin
{
    public static Map<String, ItemEntry> items;
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new DeathListener(this), this);
    }

    static
    {
        NoVoidLoss.items = new HashMap<String, ItemEntry>();
    }
}
