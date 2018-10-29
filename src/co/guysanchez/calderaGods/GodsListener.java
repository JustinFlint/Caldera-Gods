package co.guysanchez.calderaGods;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
public class GodsListener implements Listener{
	/*ReligiousStatus will be an int of 0-2
	 *0: Not a follower of a religion
	 *1: Follower of a religion
	 *2: Founder of a religion
	 *
	 *God will be a string of the god's name
	 */

	private int priestFP = 10;
	private int followerFP = 10;
	@EventHandler 
	public void onSignActivate(PlayerInteractEvent e) {
		if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN
				&&e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Sign godsSign = (Sign) e.getClickedBlock().getState();
			if(godsSign.getLine(0).contains("[Gods]")) {
				switch((int)e.getPlayer().getMetadata("ReligiousStatus").get(0).value()){
				case 0:
					e.getPlayer().removeMetadata("ReligiousStatus", Main.getInstance());
						if(Main.godsList.contains(godsSign.getLine(1).trim())) {
							e.getPlayer().setMetadata("ReligiousStatus", new FixedMetadataValue(Main.getInstance(), 1));
							e.getPlayer().setMetadata("God", new FixedMetadataValue(Main.getInstance(), godsSign.getLine(1).trim()));
						}
						else {
							e.getPlayer().setMetadata("ReligiousStatus", new FixedMetadataValue(Main.getInstance(), 2));
							e.getPlayer().setMetadata("God", new FixedMetadataValue(Main.getInstance(), godsSign.getLine(1).trim()));
							Main.godsList.add(godsSign.getLine(1).trim());
							Main.fpList.add(0);
							Main.priestList.add(e.getPlayer().getUniqueId());
							e.getPlayer().sendMessage("God Created!");
						}
					break;
				case 1:
					//TODO: Set config file setting for FP per priest and follower
					if(e.getPlayer().getMetadata("God").get(0).asString().equals(godsSign.getLine(1))) {
						Main.updateFP(followerFP,(String)e.getPlayer().getMetadata("God").get(0).value());
						e.getPlayer().sendMessage("Your religion has been gifted " + Integer.toString(followerFP) + "FP!");
					}
					else {
					e.getPlayer().sendMessage("That is not your altar!");
					}
					break;
					
				case 2:
					if(e.getPlayer().getMetadata("God").get(0).asString().equals(godsSign.getLine(1))) {
						Main.updateFP(priestFP,(String)e.getPlayer().getMetadata("God").get(0).value());
						e.getPlayer().sendMessage("Your religion has been gifted " + Integer.toString(priestFP) + "FP!");
					}
					else {
					e.getPlayer().sendMessage("That is not your altar!");
					}
					break;					
				}
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		GodsIO.playerInput(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		GodsIO.playerOutput(player);
		
	}
}
