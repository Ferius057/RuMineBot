package com.vk.api.sdk.objects.podcasts.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import com.vk.api.sdk.objects.podcast.ExternalData;
import java.util.List;
import java.util.Objects;

/**
 * SearchPodcastResponse object
 */
public class SearchPodcastResponse implements Validable {
    @SerializedName("podcasts")
    @Required
    private List<ExternalData> podcasts;

    /**
     * Total amount of found results
     */
    @SerializedName("results_total")
    private Integer resultsTotal;

    public List<ExternalData> getPodcasts() {
        return podcasts;
    }

    public SearchPodcastResponse setPodcasts(List<ExternalData> podcasts) {
        this.podcasts = podcasts;
        return this;
    }

    public Integer getResultsTotal() {
        return resultsTotal;
    }

    public SearchPodcastResponse setResultsTotal(Integer resultsTotal) {
        this.resultsTotal = resultsTotal;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(podcasts, resultsTotal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchPodcastResponse searchPodcastResponse = (SearchPodcastResponse) o;
        return Objects.equals(podcasts, searchPodcastResponse.podcasts) &&
                Objects.equals(resultsTotal, searchPodcastResponse.resultsTotal);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("SearchPodcastResponse{");
        sb.append("podcasts=").append(podcasts);
        sb.append(", resultsTotal=").append(resultsTotal);
        sb.append('}');
        return sb.toString();
    }
}
