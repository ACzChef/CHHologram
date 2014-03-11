package com.aczchef.chhologram.hologram;

import com.aczchef.chhologram.exceptions.HologramException;
import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.MCFireball;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.Velocity;
import com.laytonsmith.abstraction.entities.MCHorse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.entity.Horse;
import org.bukkit.entity.WitherSkull;

/**
 *
 * @author cgallarno
 */
public class Hologram {
    private static long id = 0;
    private String[] lines;
    private MCLocation location;
    private List<MCEntity[]> entities;
    private double alignment;
    private long uid;
    private String name;

    /**
     * Used to create a new hologram into the server
     * @param lines
     * @param location
     * @param alignment
     */
    public Hologram(String[] lines, MCLocation location, double alignment, String name) {
	this.lines = lines;
	this.location = location;
	this.alignment = alignment;
	this.entities = new ArrayList<MCEntity[]>();
	location.add(0, 57, 0);
	for (int i = lines.length - 1; i >= 0; i--) {
	    String line = lines[i];
	    MCFireball witherSkull = (MCFireball) location.getWorld().spawn(location, WitherSkull.class);
	    MCHorse horse = (MCHorse) location.getWorld().spawn(location, Horse.class);
	    if (witherSkull == null || horse == null) {
		throw new NullPointerException("Error spawning entities");
	    }
	    witherSkull.setPassenger(horse);
	    witherSkull.setDirection(new Velocity(0, 0, 0));
	    horse.setAge(-1700000);
	    horse.setAgeLock(true);
	    horse.setCustomNameVisible(true);
	    horse.setCustomName(line);
	    location.add(0, alignment, 0);
	    MCEntity[] entityPair = new MCEntity[2];
	    entityPair[0] = horse;
	    entityPair[1] = witherSkull;
	    this.entities.add(entityPair);
	}
	this.uid = id++;
	this.name = name;
	try {
	    HologramManager.addExistingHologram(this);
	} catch (HologramException ex) {
	    System.out.println("An error occured creating the hologram. Any created entites were removed.");
	    System.out.println(ex);
	    for (int i = 0; i < entities.size(); i++) {
		MCEntity[] entityPair = entities.get(i);
		entityPair[0].remove();
		entityPair[1].remove();
	    }
	}
    }

    public Hologram(String[] lines, MCLocation location, double alignment) {
	this(lines, location, alignment, "");
    }

    public Hologram(String[] lines, MCLocation location, String name) {
	this(lines, location, 0.3, name);
    }
    
    public Hologram(String[] lines, MCLocation location) {
	this(lines, location, 0.3);
    }

    public long getUid() {
	return uid;
    }

    public String getName() {
	return name;
    }
    
    public void setLines(String[] lines) {
	
    }
    
    public void remove() {
	for (Iterator<MCEntity[]> it = entities.iterator(); it.hasNext();) {
	    MCEntity[] entityPair = it.next();
	    entityPair[0].remove();
	    entityPair[1].remove();
	}
    }
}
