package com.xdesign.service.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HillData Model : to store the required Hill Data Attributes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "height", "hill_category", "grid_reference"})
public class HillData {

    private String name;

    private double height;

    @SerializedName("hill_category")
    @JsonProperty("hill_category")
    private String hillCategory;

    @SerializedName("grid_reference")
    @JsonProperty("grid_reference")
    private String gridReference;
}
