package com.vk.api.sdk.actions;

import com.vk.api.sdk.client.AbstractAction;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.queries.store.StoreAddStickersToFavoriteQuery;
import com.vk.api.sdk.queries.store.StoreGetFavoriteStickersQuery;
import com.vk.api.sdk.queries.store.StoreGetProductsQuery;
import com.vk.api.sdk.queries.store.StoreGetStickersKeywordsQuery;
import com.vk.api.sdk.queries.store.StoreRemoveStickersFromFavoriteQuery;
import java.util.List;

/**
 * List of Store methods
 */
public class Store extends AbstractAction {
    /**
     * Constructor
     *
     * @param client vk api client
     */
    public Store(VkApiClient client) {
        super(client);
    }

    /**
     * Adds given sticker IDs to the list of user's favorite stickers
     *
     * @param actor vk actor
     * @param stickerIds Sticker IDs to be added
     * @return query
     */
    public StoreAddStickersToFavoriteQuery addStickersToFavorite(UserActor actor,
            Integer... stickerIds) {
        return new StoreAddStickersToFavoriteQuery(getClient(), actor, stickerIds);
    }

    /**
     * Adds given sticker IDs to the list of user's favorite stickers
     *
     * @param actor vk actor
     * @param stickerIds Sticker IDs to be added
     * @return query
     */
    public StoreAddStickersToFavoriteQuery addStickersToFavorite(UserActor actor,
            List<Integer> stickerIds) {
        return new StoreAddStickersToFavoriteQuery(getClient(), actor, stickerIds);
    }

    /**
     * @param actor vk actor
     * @return query
     */
    public StoreGetFavoriteStickersQuery getFavoriteStickers(UserActor actor) {
        return new StoreGetFavoriteStickersQuery(getClient(), actor);
    }

    /**
     * @param actor vk actor
     * @return query
     */
    public StoreGetProductsQuery getProducts(UserActor actor) {
        return new StoreGetProductsQuery(getClient(), actor);
    }

    /**
     * @param actor vk actor
     * @return query
     */
    public StoreGetStickersKeywordsQuery getStickersKeywords(UserActor actor) {
        return new StoreGetStickersKeywordsQuery(getClient(), actor);
    }

    /**
     * Removes given sticker IDs from the list of user's favorite stickers
     *
     * @param actor vk actor
     * @param stickerIds Sticker IDs to be removed
     * @return query
     */
    public StoreRemoveStickersFromFavoriteQuery removeStickersFromFavorite(UserActor actor,
            Integer... stickerIds) {
        return new StoreRemoveStickersFromFavoriteQuery(getClient(), actor, stickerIds);
    }

    /**
     * Removes given sticker IDs from the list of user's favorite stickers
     *
     * @param actor vk actor
     * @param stickerIds Sticker IDs to be removed
     * @return query
     */
    public StoreRemoveStickersFromFavoriteQuery removeStickersFromFavorite(UserActor actor,
            List<Integer> stickerIds) {
        return new StoreRemoveStickersFromFavoriteQuery(getClient(), actor, stickerIds);
    }
}
