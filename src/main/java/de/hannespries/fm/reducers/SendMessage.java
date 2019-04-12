package de.hannespries.fm.reducers;

import de.hannespries.globalstate.Action;
import de.hannespries.globalstate.Reducer;

import java.util.Map;

public class SendMessage implements Reducer {
    @Override
    public String getAction() {
        return "sendmessage";
    }

    @Override
    public boolean reduce(Action action, Map<String, Object> map) {
        return false;
    }
}
