package com.aczchef.chhologram.extension;

import com.aczchef.chhologram.config.HologramConfig;
import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.core.MethodScriptFileLocations;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import com.laytonsmith.persistence.DataSourceException;
import com.laytonsmith.persistence.PersistenceNetwork;
import com.laytonsmith.persistence.io.ConnectionMixinFactory;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cgallarno
 */
@MSExtension("CHHologram")
public class LifeCycle extends AbstractExtension {

    public Version getVersion() {
	return HologramVersion.V0_0_1;
    }
    @Override
    public void onStartup() {
	
	
//	File platformFolder = new File(File(this.getConfigDir(), "CHHologram.jar");, Implementation.GetServerType().getBranding() + "/");
//	ConnectionMixinFactory.ConnectionMixinOptions options = new ConnectionMixinFactory.ConnectionMixinOptions();
//	options.setWorkingDirectory(platformFolder);
//	try {
//	    PersistenceNetwork persistenceNetwork = new PersistenceNetwork(MethodScriptFileLocations.getDefault().getPersistenceConfig(),
//		    new URI("sqlite://" + new File(platformFolder, "persistence.db").getCanonicalPath().replace("\\", "/")), options);
//	} catch (IOException ex) {
//	    Logger.getLogger(LifeCycle.class.getName()).log(Level.SEVERE, null, ex);
//	} catch (DataSourceException ex) {
//	    Logger.getLogger(LifeCycle.class.getName()).log(Level.SEVERE, null, ex);
//	} catch (URISyntaxException ex) {
//	    Logger.getLogger(LifeCycle.class.getName()).log(Level.SEVERE, null, ex);
//	}
	
	File file = new File(this.getConfigDir(), "CHHologram.ini");
	try {
	    file = file.getCanonicalFile();
	    HologramConfig.init(file);
	} catch (IOException ex) {
	    System.out.println(ex);
	}
	System.out.println("[CommandHelper] CHHologram Initialized - ACzChef");
    }

    @Override
    public void onShutdown() {
	System.out.println("[CommandHelper] CHHologram De-Initialized - ACzChef");
    }
}
