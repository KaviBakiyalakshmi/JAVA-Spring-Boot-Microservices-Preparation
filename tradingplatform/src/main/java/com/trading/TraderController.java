package com.trading;

import ch.qos.logback.core.joran.spi.HttpUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/trading/traders")
public class TraderController {

    @Autowired
    private TraderService traderService;

    //1.Register method with email check
    @RequestMapping(value="/register",method= RequestMethod.POST,consumes="application/json")
    public ResponseEntity<?> registerTrader(@RequestBody @Valid Trader trader)
    {
        if(traderService.existsByEmail(trader.getEmail()))
        {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already exists with response code is 400");
        }

        traderService.registerTrader(trader);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    //2.Get all details
    @RequestMapping(value="/all",method= RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<Trader>> getAllTraders()
    {
        List<Trader> trader=traderService.getAllTradersSortedById();
        return ResponseEntity.ok(trader);
    }

    //3.Get By email
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Trader> getTraderByEmail(@RequestParam("email") String email)
    {
        return traderService.getTraderByEmail(email)
                .map(trader -> new ResponseEntity<>(trader,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //4 update name by email
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateTrader(@RequestBody @Valid UpdateTraderDTO trader)
    {
        traderService.updateTrader(trader);
    }

    //5.update or add money by email
    @RequestMapping(value="/add",method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void addMoneyTrader(@RequestBody @Valid AddMoneyDTO trader)
    {
        traderService.addMoneyToTrader(trader);
    }
}
