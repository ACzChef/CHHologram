package com.aczchef.chhologram;

import com.aczchef.chhologram.config.HologramConfig;
import com.aczchef.chhologram.extension.LifeCycle;
import com.aczchef.chhologram.hologram.HologramManager;
import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.core.MethodScriptFileLocations;
import com.laytonsmith.core.Static;
import com.laytonsmith.persistence.DataSourceException;
import com.laytonsmith.persistence.PersistenceNetwork;
import com.laytonsmith.persistence.io.ConnectionMixinFactory;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author cgallarno
 */
public class CHHologram {
    private PersistenceNetwork persistenNetwork;
    private HologramConfig configOptions;
    // Reserved
    private HologramManager holograManager;
    private static CHHologram defaultInstance;

    public CHHologram(PersistenceNetwork persistenceNetwork, HologramConfig config) {
	
    }

    
    
    public static void createDefaultInstance() {
	// Get CommandHelper Main Persistence Network.
	PersistenceNetwork persistenceNetwork = null;
	File jarLocation = new File(Static.class.getProtectionDomain().getCodeSource().getLocation().getFile()).getParentFile();
	File platformFolder = new File(jarLocation, Implementation.GetServerType().getBranding() + "/");
	ConnectionMixinFactory.ConnectionMixinOptions options = new ConnectionMixinFactory.ConnectionMixinOptions();
	options.setWorkingDirectory(platformFolder);
	try {
	    persistenceNetwork = new PersistenceNetwork(MethodScriptFileLocations.getDefault().getPersistenceConfig(),
		    new URI("sqlite://" + new File(platformFolder, "persistence.db").getCanonicalPath().replace("\\", "/")), options);
	} catch (IOException ex) {
	    System.out.println(ex);
	} catch (DataSourceException ex) {
	     System.out.println(ex);
	} catch (URISyntaxException ex) {
	     System.out.println(ex);
	}
	
	// Get The Default CHHologram Config.
	File file = new File(LifeCycle.getLifeCycle().getConfigDir(), "CHHologram.ini");
	HologramConfig hologramConfig = null;
	try {
	    file = file.getCanonicalFile();
	    hologramConfig = new HologramConfig(file);
	} catch (IOException ex) {
	    System.out.println(ex);
	}
	
	defaultInstance = new CHHologram(persistenceNetwork, hologramConfig);
    }
    
    public static CHHologram getDefaultInsance() {
	return defaultInstance;
    }
    
    public HologramConfig getConfig() {
	return this.configOptions;
    }
    
    public PersistenceNetwork getPersistenceNetwork() {
	return this.persistenNetwork;
    }
}
