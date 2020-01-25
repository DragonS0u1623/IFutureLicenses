package com.dragons0u1.ifuture.listeners;

import com.dragons0u1.ifuture.*;
import com.dragons0u1.ifuture.managers.*;
import com.dragons0u1.ifuture.ui.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.meta.*;

public class LicenseClickListener implements Listener {
	private IFutureLicenses plugin;
	
	public LicenseClickListener(IFutureLicenses plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent event) {
		Player p = event.getPlayer();
		Entity target = event.getRightClicked();
		@SuppressWarnings("deprecation")
		ItemMeta meta = p.getItemInHand().getItemMeta();
		if ((target instanceof Player) && meta.getDisplayName().equals("License Checker")) {
			Player t = (Player) target;
			if (PlayerLicenses.playerLicenses.get(t.getUniqueId()) != null) {
				p.openInventory(LicenseUI.GUI(p, t));
			}
			else {
				p.sendMessage("That person doesn't have any licenses.");
			}
		}
	}
}
