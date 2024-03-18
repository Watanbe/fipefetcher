package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Year(
        @JsonAlias("codigo") String code,
        @JsonAlias("nome") String name
) {
}
