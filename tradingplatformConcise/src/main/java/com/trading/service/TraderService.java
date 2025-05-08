package com.trading.service;

import com.trading.dto.AddMoneyTraderDTO;
import com.trading.dto.UpdateTraderDTO;
import com.trading.model.Trader;

import java.util.List;

public interface TraderService {
    Trader getTraderById(Long id);
    Trader getTraderByEmail(String email);
    void registerTrader(Trader trader);
    void updateTrader(UpdateTraderDTO trader);
    void addMoney(AddMoneyTraderDTO trader);
    List<Trader> getAllTraders();
}
