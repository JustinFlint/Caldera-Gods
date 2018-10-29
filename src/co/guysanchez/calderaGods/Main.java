package co.guysanchez.calderaGods;
import java.io.File;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
	
	public static List<String> godsList;
	public static List<Integer> fpList;
	public static List<UUID> priestList;
	
	//Readies all player data and calls input functions
	@Override
	public void onEnable() {
		getLogger().info("Checking to see if this plugin data exists.");
		if(new File("plugins\\CalderaGods").exists()){
			getLogger().info("Plugin directory exists.");
		}
		else{
			getLogger().info("Plugin directory does not exist. Creating now.");
			new File("plugins\\CalderaGods").mkdirs();
			new File("plugins\\CalderaGods\\players").mkdirs();
		}
		godsList = GodsIO.godInput();
		fpList = GodsIO.fpInput();
		
		//Event Registration
		getServer().getPluginManager().registerEvents(new GodsListener(), this);
		this.getCommand("calderagods").setExecutor(new CommandKit());		
		
	}
	//Returns a copy of the Plugin value for metadata purposes
	public static Main getInstance() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CalderaGods");
		if(plugin == null || !(plugin instanceof Main)) 
				throw new RuntimeException("CalderaGods not found. Is plugin disabled?");
		return (Main)plugin;
	}
	//Changes the faith points for the god specified
	public static void updateFP(int change, String god) {
		fpList.set(godsList.indexOf(god), fpList.get(godsList.indexOf(god))+change);
	}
	//Ensures all data is saved before closing
	@Override
	public void onDisable() {
		GodsIO.godOutput();
		GodsIO.fpOutput();
	}	
}
