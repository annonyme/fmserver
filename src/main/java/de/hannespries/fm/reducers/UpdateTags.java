package de.hannespries.fm.reducers;

import de.hannespries.fm.dtos.Tag;
import de.hannespries.globalstate.Action;
import de.hannespries.globalstate.Reducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateTags implements Reducer {
    @Override
    public String getAction() {
        return "updatetags";
    }

    @Override
    public boolean reduce(Action action, Map<String, Object> map) {
        boolean result = false;
        try{
            if(action.getPayload().containsKey("tags") && map.containsKey(action.getToken())){
                if(map.get(action.getToken()) instanceof Map && action.getPayload().get("tags") instanceof List){
                    Map artifact = (Map) map.get(action.getToken());
                    List<Tag> list = new ArrayList<>();
                    for(Object item: (List) action.getPayload().get("tags")){
                        if(item instanceof Map){
                            Map tag = (Map) item;
                            if(tag.containsKey("name") && tag.containsKey("scope")){
                                list.add(new Tag(tag.get("name").toString(), tag.get("scope").toString()));
                            }
                        }
                    }
                    artifact.put("tags", list);
                }
            }
        }
        catch(Exception e){

        }
        return result;
    }
}
