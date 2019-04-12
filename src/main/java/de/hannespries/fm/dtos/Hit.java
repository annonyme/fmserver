package de.hannespries.fm.dtos;

import lombok.Data;

@Data
public class Hit {
    private String publicUUID;
    private String userName;
    private Tag tag;
    //messages
}
