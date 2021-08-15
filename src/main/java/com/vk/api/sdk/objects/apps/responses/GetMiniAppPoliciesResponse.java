package com.vk.api.sdk.objects.apps.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import java.net.URI;
import java.util.Objects;

/**
 * GetMiniAppPoliciesResponse object
 */
public class GetMiniAppPoliciesResponse implements Validable {
    /**
     * URL of the app's privacy policy
     */
    @SerializedName("privacy_policy")
    private URI privacyPolicy;

    /**
     * URL of the app's terms
     */
    @SerializedName("terms")
    private URI terms;

    public URI getPrivacyPolicy() {
        return privacyPolicy;
    }

    public GetMiniAppPoliciesResponse setPrivacyPolicy(URI privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
        return this;
    }

    public URI getTerms() {
        return terms;
    }

    public GetMiniAppPoliciesResponse setTerms(URI terms) {
        this.terms = terms;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(terms, privacyPolicy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetMiniAppPoliciesResponse getMiniAppPoliciesResponse = (GetMiniAppPoliciesResponse) o;
        return Objects.equals(terms, getMiniAppPoliciesResponse.terms) &&
                Objects.equals(privacyPolicy, getMiniAppPoliciesResponse.privacyPolicy);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("GetMiniAppPoliciesResponse{");
        sb.append("terms=").append(terms);
        sb.append(", privacyPolicy=").append(privacyPolicy);
        sb.append('}');
        return sb.toString();
    }
}
