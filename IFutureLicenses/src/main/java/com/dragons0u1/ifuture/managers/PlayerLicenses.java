package com.dragons0u1.ifuture.managers;

import com.dragons0u1.ifuture.*;
import org.bukkit.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class PlayerLicenses {
	IFutureLicenses plugin;
	public static HashMap<UUID, List<License>> playerLicenses = new HashMap<>();
	public static HashMap<String, License> licenses = new HashMap<>();
	
	public PlayerLicenses(IFutureLicenses plugin) {
		this.plugin = plugin;
		licenses.put("Blacksmith", new License("Blacksmith", 100, ""));
		licenses.put("Electrician", new License("Electrician", 100, ""));
		licenses.put("Carpenter", new License("Carpenter", 100, ""));
		licenses.put("Miner", new License("Miner", 100, ""));
		licenses.put("Adventurer", new License("Adventurer", 100, ""));
		licenses.put("Brewer", new License("Brewer", 100, ""));
	}
	
	public void savePlayerLicenses() throws IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			File playerLicensesFile = new File("IFuture/playerLicenses.dat");
			ObjectOutputStream output = new ObjectOutputStream(
					new GZIPOutputStream(new FileOutputStream(playerLicensesFile)));
			
			if (playerLicenses.get(p.getUniqueId()) != null) {
				playerLicenses.put(p.getUniqueId(), playerLicenses.get(p.getUniqueId()));
			}
			
			try {
				output.writeObject(playerLicenses);
				output.flush();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveLicenses() throws IOException {
		File licensesFile = new File("IFuture/licenses.dat");
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(licensesFile)));
		
		try {
			output.writeObject(licenses);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadPlayerLicenses() throws IOException, ClassNotFoundException {
		File playerLicensesFile = new File("IFuture/playerLicenses.dat");
		
		if (playerLicensesFile != null) {
			ObjectInputStream input = new ObjectInputStream(
					new GZIPInputStream(new FileInputStream(playerLicensesFile)));
			Object readObject = input.readObject();
			input.close();
			
			if (!(readObject instanceof HashMap))
				throw new IOException("ERROR: Data is not a HashMap");
			
			playerLicenses = (HashMap<UUID, List<License>>) readObject;
			playerLicenses.replaceAll((k, v) -> v);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadLicenses() throws IOException, ClassNotFoundException {
		File licensesFile = new File("IFuture/licenses.dat");
		
		if (licensesFile != null) {
			ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(licensesFile)));
			Object readObject = input.readObject();
			input.close();
			
			if (!(readObject instanceof HashMap))
				throw new IOException("ERROR: Data is not a HashMap");
			
			licenses = (HashMap<String, License>) readObject;
			licenses.replaceAll((l, v) -> v);
		}
	}
	
	public void addLicenseToPlayer(OfflinePlayer p, License license) {
		playerLicenses.get(p.getUniqueId()).add(license);
	}
	
	public void addLicense(License license) {
		licenses.put(license.getName(), license);
	}
	
	public void removeLicenseFromPlayer(OfflinePlayer p, License license) {
		playerLicenses.get(p.getUniqueId()).remove(license);
	}
	
	public void removeLicense(License license) {
		if (licenses.containsValue(license))
			licenses.remove(license.getName());
	}
}
