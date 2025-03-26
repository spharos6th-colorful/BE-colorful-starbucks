package colorful.starbucks.auth.domain;

import lombok.Getter;


public enum Gender {
    M("남성"),
    W("여성");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}
