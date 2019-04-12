package de.hannespries.fm.dtos;

import de.hannespries.globalstate.defaults.filters.CoordsDistanceInterface;
import lombok.Data;

@Data
public class GeoLocation implements CoordsDistanceInterface {
    private double longCoord;
    private double lat;

    public double getLong() {
        return longCoord;
    }

    public void setLong(double longCoord) {
        this.longCoord = longCoord;
    }
}
