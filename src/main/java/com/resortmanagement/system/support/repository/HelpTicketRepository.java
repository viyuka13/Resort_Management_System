package com.resortmanagement.system.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resortmanagement.system.support.entity.HelpTicket;

@Repository
public interface HelpTicketRepository extends JpaRepository<HelpTicket, Long> {
    // TODO: add custom queries if needed
}
