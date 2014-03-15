/*
 * Copyright 2014 Thierry.
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
package ch.thp.proto.time.user.domain;

import java.io.Serializable;
import java.util.Set;
import javax.enterprise.inject.Vetoed;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Thierry
 */
@Data
@NoArgsConstructor
@Vetoed
@Entity
@Table(name = "TIME_USER")
@NamedQuery(name = "user.getByUsername", query = "SELECT u FROM BallbotscheUser u WHERE u.username = :uname")
public class User implements Serializable {

    @EmbeddedId
    private UserId userId;
    private String username;
    private String password;
    //todo salt it. 
//    private String salt; 
    @ManyToMany
    @JoinTable(name = "USER_GROUPS")
    private Set<Group> groups;
    

}
