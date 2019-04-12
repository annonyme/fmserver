package de.hannespries.fm.dtos;

import lombok.Data;

@Data
public class Message {
    private String tag;
    private String username;
    private String userPublicId;
    private String text;
}
