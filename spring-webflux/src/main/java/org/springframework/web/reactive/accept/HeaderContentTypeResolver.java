/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.reactive.accept;

import java.util.List;

import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.server.ServerWebExchange;

/**
 * Resolver that looks at the 'Accept' header of the request.
 *
 * @author Rossen Stoyanchev
 * @since 5.0
 */
public class HeaderContentTypeResolver implements RequestedContentTypeResolver {

    @Override
    public List<MediaType> resolveMediaTypes(ServerWebExchange exchange) throws NotAcceptableStatusException {
        try {
            List<MediaType> mediaTypes = exchange.getRequest().getHeaders().getAccept();
            MediaType.sortBySpecificityAndQuality(mediaTypes);
            return (!CollectionUtils.isEmpty(mediaTypes) ? mediaTypes : MEDIA_TYPE_ALL_LIST);
        } catch (InvalidMediaTypeException ex) {
            String value = exchange.getRequest().getHeaders().getFirst("Accept");
            throw new NotAcceptableStatusException(
                    "Could not parse 'Accept' header [" + value + "]: " + ex.getMessage());
        }
    }

}
