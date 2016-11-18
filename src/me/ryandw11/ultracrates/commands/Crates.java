package me.ryandw11.ultracrates.commands;

import java.util.ArrayList;
import java.util.List;

import me.ryandw11.ultracrates.core.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Crates implements CommandExecutor {
	
	public static boolean chestclick = false;
	public static String Name = "Null";
	
	
	private Main plugin;
	public Crates(Main plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		Player p = (Player) sender;
		
		if(!(p instanceof Player )) return true;
		
		if(cmd.getName().equalsIgnoreCase("crates")){
			if(args.length == 0){    //if there is nothing there or /crate
				p.sendMessage(ChatColor.GREEN + "-------------------=[" + ChatColor.GOLD + "Crates" + ChatColor.GREEN + "]=-------------------");
				p.sendMessage(ChatColor.GOLD + "/crate create (Name)" + ChatColor.GREEN + "  Makes a crate!");
				p.sendMessage(ChatColor.GOLD + "/crate give (Name) (Player Name)" + ChatColor.GREEN + "  Gives a player the key!");
				p.sendMessage(ChatColor.GREEN + "Plugin made by: " + ChatColor.GOLD + "Ryandw11" + ChatColor.GREEN + "!");
				p.sendMessage(ChatColor.GREEN + "-----------------------------------------------");
			}
			
			if(args.length == 2 && args[1] == "create"){
				p.sendMessage(ChatColor.GREEN + "Please right click on the chest!");
				chestclick = true;
				Name = args[2];
			}
			
			if(args.length == 3 && args[1] == "give"){
				
				Player play = (Player) Bukkit.getServer().getPlayer(args[3]);
				
				if(plugin.chest.getString(args[2]) != null){
					if(play.isOnline()){
				
							String kn = args[2];
							List<String> lore = new ArrayList<String>();  //adding the lore to a list so I could add it.
							lore.add(kn);
							
							ItemStack myItem = new ItemStack(Material.TRIPWIRE_HOOK);  //your item
				
							ItemMeta im = myItem.getItemMeta(); //get the itemmeta of the item
				
							im.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(args[2] + "Key_Name"))); //set the displayname
							im.setLore(lore);
							myItem.setItemMeta(im);
							play.getInventory().addItem(myItem);
							p.sendMessage(ChatColor.GREEN + "A key for the chest " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " has been sent to " + ChatColor.GOLD + args[3] + ChatColor.GREEN + "!");
						}
						else{
							p.sendMessage(ChatColor.RED + "Error: That player isn't online at the moment!");
						}
				}//end of null check
				else{
					p.sendMessage(ChatColor.RED + "Error: Could not find that chest.");
				}
				
			}
		}
		
		
		
		return false;
	}

}
