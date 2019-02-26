package de.hannespries.fm;

import de.hannespries.fm.filters.TagFilter;
import de.hannespries.fm.reducers.Login;
import de.hannespries.fm.reducers.UpdateTags;
import de.hannespries.fm.utils.JSONToolkit;
import de.hannespries.globalstate.Action;
import de.hannespries.globalstate.GlobalState;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class QueryTest {
    private String jsonAction = "{\"action\": \"query\", " +
            "\"payload\": {" +
            "       \"tags\": [" +
            "           {\"name\": \"dummy\", \"scope\": \"peer\"}," +
            "           {\"name\": \"blubb\", \"scope\": \"offer\"}," +
            "           {\"name\": \"urks\", \"scope\": \"peer\"}" +
            "       ]" +
            "   }" +
            "}";

    private String jsonActionLogin = "{\"action\": \"login\", \"payload\": {\"name\": \"zzz\"}}";

    private String jsonActionUpdate = "{\"action\": \"updatetags\", " +
            "\"payload\": {" +
            "       \"tags\": [" +
            "           {\"name\": \"dummy\", \"scope\": \"peer\"}," +
            "           {\"name\": \"blubb\", \"scope\": \"offer\"}," +
            "           {\"name\": \"urks\", \"scope\": \"peer\"}" +
            "       ]" +
            "   }" +
            "}";

    @Test
    public void createSearchList() throws  Exception{
        Action action = (Action) JSONToolkit.jsonToObject(this.jsonAction, Action.class);

        TagFilter filter = new TagFilter(action);
        List<String> result = filter.createCheckList(action);

        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains("blubb:search"));
        Assert.assertTrue(result.contains("dummy:peer"));
        Assert.assertTrue(result.contains("urks:peer"));
    }

    @Test
    public void updateTags() throws Exception{
        GlobalState state = new GlobalState();
        state.getReducers().add(new Login());
        state.getReducers().add(new UpdateTags());

        Action action = (Action) JSONToolkit.jsonToObject(this.jsonActionLogin, Action.class);
        state.action(action);
        Action actionUpdate = (Action) JSONToolkit.jsonToObject(this.jsonActionUpdate, Action.class);
        actionUpdate.setToken(action.getToken());
        Map<String, Object> result = state.action(actionUpdate);

        Assert.assertEquals(1, result.size());

        int count = -1;
        if(result.get(action.getToken()) instanceof Map){
            Map artifact = (Map) result.get(action.getToken());
            if(artifact.get("tags") instanceof List){
                List tags = (List) artifact.get("tags");
                count = tags.size();
            }
        }
        Assert.assertEquals(3, count);
    }

    public void findHits() throws Exception{

    }

}
