package com.vk.api.sdk.queries.store;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.Utils;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.store.responses.GetFavoriteStickersResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Query for Store.getFavoriteStickers method
 */
public class StoreGetFavoriteStickersQuery extends AbstractQueryBuilder<StoreGetFavoriteStickersQuery, List<GetFavoriteStickersResponse>> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     */
    public StoreGetFavoriteStickersQuery(VkApiClient client, UserActor actor) {
        super(client, "store.getFavoriteStickers", Utils.buildParametrizedType(List.class, GetFavoriteStickersResponse.class));
        accessToken(actor.getAccessToken());
    }

    @Override
    protected StoreGetFavoriteStickersQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("access_token");
    }
}
