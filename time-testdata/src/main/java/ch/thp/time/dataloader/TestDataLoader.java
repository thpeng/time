/*
 * Copyright 2013 Thierry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.thp.time.dataloader;

import ch.thp.proto.time.hash.Utils;
import ch.thp.proto.time.user.domain.Group;
import ch.thp.proto.time.user.domain.User;
import ch.thp.proto.time.user.domain.UserId;
import ch.thp.time.stamp.domain.TimesheetEntry;
import ch.thp.time.stamp.domain.TimesheetEntryId;
import com.google.common.collect.Sets;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

/**
 * Startup bean, adds some data into the application. Uses an inmemory h2 database.
 * create here new users, testdata and userstories, 
 * @author Thierry
 */
@Singleton
@LocalBean
@Startup
public class TestDataLoader {
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        //reminder.. roles != groups
        //mapping of groups into roles made with the glassfish-web-app.xml
        Group applicationUsers = new Group(UUID.randomUUID().toString(), "standard", "can use the application");
        Group applicationAdmins = new Group(UUID.randomUUID().toString(), "admin", "can use the application");
        Group appSuperUsers = new Group(UUID.randomUUID().toString(), "super", "can use the application");
        em.persist(applicationUsers);
        em.persist(applicationAdmins);
        em.persist(appSuperUsers);
        
        //unfortunately we have to provide a view for the glassfish. 
        //glassfish may handle 1 or 2 tables for auth but with this manytomany we need 3
        em.createNativeQuery("CREATE VIEW `V_USER_GROUP` AS"
                + "SELECT  u.username, u.password, g.groupname"
                + " FROM `USER_GROUPS` ug"
                + " INNER JOIN `TIME_USER` u ON u.userId = ug.TIME_USER_userId"
                + " INNER JOIN `TIME_GROUPS` g ON g.groupId =  ug.groups_groupId; ").executeUpdate();
        //check the src/main/serverconfig for the corresponding domain.xml
        
        //add some user 
        User userone = new User(new UserId("1111-2222"), "ned.stark", Utils.hash("test1", "SHA-256"), Sets.newHashSet(applicationUsers));
        User usertwo = new User(new UserId("2222-2222"), "john.snow", Utils.hash("test1", "SHA-256"), Sets.newHashSet(applicationUsers));
        em.persist(userone);
        em.persist(usertwo);
        
        //add some testdata
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(230), LocalDate.parse("2014-01-01"), "did something"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(30), LocalDate.parse("2014-01-01"), "did more"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(120), LocalDate.parse("2014-01-01"), "did really some more"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(60), LocalDate.parse("2014-01-01"), "did nothing"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(20), LocalDate.parse("2014-01-01"), "did something again"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(15), LocalDate.parse("2014-01-02"), "did. did?"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(125), LocalDate.parse("2014-01-02"), "yes, I did again something"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), usertwo.getUserId(), Duration.standardMinutes(15), LocalDate.parse("2014-01-02"), "did. did?"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), usertwo.getUserId(), Duration.standardMinutes(125), LocalDate.parse("2014-01-02"), "yes, I did again something"));
    }
}
