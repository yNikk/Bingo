package eu.flowtex.bingo.manager;

import eu.flowtex.bingo.Main;
import eu.flowtex.bingo.utils.Utils;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.scoreboard.*;
import org.bukkit.*;
import org.bukkit.scoreboard.Team;

public class BoardManager
{
    private static HashMap<Integer, String> defaults;
    private static ItemManager itemManager;
    private static Main main;
    private static String colorcode = Main.getInstance().getConfig().getString("general.colorcode");
    
    public static void setCurrentScoreboard(final Player p, final String p2) {
        final Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
        s.registerNewObjective("m", "dummy", p2);
        s.getObjective("m").setDisplaySlot(DisplaySlot.SIDEBAR);
        for (final Map.Entry<Integer, String> integerStringEntry : BoardManager.defaults.entrySet()) {
            setLine(integerStringEntry.getKey(), integerStringEntry.getValue(), s);
        }
        p.setScoreboard(s);
    }

    public static void addLine(final Integer s, final String s2) {
        BoardManager.defaults.put(s, s2);
        Bukkit.getOnlinePlayers().forEach(player -> setLine(s, s2, player.getScoreboard()));
    }

    public static void setLine(final int i, final String s2, final Scoreboard s) {
        if (s.getTeam("t" + i) == null) {
            final Team t = s.registerNewTeam("t" + i);
            t.addEntry(getChatColor(i) + "" + getChatColor(i));
            s.getObjective("m").getScore(getChatColor(i) + "" + getChatColor(i)).setScore(i);
        }
        s.getTeam("t" + i).setPrefix(s2);
    }
    
    private static ChatColor getChatColor(final int s) {
        int i = 0;
        for (final ChatColor value : ChatColor.values()) {
            if (i == s) {
                return value;
            }
            ++i;
        }
        return ChatColor.GOLD;
    }
    
    static {
        BoardManager.defaults = new HashMap<Integer, String>();
    }

    public static void startScoreboard() {
        final int online = Bukkit.getOnlinePlayers().size();
        BoardManager.addLine(22, "");
        BoardManager.addLine(21, colorcode + "Platzierung");
        BoardManager.addLine(20, "§8➥ §7" + "-");
        BoardManager.addLine(19, "");
        BoardManager.addLine(18, colorcode + "Team");
        BoardManager.addLine(17, "§8➥ §7" + "-");
        BoardManager.addLine(16, "");
        BoardManager.addLine(15, colorcode + "Verbleibend");
        BoardManager.addLine(14, "§8➥ §7" + "-");
        BoardManager.addLine(13, "");
    }

    public static void refreshScoreboard(final eu.flowtex.bingo.utils.Team team) {
        if (team == eu.flowtex.bingo.utils.Team.SPECTATOR) {
            return;
        }
        final ArrayList<Material> teamitems = itemManager.getItemsFromTeam(team.getTeamid());
        for (final UUID uuid : Main.getBingoManager().getTeamManager().getPlayersInTeam(team)) {
            if (Bukkit.getPlayer(uuid) != null) {
                final Player player = Bukkit.getPlayer(uuid);
                final int online = Bukkit.getOnlinePlayers().size();
                final int placement = itemManager.getPlace(team);
                final int teamid = team.getTeamid();
                final int remainingScore = teamitems.size();
                BoardManager.setLine(21, colorcode + "Platzierung", player.getScoreboard());
                BoardManager.setLine(20, "§8➥ §7" + placement + "§7. Platz", player.getScoreboard());
                BoardManager.setLine(19, colorcode + " ", player.getScoreboard());
                BoardManager.setLine(18, colorcode + "Team", player.getScoreboard());
                BoardManager.setLine(17, "§8➥ §7" + teamid + "§7. Team", player.getScoreboard());
                BoardManager.setLine(16, colorcode + " ", player.getScoreboard());
                BoardManager.setLine(15, colorcode + "Verbleibend", player.getScoreboard());
                BoardManager.setLine(14, "§8➥ §7" + remainingScore + " Items", player.getScoreboard());
                BoardManager.setLine(13, colorcode + " ", player.getScoreboard());
                BoardManager.setLine(10, colorcode + " ", player.getScoreboard());
                BoardManager.setLine(9, colorcode + " ", player.getScoreboard());
                BoardManager.setLine(8, colorcode + " ", player.getScoreboard());
                BoardManager.setLine(7, colorcode + " ", player.getScoreboard());
                BoardManager.setLine(6, colorcode + " ", player.getScoreboard());
                BoardManager.setLine(5, colorcode + " ", player.getScoreboard());
                if (teamitems.size() >= 5) {
                    int x = 10;
                    for (final Material teamitem : teamitems) {
                        BoardManager.setLine(x, "§8- §7" + Utils.getItemName(teamitem), player.getScoreboard());
                        if (--x <= 6) {
                            break;
                        }
                        final int additional = teamitems.size() - 4;
                        BoardManager.setLine(5, "§8- §7" + additional + " §7weitere Items...", player.getScoreboard());
                    }
                }
                else {
                    int x = 10;
                    for (final Material teamitem : teamitems) {
                        BoardManager.setLine(x, "§8- §7" + Utils.getItemName(teamitem), player.getScoreboard());
                        --x;
                    }
                }
            }
        }
    }
}
