/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aczchef.chhologram.config;

import com.laytonsmith.PureUtilities.Preferences;
import com.laytonsmith.core.Static;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cgallarno
 */
public class HologramConfig {
    private static Preferences prefs;
    
    public static void init(File f) throws IOException {
	List<Preferences.Preference> def = new ArrayList<Preferences.Preference>();
	def.add(new Preferences.Preference("Unique-storage", "false", Preferences.Type.BOOLEAN, "Whether to use 'Server-name' when storing holograms or not. RECOMENDED IF YOU SHARE A DATABASE WITH MULTIPLE SERVERS."));
	def.add(new Preferences.Preference("Server-name", Static.getServer().getServerName(), Preferences.Type.STRING, "The name to make theese holograms stored seperately to this server."));
	prefs = new Preferences("CHHologram", Static.getLogger(), def);
	prefs.init(f);
    }
    
    public static String getUniqueName() {
	return (String) prefs.getPreference("Server-name");
    }
    
    public boolean storeUnique() {
	return (Boolean) prefs.getPreference("Unique-storage");
    }
}