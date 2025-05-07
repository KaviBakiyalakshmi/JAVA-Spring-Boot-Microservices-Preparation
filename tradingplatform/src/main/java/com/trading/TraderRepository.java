package com.trading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraderRepository extends JpaRepository<Trader,Long> {

    //1.boolean existsByEmail(String email);
    // get by email
    Optional<Trader> findByEmail(String email);

    //2.get all details
    List<Trader> findAllByOrderByIdAsc();










}
