package eu.rukes.testplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Test extends JavaPlugin implements Listener{
    
    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable(){}
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        NameTagChangeAPI.changeName("§5§nAwesomeKing", e.getPlayer(), true);
    }
}
