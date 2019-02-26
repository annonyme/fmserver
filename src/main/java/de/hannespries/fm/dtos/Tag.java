package de.hannespries.fm.dtos;

import lombok.Data;

@Data
public class Tag {
    private String name;
    private String scope;

    public Tag(){

    }

    public Tag(String name, String scope){
        this.name = name;
        this.scope = scope;
    }
}
