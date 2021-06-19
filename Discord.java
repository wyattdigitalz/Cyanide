package me.IodicAcid.FartLol;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Discord {
	// an unused function because im a dumbass
	public static String getUUID(String sender) {
		Player player = Bukkit.getPlayer(sender);
		UUID unid = player.getUniqueId();
		String uuid = unid.toString();
		return uuid;
	}

	//downloads a file
	public static void downloadFile(URL url, String fileName) throws Exception {
		try (InputStream in = url.openStream()) {
			Files.copy(in, Paths.get(fileName));
		}
	}
}
