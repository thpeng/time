package ch.thp.proto.time.user.domain;

import java.io.Serializable;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Thierry
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Vetoed
@Entity
@Table(name = "TIME_GROUP")
public class Group implements Serializable{
    @Id
    private String uuId; 
    private String name; 
    private String description; 
    
}