package com.helpdesk.supportapi.service;

import com.helpdesk.supportapi.model.entity.CallHistory;
import com.helpdesk.supportapi.model.entity.Called;
import com.helpdesk.supportapi.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class CallHistoryService {

    public CallHistory createCallHistory(User responsibleUser, Called called,String message){
        CallHistory callHistory = new CallHistory();

        callHistory.setEventDescription(message);
        callHistory.setUser(responsibleUser);
        callHistory.setCalled(called);

        return callHistory;
    }
}
