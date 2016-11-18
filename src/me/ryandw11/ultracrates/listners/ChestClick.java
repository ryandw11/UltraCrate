package me.ryandw11.ultracrates.listners;

import java.util.ArrayList;
import java.util.List;

import me.ryandw11.ultracrates.commands.Crates;
import me.ryandw11.ultracrates.core.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestClick implements Listener {
	
	private Main plugin;
	public ChestClick(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event){
		Block clicked = event.getClickedBlock();
	    Player p = event.getPlayer();
	    if(Crates.chestclick == true && event.getClickedBlock().getType() == Material.CHEST){
	    	Location loc = clicked.getLocation();
	    	plugin.chest.set(Crates.Name + ".World", loc.getWorld());
	    	plugin.chest.set(Crates.Name + ".X", loc.getX());
	    	plugin.chest.set(Crates.Name + ".Y", loc.getY());
	    	plugin.chest.set(Crates.Name + ".Z", loc.getZ());
	    	plugin.chest.set(Crates.Name + ".Key_Name", "&a" + Crates.Name);
	    	
	    	plugin.saveFile();
	    	
	    	List<String> list = new ArrayList<String>();
			list.add("264");
			list.add("266");
	    	
	    	plugin.getConfig().set("Crates_Items." + Crates.Name, list);
			plugin.saveConfig();
	    	
	    	Crates.chestclick = false;
	    	Crates.Name = "Null";
	    	
	    	p.sendMessage(ChatColor.GREEN + "Crate Created! You can edit the Key name and lore in the Crate file.");
	    }
	    else{
	    	p.sendMessage(ChatColor.RED + "Error: You need to click a chest block!");
	    }
	}
	
	
	
}
