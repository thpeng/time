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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

/**
 *
 * @author Thierry
 */
@Singleton
@LocalBean
public class TestDataLoader {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        Group applicationUsers = new Group("1111-1111", "ballbotscheUser", "can use the application");
        em.persist(applicationUsers);
        //pw: test1
        User userone = new User(new UserId("1111-2222"), "ned.stark", Utils.hash("test1", "SHA-256"), Sets.newHashSet(applicationUsers));
        //pw: test1                                                                                           
        User usertwo = new User(new UserId("2222-2222"), "john.snow", Utils.hash("test1", "SHA-256"), Sets.newHashSet(applicationUsers));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(230), LocalDate.parse("2014-01-01"), "did something"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(30), LocalDate.parse("2014-01-01"), "did more"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(120), LocalDate.parse("2014-01-01"), "did really some more"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(60), LocalDate.parse("2014-01-01"), "did nothing"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(20), LocalDate.parse("2014-01-01"), "did something again"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(15), LocalDate.parse("2014-01-02"), "did. did?"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), userone.getUserId(), Duration.standardMinutes(125), LocalDate.parse("2014-01-02"), "yes, I did again something"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), usertwo.getUserId(), Duration.standardMinutes(15), LocalDate.parse("2014-01-02"), "did. did?"));
        em.persist(new TimesheetEntry(new TimesheetEntryId(UUID.randomUUID().toString()), usertwo.getUserId(), Duration.standardMinutes(125), LocalDate.parse("2014-01-02"), "yes, I did again something"));

        em.persist(userone);
        em.persist(usertwo);
    }

}
