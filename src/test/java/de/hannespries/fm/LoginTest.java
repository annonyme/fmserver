package de.hannespries.fm;

import de.hannespries.fm.dtos.User;
import de.hannespries.fm.reducers.Login;
import de.hannespries.fm.utils.JSONToolkit;
import de.hannespries.globalstate.Action;
import de.hannespries.globalstate.GlobalState;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LoginTest {

    private String jsonAction = "{\"action\": \"login\", \"payload\": {\"name\": \"zzz\"}}";

    @Test
    public void login(){
        GlobalState state = new GlobalState();
        state.getReducers().add(new Login());

        Action action = new Action("login");
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "zzz");
        action.setPayload(payload);

        Map<String, Object> result = state.action(action);

        Assert.assertTrue(result.containsKey(action.getToken()));
        Assert.assertEquals(1, result.size());

        String name = "";
        if(result.get(action.getToken()) instanceof Map){
            Map artifact = (Map) result.get(action.getToken());
            if(artifact.get("user") instanceof User){
                User user = (User) artifact.get("user");
                name = user.getName();
            }
        }
        Assert.assertEquals("zzz", name);
    }

    @Test
    public void loginJSON() throws Exception{
        GlobalState state = new GlobalState();
        state.getReducers().add(new Login());

        Action action = (Action) JSONToolkit.jsonToObject(this.jsonAction, Action.class);

        Map<String, Object> result = state.action(action);

        Assert.assertTrue(result.containsKey(action.getToken()));
        Assert.assertEquals(1, result.size());

        String name = "";
        if(result.get(action.getToken()) instanceof Map){
            Map artifact = (Map) result.get(action.getToken());
            if(artifact.get("user") instanceof User){
                User user = (User) artifact.get("user");
                name = user.getName();
            }
        }
        Assert.assertEquals("zzz", name);
    }
}
