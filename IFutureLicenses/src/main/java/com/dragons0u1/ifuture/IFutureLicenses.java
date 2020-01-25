package com.dragons0u1.ifuture;

import com.dragons0u1.ifuture.commands.*;
import com.dragons0u1.ifuture.listeners.*;
import com.dragons0u1.ifuture.managers.*;
import com.dragons0u1.ifuture.ui.*;
import org.bukkit.plugin.java.*;

public final class IFutureLicenses extends JavaPlugin {

	@Override
	public void onEnable() {
		if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
			this.getServer().getPluginManager().disablePlugin(this);
			this.getLogger().info("Plugin disabled due to Vault not being found!");
		}
		
		PlayerLicenses pL = new PlayerLicenses(this);
		try {
			pL.loadPlayerLicenses();
			pL.loadLicenses();
		} catch (Exception e) {
			e.printStackTrace();
		}
		registerCommands();
		registerListeners();
		LicenseUI.initialize();
	}

	@Override
	public void onDisable() {
		PlayerLicenses pL = new PlayerLicenses(this);
		try {
			pL.savePlayerLicenses();
			pL.saveLicenses();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void registerCommands() {
		new LicenseCommands(this);
	}
	
	private void registerListeners() {
		new LicenseClickListener(this);
		new InventoryClickListener(this);
	}
}
