package com.aczchef.chhologram;

import com.aczchef.chhologram.exceptions.HologramException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONValue;

/**
 *
 * @author cgallarno
 */
public class HologramManager {
    private static Map<Integer, Hologram> holograms = new HashMap<Integer, Hologram>();
    private static Map<String, Integer> names = new HashMap<String, Integer>();
    
    public static void addHologram(Hologram hologram) throws HologramException {
	holograms.put(hologram.getUid(), hologram);
	if (!hologram.getName().equals("")) {
	    if (isNameAvailable(hologram.getName())) {
		names.put(hologram.getName(), hologram.getUid());
	    } else {
		throw new HologramException("Hologram name is taken in current hologram list.");
	    }
	    
	}
    }
    
    public static Hologram getHologram(Integer id) {
	return holograms.get(id);
    }
    
    public static Hologram getHologram(String name) {
	return holograms.get(names.get(name));
    }
    
    public static void loadHolograms() {
	
    }
    
    public static void storeHolograms() {
	String JSONHolograms = JSONValue.toJSONString(holograms);
	String JSONNames = JSONValue.toJSONString(names);
	//check database state
	//check config settings
	//store
    }
    
    public static boolean isNameAvailable(String name) {
	if(names.containsKey(name)) {
	    return false;
	} else {
	    return true;
	}
    }
}
