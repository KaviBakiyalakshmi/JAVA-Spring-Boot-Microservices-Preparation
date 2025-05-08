package com.trading.service;

import com.trading.dto.AddMoneyTraderDTO;
import com.trading.dto.UpdateTraderDTO;
import com.trading.model.Trader;
import com.trading.repository.TraderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderServiceImpl implements TraderService {

    @Autowired
    private TraderRepository traderRepository;

    @Override
    public Trader getTraderById(Long id) {
        return traderRepository.findById(id).orElse(new Trader());
    }

    @Override
    public Trader getTraderByEmail(String email) {
        return traderRepository.findByEmail(email).orElse(new Trader());
    }

    @Override
    public void registerTrader(Trader trader) {
        traderRepository.save(trader);
    }


    @Override
    public List<Trader> getAllTraders() {
        return traderRepository.findAll();
    }

    @Override
    public void updateTrader(UpdateTraderDTO dto) {
        Trader trader = traderRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Trader not found"));

        trader.setName(dto.getName());
        trader.setEmail(dto.getEmail());
        // Update other fields as needed

        traderRepository.save(trader);
    }

    @Override
    public void addMoney(AddMoneyTraderDTO dto) {
        Trader trader = traderRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Trader not found"));

        trader.setBalance(trader.getBalance() + dto.getAmount());
        traderRepository.save(trader);
    }
}
