package com.aczchef.chhologram.extension;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;

/**
 *
 * @author cgallarno
 */
public enum HologramVersion implements Version {
    V0_0_1("0.0.1");
    final SimpleVersion version;
    
    private HologramVersion(String version){
        this.version = new SimpleVersion(version);
    }
    
    public int getMajor() {
	return this.version.getMajor();
    }

    public int getMinor() {
	return this.version.getMinor();
    }

    public int getSupplemental() {
	return this.version.getSupplemental();
    }

    public boolean lt(Version other) {
	return this.version.lt(other);
    }

    public boolean lte(Version other) {
	return this.version.lte(other);
    }

    public boolean gt(Version other) {
	return this.version.gt(other);
    }

    public boolean gte(Version other) {
	return this.version.gte(other);
    }
    
}
