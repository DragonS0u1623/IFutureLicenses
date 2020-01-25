package com.dragons0u1.ifuture.commands;

import com.dragons0u1.ifuture.*;
import com.dragons0u1.ifuture.helpers.*;
import com.dragons0u1.ifuture.managers.*;
import com.dragons0u1.ifuture.ui.*;
import net.milkbowl.vault.economy.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.*;

public class LicenseCommands implements CommandExecutor {
	
	private IFutureLicenses plugin;
	
	public LicenseCommands(IFutureLicenses plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("license").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
			return true;
		}
		
		Player p = (Player) sender;
		PlayerLicenses pl = new PlayerLicenses(plugin);
		Economy eco = VaultHelper.getEconomy();
		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "Incorrect usage: Please add more arguments.");
			return false;
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("check")) {
				if (PlayerLicenses.playerLicenses.get(p.getUniqueId()) != null) {
					p.openInventory(LicenseUI.GUI(p));
				}
				else {
					p.sendMessage(ChatColor.RED + "You don't have any licenses");
				}
				return true;
			} else {
				p.sendMessage(ChatColor.RED + "Incorrect usage: Please add more arguments.");
				return false;
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("delete")) {
				if (PlayerLicenses.licenses.containsKey(args[1])) {
					pl.removeLicense(PlayerLicenses.licenses.get(args[1]));
					p.sendMessage("Removed " + ChatColor.GREEN + args[1] + ChatColor.WHITE + " from acquirable licenses");
					return true;
				}
			} else {
				p.sendMessage(ChatColor.RED + "Incorrect usage: Please add more arguments.");
				return false;
			}
		} else if (args.length == 3) {
			if (args[0].equalsIgnoreCase("check")) {
				p.sendMessage(ChatColor.RED + "Incorrect usage: Please use less arguments.");
				return false;
			} else if (args[0].equalsIgnoreCase("give")) {
				if (Bukkit.getOfflinePlayer(args[1]) != null) {
					Player target = (Player) Bukkit.getOfflinePlayer(args[1]);
					if (PlayerLicenses.licenses.containsKey(args[2])) {
						if (eco.getBalance((OfflinePlayer) p) >= PlayerLicenses.licenses.get(args[2]).getPrice()) {
							pl.addLicenseToPlayer(Bukkit.getOfflinePlayer(args[1]), PlayerLicenses.licenses.get(args[2]));
							eco.withdrawPlayer((OfflinePlayer) p, PlayerLicenses.licenses.get(args[2]).getPrice());
							p.sendMessage("Added license " + ChatColor.GREEN + args[2] + ChatColor.WHITE + " to player "
									+ ChatColor.YELLOW + target.getDisplayName());
						}
						return true;
					} else {
						p.sendMessage(ChatColor.RED + "That license doesn't exist");
						return false;
					}
				} else {
					p.sendMessage(ChatColor.RED + "Incorrect usage: Please tag a valid player.");
					return false;
				}
			} else if (args[0].equalsIgnoreCase("remove")) {
				if (Bukkit.getOfflinePlayer(args[1]) != null) {
					Player target = (Player) Bukkit.getOfflinePlayer(args[1]);
					if (PlayerLicenses.playerLicenses.get(target.getUniqueId()).contains(PlayerLicenses.licenses.get(args[2]))) {
						pl.removeLicenseFromPlayer(Bukkit.getOfflinePlayer(args[1]), PlayerLicenses.licenses.get(args[2]));
						eco.depositPlayer((OfflinePlayer) p, PlayerLicenses.licenses.get(args[2]).getPrice());
						p.sendMessage("Removed license " + ChatColor.GREEN + args[2] + ChatColor.WHITE + " from player "
								+ ChatColor.YELLOW + target.getDisplayName());
						return true;
					} else {
						p.sendMessage(ChatColor.RED + "That player doesn't have that license");
						return false;
					}
				} else {
					p.sendMessage(ChatColor.RED + "Incorrect usage: Please tag a valid player.");
					return false;
				}
			} else {
				if (args[0].equalsIgnoreCase("add")) {
					if (!PlayerLicenses.licenses.containsKey(args[1])) {
						int price = Integer.parseInt(args[2]);
						List<String> lore = Arrays.asList(args);
						lore.remove(0);
						lore.remove(1);
						lore.remove(2);
						pl.addLicense(new License(args[1], price, lore));
						p.sendMessage("Added " + ChatColor.GREEN + args[1] + ChatColor.WHITE + " to the acquirable licenses.");
					}
					return true;
				} else {
					p.sendMessage(ChatColor.RED + "Incorrect usage. Please refer to the proper usage below.");
					return false;
				}
			}
		}
		return false;
	}
}
