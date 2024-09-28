package com.mypg.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum LivingStatus {
    INTENT_TO_LEAVE("Intent To Leave"),
    LEAVE("Guest Leaved"),
    LIVING("Currently Living");
    private final String description;
    LivingStatus(String description) {
        this.description=description;
    }
}
