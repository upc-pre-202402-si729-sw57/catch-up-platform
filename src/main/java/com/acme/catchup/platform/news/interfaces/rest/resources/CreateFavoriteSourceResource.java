package com.acme.catchup.platform.news.interfaces.rest.resources;

/**
 * CreateFavoriteSourceResource
 * @summary
 * CreateFavoriteSourceResource is a record class that represents the resource to create a favorite news source.
 * @param newsApiKey - the News API Key of the favorite source.
 *                   It cannot be null or empty.
 *                   It must be a valid News API Key.
 * @param sourceId - the source ID of the favorite source.
 *                 It cannot be null or empty.
 *                 It must be a valid source ID.
 * @since 1.0.0
 */
public record CreateFavoriteSourceResource(String newsApiKey, String sourceId) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException if the newsApiKey or sourceId is null or empty
     */
    public CreateFavoriteSourceResource {
        if (newsApiKey == null || newsApiKey.isBlank())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
        if (sourceId == null || sourceId.isBlank())
            throw new IllegalArgumentException("sourceId cannot be null or empty");
    }
}
