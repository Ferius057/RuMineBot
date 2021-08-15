package com.vk.api.sdk.queries.store;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.base.responses.OkResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Query for Store.removeStickersFromFavorite method
 */
public class StoreRemoveStickersFromFavoriteQuery extends AbstractQueryBuilder<StoreRemoveStickersFromFavoriteQuery, OkResponse> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     * @param stickerIds value of "sticker ids" parameter.
     */
    public StoreRemoveStickersFromFavoriteQuery(VkApiClient client, UserActor actor,
            Integer... stickerIds) {
        super(client, "store.removeStickersFromFavorite", OkResponse.class);
        accessToken(actor.getAccessToken());
        stickerIds(stickerIds);
    }

    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     * @param stickerIds value of "sticker ids" parameter.
     */
    public StoreRemoveStickersFromFavoriteQuery(VkApiClient client, UserActor actor,
            List<Integer> stickerIds) {
        super(client, "store.removeStickersFromFavorite", OkResponse.class);
        accessToken(actor.getAccessToken());
        stickerIds(stickerIds);
    }

    /**
     * sticker_ids
     * Sticker IDs to be removed
     *
     * @param value value of "sticker ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected StoreRemoveStickersFromFavoriteQuery stickerIds(Integer... value) {
        return unsafeParam("sticker_ids", value);
    }

    /**
     * Sticker IDs to be removed
     *
     * @param value value of "sticker ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected StoreRemoveStickersFromFavoriteQuery stickerIds(List<Integer> value) {
        return unsafeParam("sticker_ids", value);
    }

    @Override
    protected StoreRemoveStickersFromFavoriteQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("sticker_ids", "access_token");
    }
}
