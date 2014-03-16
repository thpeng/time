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
package ch.thp.proto.time.user;

import ch.thp.proto.time.hash.Utils;
import ch.thp.proto.time.user.CurrentUser;
import ch.thp.proto.time.user.domain.User;
import javax.inject.Inject;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Thierry
 */
@RunWith(Arquillian.class)
public class UserProducerTest {

    @Inject
    @CurrentUser
    private User user;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(CurrentUser.class.getPackage())
                .addPackage(User.class.getPackage())
                .addClass(Utils.class)
                .addClass(UserProducerTestDataLoader.class)
                .addAsResource("userproducer_test_persistence.xml", "META-INF/persistence.xml")
                .addAsResource("log4j.properties", "log4j.properties")
                .addAsManifestResource("userproducer_test_beans.xml",
                        ArchivePaths.create("beans.xml"));

    }

    @Test
    public void testInjectCurrentUser() {
        //a bit of a hack: we cannot mock the built-in principal with cdi tools for example @alternative or @spezialize
        // we could do a proper jaas login but thats not the point of this test
        // the built-in default principal is ANONYMOUS, so we reuse this fine lad. 
        Assert.assertEquals("ANONYMOUS", user.getUsername());
    }
}
