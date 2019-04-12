package de.hannespries.fm.dtos;

import lombok.Data;

@Data
public class Tag {
    private String name;
    private String scope;
    private String fkUserUUID;

    public Tag(){

    }

    public Tag(String name, String scope, String fkUserUUID){
        this.name = name;
        this.scope = scope;
        this.fkUserUUID = fkUserUUID;
    }
}
