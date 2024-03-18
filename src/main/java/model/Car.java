package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Car(
        @JsonAlias("Valor") String value,
        @JsonAlias("Marca") String brand,
        @JsonAlias("Modelo") String model,
        @JsonAlias("AnoModelo") Integer year,
        @JsonAlias("Combustivel") String gasType,
        @JsonAlias("CodigoFipe") String fipeCode,
        @JsonAlias("MesReferencia") String monthOfReference
) {
}
