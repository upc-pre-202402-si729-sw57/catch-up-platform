package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * @summary
 * This interface represents the service to handle the favorite source queries.
 *
 */
public interface FavoriteSourceQueryService {
    /**
     * Handles the get all favorite sources by newsApiKey query.
     * @param query the get all favorite sources by newsApiKey query.
     * @return the list of favorite sources.
     * @throws IllegalArgumentException if the newsApiKey is null or empty
     * @see GetAllFavoriteSourcesByNewsApiKeyQuery
     */
    List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query);
    /**
     * Handles the get favorite source by id query.
     * @param query the get favorite source by id query.
     * @return the favorite source.
     * @throws IllegalArgumentException if the id is null
     * @see GetFavoriteSourceByIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query);
    /**
     * Handles the get favorite source by newsApiKey and sourceId query.
     * @param query the get favorite source by newsApiKey and sourceId query.
     * @return the favorite source.
     * @throws IllegalArgumentException if the newsApiKey or sourceId is null or empty
     * @see GetFavoriteSourceByNewsApiKeyAndSourceIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query);
}
