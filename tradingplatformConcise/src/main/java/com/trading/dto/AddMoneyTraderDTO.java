package com.trading.dto;

import jakarta.validation.constraints.NotNull;

public class AddMoneyTraderDTO {

    @NotNull
    private Long id;
    @NotNull
    private Double amount;

    public AddMoneyTraderDTO() {}

    public AddMoneyTraderDTO(Long id, Double amount) {
        this.id = id;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
