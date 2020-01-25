package com.dragons0u1.ifuture.managers;

import java.io.*;
import java.util.*;

public class License implements Serializable {
	private String name;
	private int price;
	public List<String> loreMeta = new ArrayList<>();
	
	public License(String name, int price, String... loreMeta) {
		this.name = name;
		this.price = price;
		this.loreMeta.addAll(Arrays.asList(loreMeta));
	}
	
	public License(String name, int price, List<String> loreMeta) {
		this.name = name;
		this.price = price;
		this.loreMeta = loreMeta;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
}