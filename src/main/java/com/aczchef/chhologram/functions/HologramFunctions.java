package com.aczchef.chhologram.functions;

import com.aczchef.chhologram.hologram.Hologram;
import com.aczchef.chhologram.hologram.HologramManager;
import com.aczchef.chhologram.extension.HologramVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CDouble;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.functions.Exceptions;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cgallarno
 */
public class HologramFunctions {
    @api
    public static class spawn_hologram extends AbstractFunction {

	public Exceptions.ExceptionType[] thrown() {
	    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public boolean isRestricted() {
	    return true;
	}

	public Boolean runAsync() {
	    return false;
	}

	public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
	    CArray CLines = (CArray) args[0];
	    MCLocation location = environment.getEnv(CommandHelperEnvironment.class).GetPlayer().getLocation();
	    double alignment = 0.3;
	    //Location array or Alignment passed in
	    if(args.length == 2) {
		if (args[1] instanceof CArray) {
		    location = ObjectGenerator.GetGenerator().location((CArray) args[1], null, t);
		} else {
		    alignment = ((CDouble) args[1]).getDouble();
		}
	    //All 3 Lines, Location, Alignment
	    } else if(args.length == 3) {
		location = ObjectGenerator.GetGenerator().location((CArray) args[1], null, t);
		alignment = ((CDouble) args[2]).getDouble();
	    }
	    
	    List<String> lines = (ArrayList<String>) Construct.GetPOJO(CLines);
	    new Hologram(lines.toArray(new String[0]), location, alignment);
	    return new CVoid(t);
	}

	public String getName() {
	    return "spawn_hologram";
	}

	public Integer[] numArgs() {
	    return new Integer[] {1, 2, 3};
	}

	public String docs() {
	    return "";
	}

	public Version since() {
	    return HologramVersion.V0_0_1;
	}
    }
    
    @api
    public static class remove_hologram extends AbstractFunction {

	public Exceptions.ExceptionType[] thrown() {
	    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public boolean isRestricted() {
	    return true;
	}

	public Boolean runAsync() {
	    return false;
	}

	public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
	    if (args[0] instanceof CString) {
		HologramManager.removeHologram(args[0].val());
	    } else if(args[0] instanceof CInt) {
		HologramManager.removeHologram( ((CInt) args[0]).getInt());
	    }
	    return new CVoid(t);
	}

	public String getName() {
	    return "remove_hologram";
	}

	public Integer[] numArgs() {
	    return new Integer[] {1};
	}

	public String docs() {
	    return "";
	}

	public Version since() {
	    return HologramVersion.V0_0_1;
	}
	
    }
}
