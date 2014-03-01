/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aczchef.chhologram;

import com.aczchef.chhologram.config.HologramConfig;
import com.laytonsmith.PureUtilities.Common.StringUtils;
import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author cgallarno
 */
@MSExtension("CHHologram")
public class LifeCycle extends AbstractExtension {

    public Version getVersion() {
	return new SimpleVersion(0, 1, 0);
    }

    @Override
    public void onStartup() {
	File file;
        URL url = LifeCycle.class.getResource("/" + LifeCycle.class.getName().replace(".", "/") + ".class");
        String s = url.toString();
	s = s.replaceFirst("jar:file:", "");
	s = StringUtils.replaceLast(s, Pattern.quote(LifeCycle.class.getName().replace(".", "/") + ".class"), "");
        file = new File(s + "../CHHologram.ini");
	try {
	    file = new File(file.getCanonicalPath());
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
