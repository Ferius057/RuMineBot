package com.vk.api.sdk.objects.store.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import com.vk.api.sdk.objects.store.StickersKeyword;
import java.util.List;
import java.util.Objects;

/**
 * GetStickersKeywordsResponse object
 */
public class GetStickersKeywordsResponse implements Validable {
    @SerializedName("count")
    @Required
    private Integer count;

    @SerializedName("dictionary")
    @Required
    private List<StickersKeyword> dictionary;

    /**
     * Total count of chunks to load
     */
    @SerializedName("chunks_count")
    private Integer chunksCount;

    /**
     * Chunks version hash
     */
    @SerializedName("chunks_hash")
    private String chunksHash;

    public Integer getCount() {
        return count;
    }

    public GetStickersKeywordsResponse setCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<StickersKeyword> getDictionary() {
        return dictionary;
    }

    public GetStickersKeywordsResponse setDictionary(List<StickersKeyword> dictionary) {
        this.dictionary = dictionary;
        return this;
    }

    public Integer getChunksCount() {
        return chunksCount;
    }

    public GetStickersKeywordsResponse setChunksCount(Integer chunksCount) {
        this.chunksCount = chunksCount;
        return this;
    }

    public String getChunksHash() {
        return chunksHash;
    }

    public GetStickersKeywordsResponse setChunksHash(String chunksHash) {
        this.chunksHash = chunksHash;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chunksCount, dictionary, count, chunksHash);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetStickersKeywordsResponse getStickersKeywordsResponse = (GetStickersKeywordsResponse) o;
        return Objects.equals(dictionary, getStickersKeywordsResponse.dictionary) &&
                Objects.equals(chunksCount, getStickersKeywordsResponse.chunksCount) &&
                Objects.equals(chunksHash, getStickersKeywordsResponse.chunksHash) &&
                Objects.equals(count, getStickersKeywordsResponse.count);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("GetStickersKeywordsResponse{");
        sb.append("dictionary=").append(dictionary);
        sb.append(", chunksCount=").append(chunksCount);
        sb.append(", chunksHash='").append(chunksHash).append("'");
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
