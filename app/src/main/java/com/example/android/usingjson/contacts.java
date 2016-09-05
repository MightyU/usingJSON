package com.example.android.usingjson;

/**
 * Created by Uche on 8/22/2016.
 */
public class contacts {

    private String firstname, lastname, party;

    public contacts (String firstname, String lastname, String party){

        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setParty(party);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
}
