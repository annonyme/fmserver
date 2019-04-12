package de.hannespries.fm.reducers;

import de.hannespries.fm.dtos.Hit;
import de.hannespries.fm.dtos.Tag;
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

    private Map<String, Tag> tagMap = new HashMap<>();

    private void createHits(Map<String, Object> current, Map<String, Object> state, Action action){
        List<Hit> hitList =  new ArrayList<>();

        //TODO check against hits to filter also geolocation
        Iterator<String> it = this.tagMap.keySet().iterator();
        while(it.hasNext()){
            try{
                String key = it.next();
                if(!key.equals(action.getToken())){
                    Hit hit = new Hit();
                    hit.setTag(new Tag(this.tagMap.get(key).getName(), this.tagMap.get(key).getScope(), key));
                    hit.setUserName(StateQuery.query(key + ".user.name", state).toString());
                    hit.setPublicUUID(StateQuery.query(key + ".user.publicId", state).toString());

                    hitList.add(hit);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        current.put("hits", hitList);
    }

    @Override
    public boolean reduce(Action action, Map<String, Object> map) {
        Map<String, List<Object>> filter = new HashMap<>();

        this.tagMap.clear();
        List<Object> valuesAndFilter = new ArrayList<>();
        valuesAndFilter.add(new TagFilter(action, this.tagMap));

        filter.put("tags", valuesAndFilter);
        Map<String, Object> hits = StateQuery.filter(filter, map);

        Object currentObj = StateQuery.query(action.getToken(), map);
        if(currentObj instanceof Map){
            this.createHits((Map<String, Object>) currentObj, map, action);
        }

        return hits.size() > 0 && currentObj != null;
    }
}
