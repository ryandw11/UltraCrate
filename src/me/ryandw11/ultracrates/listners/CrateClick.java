package me.ryandw11.ultracrates.listners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ryandw11.ultracrates.commands.Crates;
import me.ryandw11.ultracrates.core.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CrateClick implements Listener {
	
	private Main plugin;
	public CrateClick(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event){
		Block clicked = event.getClickedBlock();
	    Player p = event.getPlayer();
	    if(p.getItemInHand().getType() == Material.TRIPWIRE_HOOK && p.getItemInHand().getItemMeta().hasDisplayName() && event.getClickedBlock().getType() == Material.CHEST){
	    	Location loc = clicked.getLocation();
	    	int spawnX = plugin.chest.getInt(p.getItemInHand().getItemMeta().getLore() + ".X");
            int spawnY = plugin.chest.getInt(p.getItemInHand().getItemMeta().getLore() + ".Y");
            int spawnZ = plugin.chest.getInt(p.getItemInHand().getItemMeta().getLore() + ".Z");
            Object world = plugin.chest.get(p.getItemInHand().getItemMeta().getLore() + ".World");
            Location location = new Location((World) world, spawnX, spawnY, spawnZ);
            
            if(loc == location){
            	
            	p.sendMessage(ChatColor.GREEN + "Opeing Crate!");
            	List <String> Items = plugin.getConfig().getStringList("Crates_Items." + p.getItemInHand().getItemMeta().getLore());
				
            	p.sendMessage("Worked");
            	
            	
            	
            }
            else{
            	p.sendMessage(ChatColor.RED + "This chest is not a crate!");
            }

	    		
	    	
	    	
	    }
	}
	
}
