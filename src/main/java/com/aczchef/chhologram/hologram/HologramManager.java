package com.aczchef.chhologram.hologram;

import com.aczchef.chhologram.exceptions.HologramException;
import com.laytonsmith.abstraction.MCLocation;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONValue;

/**
 *
 * @author cgallarno
 */
public class HologramManager {
    private static Map<Long, Hologram> holograms = new HashMap<Long, Hologram>();
    private static Map<String, Long> names = new HashMap<String, Long>();
    
    public static void addExistingHologram(Hologram hologram) throws HologramException {
	holograms.put(hologram.getUid(), hologram);
	if (!hologram.getName().equals("")) {
	    if (isNameAvailable(hologram.getName())) {
		names.put(hologram.getName(), hologram.getUid());
	    } else {
		throw new HologramException("Hologram name is taken in current hologram list.");
	    }
	    
	}
    }
    
    // Reserverd
    public static void createHologram(String[] lines, MCLocation location, double alignment, String name) {
	
    }
    
    public static void removeHologram(long id) {
	Hologram hologram = getHologram(id);
	hologram.remove();
	holograms.remove(id);
	if (!hologram.getName().equals("")) {
	    names.remove(hologram.getName());
	}
    }
    
    public static void removeHologram(String name) {
	removeHologram(getHologramId(name));
    }
    
    public static Hologram getHologram(long id) {
	return holograms.get(id);
    }
    
    public static Hologram getHologram(String name) {
	return holograms.get(names.get(name));
    }
    
    public static long getHologramId(String name) {
	return names.get(name);
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
