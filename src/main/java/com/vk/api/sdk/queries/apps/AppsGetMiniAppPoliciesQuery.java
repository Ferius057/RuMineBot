package com.vk.api.sdk.queries.apps;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.apps.responses.GetMiniAppPoliciesResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Query for Apps.getMiniAppPolicies method
 */
public class AppsGetMiniAppPoliciesQuery extends AbstractQueryBuilder<AppsGetMiniAppPoliciesQuery, GetMiniAppPoliciesResponse> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor actor with access token
     * @param appId value of "app id" parameter. Minimum is 0.
     */
    public AppsGetMiniAppPoliciesQuery(VkApiClient client, UserActor actor, int appId) {
        super(client, "apps.getMiniAppPolicies", GetMiniAppPoliciesResponse.class);
        accessToken(actor.getAccessToken());
        appId(appId);
    }

    /**
     * Mini App ID
     *
     * @param value value of "app id" parameter. Minimum is 0.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected AppsGetMiniAppPoliciesQuery appId(int value) {
        return unsafeParam("app_id", value);
    }

    @Override
    protected AppsGetMiniAppPoliciesQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("app_id", "access_token");
    }
}
