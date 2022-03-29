package eu.flowtex.bingo.utils;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Utils {
    public static String shortInteger(int duration) {
        String string = "";
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (duration / 60 / 60 / 24 >= 1) {
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60;
            duration -= duration / 60 / 60 * 60 * 60;
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60;
            duration -= duration / 60 * 60;
        }
        if (duration >= 1) {
            seconds = duration;
        }
        if (hours <= 9) {
            string = ChatColor.AQUA + String.valueOf(string) + "0" + hours + "§7:" + ChatColor.AQUA;
        } else {
            string = ChatColor.AQUA + String.valueOf(string) + hours + "§7:" + ChatColor.AQUA;
        }
        if (minutes <= 9) {
            string = ChatColor.AQUA + String.valueOf(string) + "0" + minutes + "§7:" + ChatColor.AQUA;
        } else {
            string = ChatColor.AQUA + String.valueOf(string) + minutes + "§7:" + ChatColor.AQUA;
        }
        if (seconds <= 9) {
            string = ChatColor.AQUA + String.valueOf(string) + "0" + seconds + ChatColor.AQUA;
        } else {
            string = ChatColor.AQUA + String.valueOf(string) + seconds + ChatColor.AQUA;
        }
        return string;
    }

    public static String getItemName(final Material material) {
        return WordUtils.capitalize(material.name().replace("_", " ").toLowerCase());
    }
}
