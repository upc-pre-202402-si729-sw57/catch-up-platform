package com.acme.catchup.platform.news.interfaces.rest.transform;

import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.interfaces.rest.resources.CreateFavoriteSourceResource;

/**
 * CreateFavoriteSourceCommandFromResourceAssembler
 * @summary
 * CreateFavoriteSourceCommandFromResourceAssembler is a class that represents the assembler to create a favorite source command from a resource.
 * @see CreateFavoriteSourceResource
 * @see CreateFavoriteSourceCommand
 * @since 1.0.0
 */
public class CreateFavoriteSourceCommandFromResourceAssembler {
    /**
     * Converts a CreateFavoriteSourceResource to a CreateFavoriteSourceCommand.
     * @param resource the resource containing the data to create the favorite source.
     * @return an instance of CreateFavoriteSourceCommand.
     */
    public static CreateFavoriteSourceCommand toCommandFromResource(CreateFavoriteSourceResource resource) {
        return new CreateFavoriteSourceCommand(resource.newsApiKey(), resource.sourceId());
    }
}
