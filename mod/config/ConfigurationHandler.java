package mod.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import mod.items.ModItemInfo;
import net.minecraftforge.common.Configuration;

public class ConfigurationHandler {
	
	private static File cfg;
	
	public static void init(File file) {
		
		cfg = file;
		
		Configuration config = new Configuration(file);
		config.load();

		ModItemInfo.BASEITEM_ID = config.getItem(ModItemInfo.BASEITEM_KEY, ModItemInfo.BASEITEM_DEFAULT).getInt();
		ModItemInfo.BLADE_ID = config.getItem(ModItemInfo.BLADE_KEY, ModItemInfo.BLADE_DEFAULT).getInt();
		
		config.save();
	}

	/**
	 * Sets a config value manually by editing the text file
	 * @param prefix - The prefix of the config option (anythin before the '=')
	 * @param from - The setting to change it from 
	 * @param to - The setting to change it to
	 */
	public static void manuallyChangeConfigValue(String prefix, String from, String to)
	{
		try
		{
			FileReader fr1 = new FileReader(cfg);
			BufferedReader read = new BufferedReader(fr1);
			
			ArrayList<String> strings = new ArrayList<String>();
			
			while (read.ready())
			{
				strings.add(read.readLine());
			}
			
			fr1.close();
			read.close();
			
			FileWriter fw = new FileWriter(cfg);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (String s : strings)
			{
				if (s.equals("    " + prefix + "=" + from))
					s = "    " + prefix + "=" + to;
				
				fw.write(s + "\n");
			}	
			
			bw.flush();
			bw.close();
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
}
