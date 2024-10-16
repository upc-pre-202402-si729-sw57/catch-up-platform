package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;

import java.util.Optional;

/**
 * @summary
 * This interface represents the service to handle the favorite source commands.
 * @since 1.0.0
 */
public interface FavoriteSourceCommandService {
    /**
     * Handles the create favorite source command.
     * @param command the create favorite source command.
     * @return the created favorite source.
     * @throws IllegalArgumentException if the newsApiKey or sourceId is null or empty
     * @see CreateFavoriteSourceCommand
     */
    Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command);
}
