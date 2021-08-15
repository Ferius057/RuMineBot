package com.vk.api.sdk.objects.market;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import java.util.Objects;

/**
 * Currency object
 */
public class Currency implements Validable {
    /**
     * Currency ID
     */
    @SerializedName("id")
    @Required
    private Integer id;

    /**
     * Currency sign
     */
    @SerializedName("name")
    @Required
    private String name;

    /**
     * Currency title
     */
    @SerializedName("title")
    @Required
    private String title;

    public Integer getId() {
        return id;
    }

    public Currency setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Currency setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Currency setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(name, currency.name) &&
                Objects.equals(id, currency.id) &&
                Objects.equals(title, currency.title);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("Currency{");
        sb.append("name='").append(name).append("'");
        sb.append(", id=").append(id);
        sb.append(", title='").append(title).append("'");
        sb.append('}');
        return sb.toString();
    }
}
