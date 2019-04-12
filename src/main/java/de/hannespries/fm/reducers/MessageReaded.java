package de.hannespries.fm.reducers;

import de.hannespries.globalstate.Action;
import de.hannespries.globalstate.Reducer;

import java.util.Map;

public class MessageReaded implements Reducer {

    @Override
    public String getAction() {
        return "messagereaded";
    }

    @Override
    public boolean reduce(Action action, Map<String, Object> map) {
        return false;
    }
}
