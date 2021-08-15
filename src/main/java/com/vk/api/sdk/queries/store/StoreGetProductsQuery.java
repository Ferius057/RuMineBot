package com.vk.api.sdk.queries.store;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.Utils;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.store.responses.GetProductsResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Query for Store.getProducts method
 */
public class StoreGetProductsQuery extends AbstractQueryBuilder<StoreGetProductsQuery, List<GetProductsResponse>> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     */
    public StoreGetProductsQuery(VkApiClient client, UserActor actor) {
        super(client, "store.getProducts", Utils.buildParametrizedType(List.class, GetProductsResponse.class));
        accessToken(actor.getAccessToken());
    }

    /**
     * Set type
     *
     * @param value value of "type" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetProductsQuery type(String value) {
        return unsafeParam("type", value);
    }

    /**
     * Set merchant
     *
     * @param value value of "merchant" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetProductsQuery merchant(String value) {
        return unsafeParam("merchant", value);
    }

    /**
     * Set section
     *
     * @param value value of "section" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetProductsQuery section(String value) {
        return unsafeParam("section", value);
    }

    /**
     * Set extended
     *
     * @param value value of "extended" parameter. By default 0.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetProductsQuery extended(Boolean value) {
        return unsafeParam("extended", value);
    }

    /**
     * product_ids
     * Set product ids
     *
     * @param value value of "product ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetProductsQuery productIds(Integer... value) {
        return unsafeParam("product_ids", value);
    }

    /**
     * Set product ids
     *
     * @param value value of "product ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetProductsQuery productIds(List<Integer> value) {
        return unsafeParam("product_ids", value);
    }

    /**
     * filters
     * Set filters
     *
     * @param value value of "filters" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetProductsQuery filters(String... value) {
        return unsafeParam("filters", value);
    }

    /**
     * Set filters
     *
     * @param value value of "filters" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public StoreGetProductsQuery filters(List<String> value) {
        return unsafeParam("filters", value);
    }

    @Override
    protected StoreGetProductsQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("access_token");
    }
}
