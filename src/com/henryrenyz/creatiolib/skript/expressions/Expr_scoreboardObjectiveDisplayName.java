package com.henryrenyz.creatiolib.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.sun.istack.internal.Nullable;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Objective;

public class Expr_scoreboardObjectiveDisplayName extends SimpleExpression<String> {

    static {
        Skript.registerExpression(Expr_scoreboardObjectiveDisplayName.class, String.class, ExpressionType.COMBINED, "[the] display name of scoreboard %objective%");
    }

    private Expression<Objective> obj;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        obj = (Expression<Objective>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "[the] display name of scoreboard %objective%";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        return new String[] {obj.getSingle(event).getDisplayName()};
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            obj.getSingle(event).setDisplayName(delta[0].toString());
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}