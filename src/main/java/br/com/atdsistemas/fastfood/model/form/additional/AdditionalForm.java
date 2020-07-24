package br.com.atdsistemas.fastfood.model.form.additional;

import br.com.atdsistemas.fastfood.model.Additional;

public class AdditionalForm {
    private String name;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Additional getAdditional() {
        Additional additional = new Additional();
        additional.setName(this.getName());
        additional.setPrice(this.getPrice());
        additional.setAvailable(true);
        return additional;
    }
}
