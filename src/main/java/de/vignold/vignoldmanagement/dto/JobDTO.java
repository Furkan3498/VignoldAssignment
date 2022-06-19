package de.vignold.vignoldmanagement.dto;


import de.vignold.vignoldmanagement.entity.state.State;

import java.time.ZonedDateTime;

public class JobDTO {
    private String description;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
