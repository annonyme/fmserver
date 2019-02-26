package de.hannespries.fm.reducers;

import de.hannespries.fm.dtos.Hit;
import de.hannespries.fm.filters.TagFilter;
import de.hannespries.globalstate.Action;
import de.hannespries.globalstate.Reducer;
import de.hannespries.globalstate.StateQuery;

import java.util.*;

public class Query implements Reducer {
    @Override
    public String getAction() {
        return "query";
    }

    private void createHits(Map<String, Object> current, Map<String, Object> hits){
        if(!current.containsKey("hits")){
            current.put("hits", new ArrayList<>());
        }

        for(String key: hits.keySet()){
            Object item = hits.get(key);
            if(item instanceof Map){
                Map artifact = (Map) item;



            }
        }
    }

    @Override
    public boolean reduce(Action action, Map<String, Object> map) {
        Map<String, List<Object>> filter = new HashMap<>();
        List<Object> values = new ArrayList<>();
        values.add(new TagFilter(action));
        filter.put("tags", values);
        Map<String, Object> hits = StateQuery.filter(filter, map);

        Map<String, Object> current = StateQuery.filterById(action.getToken(), map);
        if(current.get(action.getToken()) instanceof Map){
            this.createHits((Map) current.get(action.getToken()), hits);
        }

        return hits.size() > 0 && current.size() > 0;
    }
}
