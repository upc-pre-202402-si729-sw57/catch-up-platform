package com.acme.catchup.platform.news.interfaces.rest.resources;

/**
 * FavoriteSourceResource
 * @summary
 * FavoriteSourceResource is a record class that represents the resource to get a favorite news source.
 * @param id - the id of the favorite source.
 * @param newsApiKey - the News API Key of the favorite source.
 * @param sourceId - the source ID of the favorite source.
 * @since 1.0.0
 */
public record FavoriteSourceResource(Long id, String newsApiKey, String sourceId) {
}
