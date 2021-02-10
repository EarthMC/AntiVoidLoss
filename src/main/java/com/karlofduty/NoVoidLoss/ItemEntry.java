package com.karlofduty.NoVoidLoss;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemEntry
{
    private Map<Integer, ItemStack> items;

    public ItemEntry()
    {
        this.items = new HashMap<>();
    }

    public Map<Integer, ItemStack> getItems()
    {
        return this.items;
    }
}