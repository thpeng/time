package ch.thp.proto.config.domain;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import lombok.Getter;
import lombok.Setter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thierry
 */
@Entity
@Getter
@Setter
@NamedQuery(name="configEntry.getByKey",query="Select x from ConfigEntry x where x.configKey =:key")
public class ConfigEntry implements Serializable{
    
    @Id
    private String uuId; 
    private String configKey; 
    private String configValue; 
    private String description;

    public ConfigEntry(String configKey, String configValue, String description) {
        this.configKey = configKey;
        this.configValue = configValue;
        this.description = description;
    }

    public ConfigEntry() {
    }
}