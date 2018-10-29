package co.guysanchez.calderaGods;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKit implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		if(sender instanceof Player) {
			if(args.length == 1) {
				displayHelp((Player)sender, 0);
			}
			else {
				if(args[1] == "list") listGods((Player)sender, args);
				if(args[1] == "setpriest") setPriest((Player)sender, args);
				if(args[1] == "admin") adminCommand((Player)sender, args);
			}
		}
		return false;
	}
	private void displayHelp(Player player, int helpMessage) {
	switch(helpMessage) {
	case 0:
		break;
		
	case 1:
		break;
		}
	}
	private void setPriest(Player sender, String[] args) {
		
	}
	private void listGods(Player player, String[] args) {
		
	}

	private void adminCommand(Player sender, String[] args) {
		
		
	}
}
