package com.vk.api.sdk.queries.ads;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.ads.responses.GetMusiciansResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Query for Ads.getMusiciansByIds method
 */
public class AdsGetMusiciansByIdsQuery extends AbstractQueryBuilder<AdsGetMusiciansByIdsQuery, GetMusiciansResponse> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     * @param ids value of "ids" parameter.
     */
    public AdsGetMusiciansByIdsQuery(VkApiClient client, UserActor actor, Integer... ids) {
        super(client, "ads.getMusiciansByIds", GetMusiciansResponse.class);
        accessToken(actor.getAccessToken());
        ids(ids);
    }

    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     * @param ids value of "ids" parameter.
     */
    public AdsGetMusiciansByIdsQuery(VkApiClient client, UserActor actor, List<Integer> ids) {
        super(client, "ads.getMusiciansByIds", GetMusiciansResponse.class);
        accessToken(actor.getAccessToken());
        ids(ids);
    }

    /**
     * ids
     * Set ids
     *
     * @param value value of "ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected AdsGetMusiciansByIdsQuery ids(Integer... value) {
        return unsafeParam("ids", value);
    }

    /**
     * Set ids
     *
     * @param value value of "ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected AdsGetMusiciansByIdsQuery ids(List<Integer> value) {
        return unsafeParam("ids", value);
    }

    @Override
    protected AdsGetMusiciansByIdsQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("ids", "access_token");
    }
}
