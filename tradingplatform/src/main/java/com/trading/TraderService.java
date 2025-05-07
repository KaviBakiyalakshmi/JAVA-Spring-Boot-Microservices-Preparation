package com.trading;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;

    //register
    public boolean existsByEmail(String email)
    {
        return traderRepository.findByEmail(email).isPresent();

    }

    public void registerTrader(@Valid Trader trader) {

        traderRepository.save(trader);
    }


    // Get all details
    public List<Trader> getAllTradersSortedById()
    {
        return traderRepository.findAllByOrderByIdAsc();
    }


    //3.Get By email
    public Optional<Trader> getTraderByEmail(String email)
    {
        return traderRepository.findByEmail(email);
    }


    //4.update the name by email
    public void updateTrader(UpdateTraderDTO dto) {
        Trader trader=traderRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Trader not found"));

        trader.setName(dto.getName());
        trader.setUpdatedAt(LocalDateTime.now());
        traderRepository.save(trader);

    }

    //update or add money by email
    public void addMoneyToTrader(AddMoneyDTO dto)
    {
        Trader trader=traderRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Trader not found"));
        trader.setBalance(trader.getBalance()+dto.getAmount());
        trader.setUpdatedAt(LocalDateTime.now());
        traderRepository.save(trader);
    }
}
