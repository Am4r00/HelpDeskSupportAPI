package com.helpdesk.supportapi.service;

import com.helpdesk.supportapi.model.entity.TicketHistory;
import com.helpdesk.supportapi.model.entity.Ticket;
import com.helpdesk.supportapi.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TicketHistoryService {

    public TicketHistory createCallHistory(User responsibleUser, Ticket ticket, String message){
        TicketHistory ticketHistory = new TicketHistory();

        ticketHistory.setEventDescription(message);
        ticketHistory.setUser(responsibleUser);
        ticketHistory.setCalled(ticket);

        return ticketHistory;
    }
}
