package com.henryrenyz.creatiolib.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.henryrenyz.creatiolib.plugins.API_CoreProtect;
import com.sun.istack.internal.Nullable;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;

public class EffCP_logRemoval extends Effect {

    static {
        Skript.registerEffect(EffCP_logRemoval.class, "log [core[ ]protect] [block] removal [registered] as %string% at %location% broke [a] %string% [[with] [block]data %-blockdata%]");
    }

    private Expression<String> string;
    private Expression<Location> location;
    private Expression<String> material;
    private Expression<BlockData> blockdata;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.string = (Expression<String>) expressions[0];
        this.location = (Expression<Location>) expressions[1];
        this.material = (Expression<String>) expressions[2];
        this.blockdata = (Expression<BlockData>) expressions[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "log [core[ ]protect] [block] removal [registered] as %string% at %location% broke [a] %material% [[with] [block]data %-blockdata%]";
    }

    @Override
    protected void execute(Event event) {
        if (string == null)  return;
        if (location == null)  return;
        if (material == null)  return;

        CoreProtectAPI CoreProtect = API_CoreProtect.getCoreProtect();
        if (CoreProtect == null)  return;
        else {
            BlockData data = null;
            Material mat = Material.getMaterial(material.getSingle(event));
            if (blockdata != null) {
                data = blockdata.getSingle(event);
            }
            CoreProtect.logRemoval(string.getSingle(event), location.getSingle(event), mat, data);
        }
    }
}

