/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aczchef.chhologram;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.extensions.AbstractExtension;

/**
 *
 * @author cgallarno
 */
public class LifeCycle extends AbstractExtension {

    public Version getVersion() {
	return new SimpleVersion(0, 1, 0);
    }

    @Override
    public void onStartup() {
	System.out.println("[CommandHelper] CHHologram Initialized - ACzChef");
    }

    @Override
    public void onShutdown() {
	System.out.println("[CommandHelper] CHHologram De-Initialized - ACzChef");
    }
}
