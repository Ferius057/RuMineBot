package com.vk.api.sdk.actions;

import com.vk.api.sdk.client.AbstractAction;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.queries.podcasts.PodcastsSearchPodcastQuery;

/**
 * List of Podcasts methods
 */
public class Podcasts extends AbstractAction {
    /**
     * Constructor
     *
     * @param client vk api client
     */
    public Podcasts(VkApiClient client) {
        super(client);
    }

    /**
     * @param actor vk actor
     * @param searchString
     * @return query
     */
    public PodcastsSearchPodcastQuery searchPodcast(UserActor actor, String searchString) {
        return new PodcastsSearchPodcastQuery(getClient(), actor, searchString);
    }

    /**
     * @param actor vk actor
     * @param searchString
     * @return query
     */
    public PodcastsSearchPodcastQuery searchPodcast(GroupActor actor, String searchString) {
        return new PodcastsSearchPodcastQuery(getClient(), actor, searchString);
    }
}
