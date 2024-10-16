package com.acme.catchup.platform.news.infrastructure.persistence.jpa;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * FavoriteSourceRepository
 * @summary
 * The FavoriteSourceRepository interface is responsible for managing the favorite news sources in the database.
 * @since 1.0.0
 */
@Repository
public interface FavoriteSourceRepository extends JpaRepository<FavoriteSource, Long> {
    /**
     * Finds all favorite sources by newsApiKey.
     * @param newsApiKey the News API Key.
     * @return the list of favorite sources.
     */
    List<FavoriteSource> findAllByNewsApiKey(String newsApiKey);
    /**
     * Checks if a favorite source exists by newsApiKey and sourceId.
     * @param newApiKey the News API Key.
     * @param sourceId the source ID.
     * @return true if the favorite source exists, false otherwise.
     */
    boolean existsByNewsApiKeyAndSourceId(String newApiKey, String sourceId);
    /**
     * Finds a favorite source by newsApiKey and sourceId.
     * @param newsApiKey the News API Key.
     * @param sourceId the source ID.
     * @return the favorite source.
     */
    Optional<FavoriteSource> findByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);
}
