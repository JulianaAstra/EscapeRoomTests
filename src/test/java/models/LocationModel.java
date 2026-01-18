package models;

import java.math.BigDecimal;
import java.util.List;

public record LocationModel(
        String address,
        List<BigDecimal> coords ) {}
