package com.dragons0u1.ifuture.listeners;

import com.dragons0u1.ifuture.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;

public class InventoryClickListener implements Listener {
	private IFutureLicenses plugin;
	
	public InventoryClickListener(IFutureLicenses plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		String title = event.getInventory().getTitle();
		if (title.contains(ChatColor.WHITE + "'s " + ChatColor.MAGIC + "Licenses")) {
			event.setCancelled(true);
		}
	}
}
