package com.vk.api.sdk.queries.podcasts;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.podcasts.responses.SearchPodcastResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Query for Podcasts.searchPodcast method
 */
public class PodcastsSearchPodcastQuery extends AbstractQueryBuilder<PodcastsSearchPodcastQuery, SearchPodcastResponse> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     * @param searchString value of "search string" parameter.
     */
    public PodcastsSearchPodcastQuery(VkApiClient client, UserActor actor, String searchString) {
        super(client, "podcasts.searchPodcast", SearchPodcastResponse.class);
        accessToken(actor.getAccessToken());
        searchString(searchString);
    }

    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     * @param searchString value of "search string" parameter.
     */
    public PodcastsSearchPodcastQuery(VkApiClient client, GroupActor actor, String searchString) {
        super(client, "podcasts.searchPodcast", SearchPodcastResponse.class);
        accessToken(actor.getAccessToken());
        searchString(searchString);
    }

    /**
     * Set search string
     *
     * @param value value of "search string" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected PodcastsSearchPodcastQuery searchString(String value) {
        return unsafeParam("search_string", value);
    }

    /**
     * Set offset
     *
     * @param value value of "offset" parameter. Minimum is 0. By default 0.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public PodcastsSearchPodcastQuery offset(Integer value) {
        return unsafeParam("offset", value);
    }

    /**
     * Set count
     *
     * @param value value of "count" parameter. Maximum is 1000. Minimum is 1. By default 20.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public PodcastsSearchPodcastQuery count(Integer value) {
        return unsafeParam("count", value);
    }

    @Override
    protected PodcastsSearchPodcastQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("search_string", "access_token");
    }
}
