/*
 * Copyright Rukes
 */

/*
 *
 * @author Rukes
 *
 */

package eu.rukes.testplugin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NameTagChange {
    public static void changeName(String name, Player player, boolean tablist) {
        try{
            Method getHandle = player.getClass().getMethod("getHandle", (Class<?>[]) null);
            try{
                Class.forName("com.mojang.authlib.GameProfile");
            }catch(ClassNotFoundException e){
                Bukkit.getLogger().info("changeName() method doesn't work on this version!");
                return;
            }
            Object profile = getHandle.invoke(player).getClass().getMethod("getProfile").invoke(getHandle.invoke(player));
            Field ff = profile.getClass().getDeclaredField("name");
            ff.setAccessible(true);
            ff.set(profile, name);
            for(Player players : Bukkit.getOnlinePlayers()){
                players.hidePlayer(player);
                players.showPlayer(player);
            }
            if(tablist){
                player.setPlayerListName(name);
            }
        }catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException e){
            Bukkit.getLogger().info("An error found while executing changeName() method:");
            e.printStackTrace();
        }
   }
}
