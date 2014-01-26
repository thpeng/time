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
package ch.thp.proto.time.user.resource;

import ch.thp.proto.time.user.UserProducer;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Thierry
 */
@Provider
@Slf4j
public class UserFilter implements ContainerRequestFilter  {
    
    public UserFilter()
    {
        log.debug("initiating filter");
    }

    @Inject
    private UserProducer producer;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        log.debug("filtering, setting user: "+requestContext.getSecurityContext().getUserPrincipal().getName());
        
        Preconditions.checkNotNull(producer);
        producer.setCurrentPrincipal(requestContext.getSecurityContext().getUserPrincipal().getName());}

}
