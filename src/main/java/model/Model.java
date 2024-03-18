package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Model(
        @JsonAlias("codigo") Integer code,
        @JsonAlias("nome") String name
) {
}
