package com.acme.catchup.platform.news.interfaces.rest.transform;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.interfaces.rest.resources.FavoriteSourceResource;

/**
 * FavoriteSourceResourceFromEntityAssembler
 * @summary
 * FavoriteSourceResourceFromEntityAssembler is a class that represents the assembler to create a favorite source resource from an entity.
 * @see FavoriteSource
 * @see FavoriteSourceResource
 *
 */
public class FavoriteSourceResourceFromEntityAssembler {
    /**
     * Converts a FavoriteSource to a FavoriteSourceResource.
     * @param entity the entity containing the data to create the favorite source resource.
     * @return an instance of FavoriteSourceResource.
     */
    public static FavoriteSourceResource toResourceFromEntity(FavoriteSource entity) {
        return new FavoriteSourceResource(entity.getId(), entity.getNewsApiKey(), entity.getSourceId());
    }
}
