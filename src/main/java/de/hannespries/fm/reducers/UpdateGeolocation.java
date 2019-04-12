package de.hannespries.fm.reducers;

import de.hannespries.fm.dtos.GeoLocation;
import de.hannespries.globalstate.Action;
import de.hannespries.globalstate.Reducer;
import de.hannespries.globalstate.StateQuery;

import java.util.Map;

public class UpdateGeolocation implements Reducer {
    @Override
    public String getAction() {
        return "updategeo";
    }

    @Override
    public boolean reduce(Action action, Map<String, Object> map) {
        GeoLocation geo = new GeoLocation();
        geo.setLat(Double.parseDouble(action.getPayload().get("lat").toString()));
        geo.setLat(Double.parseDouble(action.getPayload().get("long").toString()));

        try{
            Object currentObj = StateQuery.query(action.getToken(), map);
            if(currentObj instanceof Map){
                Map current = (Map) currentObj;
                current.put("geo", geo);
            }
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
