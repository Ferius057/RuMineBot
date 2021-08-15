package com.vk.api.sdk.objects.ads.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.ads.CampaignStatus;
import com.vk.api.sdk.objects.ads.CampaignType;
import com.vk.api.sdk.objects.annotations.Required;
import java.util.Objects;

/**
 * GetCampaignsResponse object
 */
public class GetCampaignsResponse implements Validable {
    /**
     * Amount of active ads in campaign
     */
    @SerializedName("ads_count")
    private Integer adsCount;

    /**
     * Campaign's total limit, rubles
     */
    @SerializedName("all_limit")
    private String allLimit;

    /**
     * Campaign create time, as Unixtime
     */
    @SerializedName("create_time")
    private Integer createTime;

    /**
     * Campaign's day limit, rubles
     */
    @SerializedName("day_limit")
    private String dayLimit;

    /**
     * Campaign ID
     */
    @SerializedName("id")
    @Required
    private Integer id;

    /**
     * Campaign title
     */
    @SerializedName("name")
    @Required
    private String name;

    /**
     * Campaign start time, as Unixtime
     */
    @SerializedName("start_time")
    private Integer startTime;

    @SerializedName("status")
    @Required
    private CampaignStatus status;

    /**
     * Campaign stop time, as Unixtime
     */
    @SerializedName("stop_time")
    private Integer stopTime;

    @SerializedName("type")
    @Required
    private CampaignType type;

    /**
     * Campaign update time, as Unixtime
     */
    @SerializedName("update_time")
    private Integer updateTime;

    /**
     * Limit of views per user per campaign
     */
    @SerializedName("views_limit")
    private Integer viewsLimit;

    public Integer getAdsCount() {
        return adsCount;
    }

    public GetCampaignsResponse setAdsCount(Integer adsCount) {
        this.adsCount = adsCount;
        return this;
    }

    public String getAllLimit() {
        return allLimit;
    }

    public GetCampaignsResponse setAllLimit(String allLimit) {
        this.allLimit = allLimit;
        return this;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public GetCampaignsResponse setCreateTime(Integer createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getDayLimit() {
        return dayLimit;
    }

    public GetCampaignsResponse setDayLimit(String dayLimit) {
        this.dayLimit = dayLimit;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public GetCampaignsResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GetCampaignsResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public GetCampaignsResponse setStartTime(Integer startTime) {
        this.startTime = startTime;
        return this;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public GetCampaignsResponse setStatus(CampaignStatus status) {
        this.status = status;
        return this;
    }

    public Integer getStopTime() {
        return stopTime;
    }

    public GetCampaignsResponse setStopTime(Integer stopTime) {
        this.stopTime = stopTime;
        return this;
    }

    public CampaignType getType() {
        return type;
    }

    public GetCampaignsResponse setType(CampaignType type) {
        this.type = type;
        return this;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public GetCampaignsResponse setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getViewsLimit() {
        return viewsLimit;
    }

    public GetCampaignsResponse setViewsLimit(Integer viewsLimit) {
        this.viewsLimit = viewsLimit;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayLimit, allLimit, createTime, name, viewsLimit, startTime, updateTime, stopTime, id, type, adsCount, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCampaignsResponse getCampaignsResponse = (GetCampaignsResponse) o;
        return Objects.equals(allLimit, getCampaignsResponse.allLimit) &&
                Objects.equals(startTime, getCampaignsResponse.startTime) &&
                Objects.equals(updateTime, getCampaignsResponse.updateTime) &&
                Objects.equals(adsCount, getCampaignsResponse.adsCount) &&
                Objects.equals(stopTime, getCampaignsResponse.stopTime) &&
                Objects.equals(createTime, getCampaignsResponse.createTime) &&
                Objects.equals(dayLimit, getCampaignsResponse.dayLimit) &&
                Objects.equals(name, getCampaignsResponse.name) &&
                Objects.equals(id, getCampaignsResponse.id) &&
                Objects.equals(viewsLimit, getCampaignsResponse.viewsLimit) &&
                Objects.equals(type, getCampaignsResponse.type) &&
                Objects.equals(status, getCampaignsResponse.status);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("GetCampaignsResponse{");
        sb.append("allLimit='").append(allLimit).append("'");
        sb.append(", startTime=").append(startTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", adsCount=").append(adsCount);
        sb.append(", stopTime=").append(stopTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", dayLimit='").append(dayLimit).append("'");
        sb.append(", name='").append(name).append("'");
        sb.append(", id=").append(id);
        sb.append(", viewsLimit=").append(viewsLimit);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
