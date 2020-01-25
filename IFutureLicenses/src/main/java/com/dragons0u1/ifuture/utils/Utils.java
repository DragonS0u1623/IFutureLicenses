package com.dragons0u1.ifuture.utils;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class Utils {
	
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack createItem(Inventory inv, int materialId, int amount, int invSlot, String displayName, List<String> lore) {
		
		ItemStack item;
		
		item = new ItemStack(Material.getMaterial(materialId), amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.chat(displayName));
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(invSlot, item);
		return item;
	}
}
