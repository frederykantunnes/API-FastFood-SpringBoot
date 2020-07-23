package br.com.atdsistemas.fastfood.model.dto.additional;

import br.com.atdsistemas.fastfood.model.Additional;

public class AdditionalDTO {
    private long id;
    private String name;
    private Double price;

    public AdditionalDTO(Additional additional) {
        this.id = additional.getId();
        this.name = additional.getName();
        this.price = additional.getPrice();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
