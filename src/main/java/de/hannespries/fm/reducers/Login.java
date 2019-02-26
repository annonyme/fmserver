package de.hannespries.fm.reducers;

import de.hannespries.fm.dtos.User;
import de.hannespries.globalstate.Action;
import de.hannespries.globalstate.Reducer;

import java.util.*;

public class Login implements Reducer {
    public String getAction() {
        return "login";
    }

    public boolean reduce(Action action, Map<String, Object> map) {
        if(map.containsKey(action.getToken())){
            map.remove(action.getToken());
        }
        if(action.getPayload() != null && action.getPayload().containsKey("name")){
            User user = new User();
            user.setToken(action.getToken());
            user.setName(action.getPayload().get("name").toString());
            user.setUuid(UUID.randomUUID().toString());
            user.setLoginDate((new GregorianCalendar()).getTimeInMillis());


            Map<String, Object> artifact = new HashMap<>();
            artifact.put("user", user);
            artifact.put("tags", new ArrayList<>());
            artifact.put("hits", new ArrayList<>());
            map.put(action.getToken(), artifact);
        }
        return map.containsKey(action.getToken());
    }
}
