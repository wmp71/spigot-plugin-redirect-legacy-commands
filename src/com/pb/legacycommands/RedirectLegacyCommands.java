package com.pb.legacycommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RedirectLegacyCommands extends JavaPlugin implements Listener {
	@Override
    public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
    @Override
    public void onDisable() {
    }
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void onCommand(PlayerCommandPreprocessEvent event) {
		// Redirect legacy commands
		
		String cmd = event.getMessage();
		String oc = cmd + "";
		
		List<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile("(?:[^\\s'\"]+|'[^']*'|\"[^\"]*\")+")
		    .matcher(cmd);
		while(m.find()) allMatches.add(m.group());
		String[] args = allMatches.toArray(new String[0]);
		args[0] = args[0].toLowerCase();
		
		// effect USERNAME EFFECTNAME|clear
		if(args.length >= 3 && args[0].equals("/effect"))
			if(!args[1].equals("give") && !args[1].equals("clear")) {
				cmd = "/effect ";
				
				if(args[2].toLowerCase().equals("clear")) cmd += "clear ";
				else cmd += "give ";
				
				cmd += args[1] + " ";
				
				if(!args[2].toLowerCase().equals("clear")) {
					try {
						// try 1.7 numeric effect IDs
						int effect = Integer.parseInt(args[2]);
						
						if(effect >= 1 && effect <= 33) {
							// cmd += Effect.getById(effect).name();
							
							if(effect == 1)
								cmd += "speed";
							else if(effect == 2)
								cmd += "slowness";
							else if(effect == 3)
								cmd += "haste";
							else if(effect == 4)
								cmd += "mining_fatigue";
							else if(effect == 5)
								cmd += "strength";
							else if(effect == 6)
								cmd += "instant_health";
							else if(effect == 7)
								cmd += "instant_damage";
							else if(effect == 8)
								cmd += "jump_boost";
							else if(effect == 9)
								cmd += "nausea";
							else if(effect == 10)
								cmd += "regeneration";
							else if(effect == 11)
								cmd += "resistance";
							else if(effect == 12)
								cmd += "fire_resistance";
							else if(effect == 13)
								cmd += "water_breathing";
							else if(effect == 14)
								cmd += "invisibility";
							else if(effect == 15)
								cmd += "blindness";
							else if(effect == 16)
								cmd += "night_vision";
							else if(effect == 17)
								cmd += "hunger";
							else if(effect == 18)
								cmd += "weakness";
							else if(effect == 19)
								cmd += "poison";
							else if(effect == 20)
								cmd += "wither";
							else if(effect == 21)
								cmd += "health_boost";
							else if(effect == 22)
								cmd += "absorption";
							else if(effect == 23)
								cmd += "saturation";
							else if(effect == 24)
								cmd += "glowing";
							else if(effect == 25)
								cmd += "levitation";
							else if(effect == 26)
								cmd += "luck";
							else if(effect == 27)
								cmd += "unluck";
							else if(effect == 28)
								cmd += "slow_falling";
							else if(effect == 29)
								cmd += "conduit_power";
							else if(effect == 30)
								cmd += "dolphins_grace";
							else if(effect == 31)
								cmd += "bad_omen";
							else if(effect == 32)
								cmd += "hero_of_the_village‌";
							else if(effect == 33)
								cmd += "darkness";
						} else {
							cmd += "unknown";
						}
					} catch(Exception e) {
						// 1.7 bukkit can have UPERCASE effect constants
						String ef = args[2].toLowerCase();
						if(ef.equals("DAMAGE_RESISTANCE"))
							cmd += "resistance";
						else
							cmd += ef;
					}
					
					if(args.length > 3) {
						cmd += " " + String.join(" ", Arrays.copyOfRange(args, 3, args.length));
					}
				}
			}
		
		// gamemode 0-3|s|c|a|sp
		if(args.length >= 2 && args[0].equals("/gamemode")) {
			cmd = "/gamemode ";
			
			if(args[1].equals("0") || args[1].equals("s"))
				cmd += "survival";
			else if(args[1].equals("1") || args[1].equals("c"))
				cmd += "creative";
			else if(args[1].equals("2") || args[1].equals("a"))
				cmd += "adventure";
			else if(args[1].equals("3") || args[1].equals("sp"))
				cmd += "spectator";
			else 
				cmd += args[1];
			
			if(args.length >= 3)
				cmd += " " + String.join(" ", Arrays.copyOfRange(args, 2, args.length));
		}
		
		// summon EnderDragon
		if(args.length >= 2 && args[0].equals("/summon")) {
			if(Character.isUpperCase(args[1].charAt(0)) && !args[1].contentEquals("-")) {
				cmd = "/summon ";
				
				if(args[1].equals("EntityHorse"))
					cmd += "minecraft:horse";
				else if(args[1].equals("EyeOfEnderSignal"))
					cmd += "minecraft:eye_of_ender";
				else if(args[1].equals("MinecartCommandBlock"))
					cmd += "minecraft:command_block_minecart";
				else if(args[1].equals("MinecartTNT"))
					cmd += "minecraft:tnt_minecart";
				else if(args[1].equals("ThrownPotion"))
					cmd += "minecraft:potion";
				else if(args[1].equals("Snowman"))
					cmd += "minecraft:snow_golem";
				else if(args[1].equals("MushroomCow"))
					cmd += "minecraft:mooshroom";
				else if(args[1].equals("LavaSlime"))
					cmd += "minecraft:magma_cube";
				else if(args[1].equals("MinecartSpawner"))
					cmd += "minecraft:spawner_minecart";
				else if(args[1].equals("MinecartHopper"))
					cmd += "minecraft:hopper_minecart";
				else if(args[1].equals("XPOrb"))
					cmd += "minecraft:experience_orb";
				else if(args[1].equals("ThrownExpBottle"))
					cmd += "minecraft:experience_bottle";
				else if(args[1].equals("FireworksRocket"))
					cmd += "minecraft:firework_rocket";
				else if(args[1].equals("FireworksRocketEntity"))
					cmd += "minecraft:firework_rocket";
				else if(args[1].equals("FallingSand"))
					cmd += "minecraft:falling_block";
				else if(args[1].equals("VillagerGolem"))
					cmd += "minecraft:iron_golem";
				else if(args[1].equals("SmallFireball"))
					cmd += "minecraft:small_fireball";
				else if(args[1].equals("MinecartRidable"))
					cmd += "minecraft:minecart";
				else if(args[1].equals("MinecartFurnace"))
					cmd += "minecraft:furnace_minecart";
				else if(args[1].equals("WitherBoss"))
					cmd += "minecraft:wither";
				else if(args[1].equals("PigZombie"))
					cmd += "minecraft:zombified_piglin";
				else {
					String _entity = String.valueOf(Character.toLowerCase(args[1].charAt(0))) + args[1].replaceFirst("^.", "");
					String entity = _entity + "";
					Pattern pattern = Pattern.compile("([A-Z])");
					Matcher matcher = pattern.matcher(entity);
					int pos;
					while(matcher.find()) {
						entity = entity.replace(matcher.group(1), "_" + matcher.group(1).toLowerCase());
						pos = matcher.end();
					}
					cmd += "minecraft:" + entity; }
				
				if(args.length >= 5)
					cmd += " " + args[2] + " " + args[3] + " " + args[4];
				
				if(args.length >= 6) {
					// nbt parsing later
					cmd += " " + String.join(" ", Arrays.copyOfRange(args, 5, args.length));
				}
			}
		}
		
		if(!cmd.equals(oc)) event.setMessage(cmd);
	}
}
