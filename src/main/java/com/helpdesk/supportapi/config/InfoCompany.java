package com.helpdesk.supportapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InfoCompany {

    @Autowired
    private CompanyConfig companyConfig;

    public void display(){
        System.out.println("Your sincerely " + companyConfig.getName() + " - " + companyConfig.getLocation());
    }
}
