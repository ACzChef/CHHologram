package com.aczchef.chhologram.extension;

import com.aczchef.chhologram.CHHologram;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;

/**
 *
 * @author cgallarno
 */
@MSExtension("CHHologram")
public class LifeCycle extends AbstractExtension {
    private static LifeCycle lifeCycle = null;
    public Version getVersion() {
	return HologramVersion.V0_0_1;
    }
    
    public static LifeCycle getLifeCycle() {
	return lifeCycle;
    }
    
    @Override
    public void onStartup() {
	lifeCycle = this;
	CHHologram.createDefaultInstance();
	System.out.println("[CommandHelper] CHHologram Initialized - ACzChef");
    }

    @Override
    public void onShutdown() {
	System.out.println("[CommandHelper] CHHologram De-Initialized - ACzChef");
    }
}
