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
package ch.thp.proto.time.hash;

import com.google.common.io.BaseEncoding;
import java.security.MessageDigest;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Thierry
 */
@Slf4j
public class Utils {

    public static String hash(String target, String algorithm) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
           log.error("could not hash with algorithm "+algorithm, e);
        }
        byte[] bytes = target.getBytes();
        byte[] byteHash = md.digest(bytes);

        String encodedHash = null;

        //use guava instead of jboss utils class
        encodedHash = BaseEncoding.base64().encode(byteHash);

        return encodedHash;
    }

    public static void main(String... args) {
        System.out.println(hash("test1", "SHA-256"));
    }
}
