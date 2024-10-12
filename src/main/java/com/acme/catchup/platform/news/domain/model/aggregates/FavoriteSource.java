/**
 * FavoriteSource Aggregate Root
 *
 * @summary
 * The FavoriteSource class is an aggregate root that represents a favorite news source.
 * It is responsible for handling the CreateFavoriteSourceCommand command.
 */
package com.acme.catchup.platform.news.domain.model.aggregates;

import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

public class FavoriteSource extends AbstractAggregateRoot<FavoriteSource> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String newsApiKey;

    @Column(nullable = false)
    @Getter
    private String sourceId;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    protected FavoriteSource() {}

    /**
     * @summary Constructor.
     * It creates a new FavoriteSource instance based on the CreateFavoriteSourceCommand command.
     * @param command - the CreateFavoriteSourceCommand command
     */
    public FavoriteSource(CreateFavoriteSourceCommand command) {
        this.newsApiKey = command.newsApiKey();
        this.sourceId = command.sourceId();
    }


}
