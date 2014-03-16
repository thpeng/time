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
package ch.thp.proto.time.user;

import ch.thp.proto.time.hash.Utils;
import ch.thp.proto.time.user.domain.Group;
import ch.thp.proto.time.user.domain.User;
import ch.thp.proto.time.user.domain.UserId;
import com.google.common.collect.Sets;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thierry
 */
@Singleton
@Startup
@LocalBean
public class UserProducerTestDataLoader {
    
    @PersistenceContext
    private EntityManager em; 

    @PostConstruct
    public void initTest() {
                Group applicationUsers = new Group("1111-1111", "standard", "can use the application");
        em.persist(applicationUsers);
        User userone = new User(new UserId("1111-2222"), "ANONYMOUS", Utils.hash("test1", "SHA-256"), Sets.newHashSet(applicationUsers));
        em.persist(userone);
    }

}
