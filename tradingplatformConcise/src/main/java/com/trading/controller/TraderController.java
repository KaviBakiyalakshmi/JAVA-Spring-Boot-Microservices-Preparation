package com.trading.controller;

import com.trading.dto.AddMoneyTraderDTO;
import com.trading.dto.TraderDTO;
import com.trading.dto.UpdateTraderDTO;
import com.trading.model.Trader;
import com.trading.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
@Validated
@RestController
@RequestMapping(value = "/trading/traders")
public class TraderController {

    @Autowired
    private TraderService traderService;

    // Register Trader
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Void> registerTrader(@RequestBody @Valid Trader newTrader) {
        Trader existingTrader = traderService.getTraderByEmail(newTrader.getEmail());
        if (existingTrader != null && existingTrader.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        traderService.registerTrader(newTrader);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    // Get Trader by ID
    @GetMapping("/{id}")
    public ResponseEntity<TraderDTO> getTraderById(@PathVariable Long id) {
        Trader trader = traderService.getTraderById(id);
        if (trader == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new TraderDTO(trader));
    }

    // Get Trader by Email
    @GetMapping
    public ResponseEntity<TraderDTO> getTraderByEmail(@RequestParam("email") String email) {
        Trader trader = traderService.getTraderByEmail(email);
        if (trader == null || trader.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new TraderDTO(trader));
    }

    // Get All Traders
    @GetMapping("/all")
    public ResponseEntity<List<TraderDTO>> getAllTraders() {
        List<TraderDTO> traders = traderService.getAllTraders()
                .stream()
                .map(TraderDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(traders);
    }

    // put update traders name by email

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateTrader(@RequestBody @Valid UpdateTraderDTO trader) {
        traderService.updateTrader(trader);
    }

    //add money to the traders

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void addMoney(@RequestBody @Valid AddMoneyTraderDTO trader) {
        traderService.addMoney(trader);
    }


}

