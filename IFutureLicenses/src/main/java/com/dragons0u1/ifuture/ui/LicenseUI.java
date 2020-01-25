package com.dragons0u1.ifuture.ui;

import com.dragons0u1.ifuture.managers.*;
import com.dragons0u1.ifuture.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import java.util.*;

public class LicenseUI {
	public static Inventory inv;
	public static String inventory_name;
	public static int inv_rows = 4 * 9;
	
	public static void initialize() {
		inventory_name = ChatColor.MAGIC + "Licenses";
		inv = Bukkit.createInventory(null, inv_rows);
	}
	
	public static Inventory GUI(Player p, Player target) {
		Inventory toReturn = Bukkit.createInventory(target, inv_rows, ChatColor.GREEN + target.getDisplayName()
				+ ChatColor.WHITE + "'s " + inventory_name);
		int length = PlayerLicenses.playerLicenses.get(target.getUniqueId()).size();
		List<License> pLicenses = PlayerLicenses.playerLicenses.get(target.getUniqueId());
		for (int i = 0; i < length; i++) {
			Utils.createItem(inv, 4, 1, i,
					pLicenses.get(i).getName(), pLicenses.get(i).loreMeta);
		}
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
	
	public static Inventory GUI(Player p) {
		Inventory toReturn = Bukkit.createInventory(p, inv_rows, ChatColor.GREEN + p.getDisplayName()
				+ "'s " + inventory_name);
		int length = PlayerLicenses.playerLicenses.get(p.getUniqueId()).size();
		List<License> pLicenses = PlayerLicenses.playerLicenses.get(p.getUniqueId());
		for (int i = 0; i < length; i++) {
			Utils.createItem(inv, 4, 1, i,
					pLicenses.get(i).getName(), pLicenses.get(i).loreMeta);
		}
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
}
