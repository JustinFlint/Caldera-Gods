package co.guysanchez.calderaGods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class GodsIO {
	//Reads and sets the metadata for all players with religions
	public static void playerInput(Player player) {
		try(BufferedReader reader = new BufferedReader(new FileReader
				(new File("plugins\\CalderaGods\\players\\" + player.getUniqueId().toString() + ".txt")))) {
			String rank = reader.readLine();
			player.setMetadata("ReligiousStatus", new FixedMetadataValue(Main.getInstance(), Integer.parseInt(rank)));
			String godName = "";
			int input;
			while((input = reader.read()) != -1) {
				godName += (char)input;
			}
			if(godName != "") {
				player.setMetadata("God", new FixedMetadataValue(Main.getInstance(), godName));
			} 
		}
		catch (FileNotFoundException e) {
			player.setMetadata("ReligiousStatus", new FixedMetadataValue(Main.getInstance(), 0));
			playerOutput(player);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	//Writes all player metadata for permanent storage
	public static void playerOutput(Player player) {
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("plugins\\CalderaGods\\Players\\" + player.getUniqueId().toString() + ".txt")))) {
				writer.write(player.getMetadata("ReligiousStatus").get(0).asString());
				writer.newLine();
				if(player.hasMetadata("God")){
					writer.write(player.getMetadata("God").get(0).asString());
				}
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	//Pulls in a list of current gods
	public static List<String> godInput() {
		List<String> gods = new ArrayList<String>();
		try(BufferedReader reader = new BufferedReader(new FileReader
				(new File("plugins\\CalderaGods\\gods.txt")))) {
			String god;
			while((god = reader.readLine()) != null) {
				gods.add(god);
			}
		} 
		catch (FileNotFoundException e) {
			try {
				new File("plugins\\CalderaGods\\gods.txt").createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return gods;
	}
	//Updates list of all gods
	public static void godOutput() {
		List<String> gods = Main.godsList;
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("plugins\\\\CalderaGods\\\\gods.txt")))) {
			for(String god: gods) {
				writer.write(god);
				writer.newLine();
			}
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//Pulls in a list of faith points with an identical index to the gods list
	public static List<Integer> fpInput(){
		List<Integer> fps = new ArrayList<Integer>();
		try(BufferedReader reader = new BufferedReader(new FileReader
				(new File("plugins\\CalderaGods\\faith-points.txt")))) {
			String fp;
			while((fp = reader.readLine()) != null) {
				fps.add(Integer.parseInt(fp));
			}
		} 
		catch (FileNotFoundException e) {
			try {
				new File("plugins\\CalderaGods\\faith-points.txt").createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return fps;
	}
	//Exports a list of faith points
	public static void fpOutput() {
		List<Integer> fps = Main.fpList;
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("plugins\\\\CalderaGods\\\\faith-points.txt")))) {
			for(int fp: fps) {
				writer.write(Integer.toString(fp));
				writer.newLine();
			}
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//Pulls in a list of current priests
	public static List<UUID> priestInput() {
			List<UUID> priests = new ArrayList<UUID>();
			try(BufferedReader reader = new BufferedReader(new FileReader
					(new File("plugins\\CalderaGods\\priestss.txt")))) {
				String priest;
				while((priest = reader.readLine()) != null) {
					priests.add(UUID.fromString(priest));
				}
			} 
			catch (FileNotFoundException e) {
				try {
					new File("plugins\\CalderaGods\\priests.txt").createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return priests;
		}
	//Updates list of all priests
	public static void priestOutput() {
		List<UUID> priests = Main.priestList;
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("plugins\\CalderaGods\\priest.txt")))) {
			for(UUID priest: priests) {
				writer.write(priest.toString());
				writer.newLine();
			}
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
}
