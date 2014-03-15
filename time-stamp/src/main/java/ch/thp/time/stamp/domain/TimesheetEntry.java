/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.thp.time.stamp.domain;

import ch.thp.proto.time.user.domain.UserId;
import ch.thp.time.utilties.database.DurationConverter;
import ch.thp.time.utilties.database.LocalDateConverter;
import java.io.Serializable;
import javax.enterprise.inject.Vetoed;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

/**
 *
 * @author Thierry
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Vetoed
@NamedQuery(name = "timesheet.forUserId", query = "SELECT t FROM TimesheetEntry t where t.userId = :id")
public class TimesheetEntry implements Serializable {
    
    @EmbeddedId
    private TimesheetEntryId Id;
    private UserId userId; 
    @Convert(converter = DurationConverter.class)
    private Duration duration; 
    @Convert(converter = LocalDateConverter.class)
    private LocalDate entryDate; 
    private String description;
    
    
}
