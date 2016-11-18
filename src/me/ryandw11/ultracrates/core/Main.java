package me.ryandw11.ultracrates.core;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;





import me.ryandw11.ultracrates.commands.Crates;
import me.ryandw11.ultracrates.listners.ChestClick;
import me.ryandw11.ultracrates.listners.CrateClick;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public Main plugin;
	
	public File chestfile = new File(getDataFolder() + "/crates.yml");
	public FileConfiguration chest = YamlConfiguration.loadConfiguration(chestfile);
	
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info("The tester plugin has been enabled!"); // prints into the log
		loadMethoid();
		loadFile();
		registerConfig();
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info("The tester plugin has been disabled!"); // same thing
		saveFile();
	}
	
	
	
	
	
	
	
	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	
	
	
	
	
	
	
	public void saveFile(){
		
		try{
			chest.save(chestfile);
		}catch(IOException e){
			e.printStackTrace();
			
		}
		
	}
	
	public void loadFile(){
		if(chestfile.exists()){
			try {
				chest.load(chestfile);
			} catch (IOException | InvalidConfigurationException e) {

				e.printStackTrace();
			}
		}
		else{
			try {
				chest.save(chestfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadMethoid(){
		getCommand("crates").setExecutor(new Crates(this));
		Bukkit.getServer().getPluginManager().registerEvents(new CrateClick(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ChestClick(this), this);
		
		
	}

}