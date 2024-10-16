
package com.acme.catchup.platform.news.domain.model.commands;

/**
 * CreateFavoriteSourceCommand
 * @summary
 * CreateFavoriteSourceCommand is a record class that represents the command to create a favorite news source.
 * @param newsApiKey - the News API Key of the favorite source.
 *                   It cannot be null or empty.
 *                   It must be a valid News API Key.
 * @param sourceId - the source ID of the favorite source.
 *                 It cannot be null or empty.
 *                 It must be a valid source ID.
 */
public record CreateFavoriteSourceCommand(String newsApiKey, String sourceId) {
    /**
     * Validates the command.
     * @throws IllegalArgumentException if the newsApiKey or sourceId is null or empty
     */
    public CreateFavoriteSourceCommand {
        if (newsApiKey == null || newsApiKey.isBlank())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
        if (sourceId == null || sourceId.isBlank())
            throw new IllegalArgumentException("sourceId cannot be null or empty");
    }

}
