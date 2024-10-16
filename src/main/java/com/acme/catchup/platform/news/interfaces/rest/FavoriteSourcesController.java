package com.acme.catchup.platform.news.interfaces.rest;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.interfaces.rest.resources.CreateFavoriteSourceResource;
import com.acme.catchup.platform.news.interfaces.rest.resources.FavoriteSourceResource;
import com.acme.catchup.platform.news.interfaces.rest.transform.CreateFavoriteSourceCommandFromResourceAssembler;
import com.acme.catchup.platform.news.interfaces.rest.transform.FavoriteSourceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * FavoriteSourcesController
 *
 * @summary FavoriteSourcesController is a class that represents the REST controller for the favorite sources. It depends on the FavoriteSourceCommandService and FavoriteSourceQueryService.
 * This class contains the REST endpoints for the favorite sources.
 * The endpoints allow to:
 * <ul>
 * <li> Create a favorite source</li>
 * <li> Get a favorite source by ID</li>
 * <li> Get favorite sources with parameters (News API Key and optionally Source ID)</li>
 * </ul>
 * @see FavoriteSourceCommandService
 * @see FavoriteSourceQueryService
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/api/v1/favorite-sources", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Favorite Sources", description = "Operations related to favorite sources")
public class FavoriteSourcesController {
    private final FavoriteSourceCommandService favoriteSourceCommandService;
    private final FavoriteSourceQueryService favoriteSourceQueryService;

    public FavoriteSourcesController(FavoriteSourceCommandService favoriteSourceCommandService, FavoriteSourceQueryService favoriteSourceQueryService) {
        this.favoriteSourceCommandService = favoriteSourceCommandService;
        this.favoriteSourceQueryService = favoriteSourceQueryService;
    }

    /**
     * Creates a favorite source.
     *
     * @param resource the resource containing the data to create the favorite source.
     * @return an instance of ResponseEntity with the favorite source resource.
     */
    @Operation(
            summary = "Create a favorite source",
            description = "Creates a favorite source with the data provided in the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorite source created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<FavoriteSourceResource> createFavoriteSource(@RequestBody CreateFavoriteSourceResource resource) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceCommandService
                .handle(CreateFavoriteSourceCommandFromResourceAssembler.toCommandFromResource(resource));
        return favoriteSource.map(source -> new ResponseEntity<>(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Gets a favorite source by id.
     *
     * @param id the id of the favorite source.
     * @return an instance of ResponseEntity with the favorite source resource.
     */
    @Operation(
            summary = "Get a favorite source by ID",
            description = "Gets a favorite source by the ID provided in the request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite source found"),
            @ApiResponse(responseCode = "404", description = "Favorite source not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<FavoriteSourceResource> getFavoriteSourceById(@PathVariable Long id) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceQueryService.handle(new GetFavoriteSourceByIdQuery(id));
        return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets all favorite sources by newsApiKey.
     *
     * @param newsApiKey the News API Key generated by the news provider.
     * @return an instance of ResponseEntity with the list of favorite source resources.
     */
    private ResponseEntity<List<FavoriteSourceResource>> getAllFavoriteSourcesByNewsApiKey(String newsApiKey) {
        var getAllFavoriteSourcesByNewsApiKeyQuery = new GetAllFavoriteSourcesByNewsApiKeyQuery(newsApiKey);
        var favoriteSources = favoriteSourceQueryService.handle(getAllFavoriteSourcesByNewsApiKeyQuery);
        if (favoriteSources.isEmpty()) return ResponseEntity.notFound().build();
        var favoriteSourceResources = favoriteSources.stream().map(FavoriteSourceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(favoriteSourceResources);
    }

    /**
     * Gets a favorite source by newsApiKey and sourceId.
     *
     * @param newsApiKey the News API Key generated by the news provider.
     * @param sourceId   the source ID of the favorite source.
     * @return an instance of ResponseEntity with the favorite source resource.
     */
    private ResponseEntity<FavoriteSourceResource> getFavoriteSourceByNewsApiKeyAndSourceId(String newsApiKey, String sourceId) {
        var getFavoriteSourceByNewsApiKeyAndSourceIdQuery = new GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(newsApiKey, sourceId);
        var favoriteSource = favoriteSourceQueryService.handle(getFavoriteSourceByNewsApiKeyAndSourceIdQuery);
        if (favoriteSource.isEmpty()) return ResponseEntity.notFound().build();
        return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets favorite sources with parameters.
     *
     * @param params the parameters to get the favorite sources.
     * @return an instance of ResponseEntity with the favorite source resources.
     */
    @Operation(
            summary = "Get favorite sources with parameter (News API Key and optionally Source ID)",
            description = "Gets favorite sources with the parameters provided in the request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite source(s) found"),
            @ApiResponse(responseCode = "404", description = "Favorite source(s) not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @Parameters({
            @Parameter(name = "newsApiKey", description = "The News API Key generated by the news provider.", required = true),
            @Parameter(name = "sourceId", description = "The source ID of the favorite source.")
    })
    @GetMapping
    public ResponseEntity<?> getFavoriteSourcesWithParameters(
            @Parameter(name = "params", hidden = true)
            @RequestParam Map<String, String> params) {
        if (params.containsKey("newsApiKey") && params.containsKey("sourceId")) {
            return getFavoriteSourceByNewsApiKeyAndSourceId(params.get("newsApiKey"), params.get("sourceId"));
        } else if (params.containsKey("newsApiKey")) {
            return getAllFavoriteSourcesByNewsApiKey(params.get("newsApiKey"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
