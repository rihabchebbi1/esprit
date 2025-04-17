package tn.esprit.gaspillagezero.services.Authentication_User_Management_Service.Mailing;

import lombok.Getter;

@Getter
public enum EmailTemp {

    ACTIVATE_ACCOUNT("activate_account");

    private final String name;

    // Constructor must match enum name
    EmailTemp(String name) {
        this.name = name;
    }
}