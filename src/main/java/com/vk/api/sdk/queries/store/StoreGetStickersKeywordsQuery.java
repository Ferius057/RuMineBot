package com.vk.api.sdk.queries.store;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.store.responses.GetStickersKeywordsResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Query for Store.getStickersKeywords method
 */
public class StoreGetStickersKeywordsQuery extends AbstractQueryBuilder<StoreGetStickersKeywordsQuery, GetStickersKeywordsResponse> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     */
    public StoreGetStickersKeywordsQuery(VkApiClient client, UserActor actor) {
        super(client, "store.getStickersKeywords", GetStickersKeywordsResponse.class);
        accessToken(actor.getAccessToken());
    }

    /**
     * Set aliases
     *
     * @param value value of "aliases" parameter. By default true.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetStickersKeywordsQuery aliases(Boolean value) {
        return unsafeParam("aliases", value);
    }

    /**
     * Set all products
     *
     * @param value value of "all products" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetStickersKeywordsQuery allProducts(Boolean value) {
        return unsafeParam("all_products", value);
    }

    /**
     * Set need stickers
     *
     * @param value value of "need stickers" parameter. By default true.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetStickersKeywordsQuery needStickers(Boolean value) {
        return unsafeParam("need_stickers", value);
    }

    /**
     * stickers_ids
     * Set stickers ids
     *
     * @param value value of "stickers ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetStickersKeywordsQuery stickersIds(Integer... value) {
        return unsafeParam("stickers_ids", value);
    }

    /**
     * Set stickers ids
     *
     * @param value value of "stickers ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetStickersKeywordsQuery stickersIds(List<Integer> value) {
        return unsafeParam("stickers_ids", value);
    }

    /**
     * products_ids
     * Set products ids
     *
     * @param value value of "products ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetStickersKeywordsQuery productsIds(Integer... value) {
        return unsafeParam("products_ids", value);
    }

    /**
     * Set products ids
     *
     * @param value value of "products ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetStickersKeywordsQuery productsIds(List<Integer> value) {
        return unsafeParam("products_ids", value);
    }

    @Override
    protected StoreGetStickersKeywordsQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("access_token");
    }
}
