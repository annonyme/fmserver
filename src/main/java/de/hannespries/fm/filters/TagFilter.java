package de.hannespries.fm.filters;

import de.hannespries.fm.dtos.Tag;
import de.hannespries.globalstate.Action;
import de.hannespries.globalstate.FilterOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TagFilter extends FilterOperator {
    private Action action;

    public TagFilter(Action action){
        this.action = action;
    }

    public List<String> createCheckList(Action action){
        List<String> result = new ArrayList<>();
        if(action.getPayload().containsKey("tags") && action.getPayload().get("tags") instanceof List){
            List items = (List) action.getPayload().get("tags");
            for (Object item: items) {
                if(item instanceof Map){
                    Map tag = (Map) item;
                    if(tag.containsKey("name") && tag.containsKey("scope")){
                        result.add(tag.get("name").toString() + ":" + this.invertScope(tag.get("scope").toString()));
                    }
                }
            }
        }
        return result;
    }

    private String invertScope(String scope){
        String result = scope;
        if(scope.equalsIgnoreCase("search")){
            result = "offer";
        }
        else if(scope.equalsIgnoreCase("offer")){
            result = "search";
        }
        return result;
    }

    @Override
    public boolean check(Object toCheckValue) {
        boolean result = false;
        List<String> checkList = this.createCheckList(this.action);
        if(toCheckValue instanceof List && checkList.size() > 0){
            List items = (List) toCheckValue;
            for (Object item: items) {
                if(item instanceof Tag){
                    Tag tag = (Tag) item;
                    if(checkList.contains(tag.getName() + ":" + tag.getScope())){
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}
