package de.hannespries.fm.dtos;

import de.hannespries.globalstate.defaults.filters.CoordsDistanceInterface;
import lombok.Data;

@Data
public class User {
    private String token;
    private String name;
    private String uuid;
    private String publicId;
    private long loginDate;
}
