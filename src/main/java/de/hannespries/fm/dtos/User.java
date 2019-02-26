package de.hannespries.fm.dtos;

import lombok.Data;

@Data
public class User {
    private String token;
    private String name;
    private String uuid;
    private long loginDate;
}
