/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ohjelmistokehitys
 */
public class User {
    private String firstName;
    private String lastName;
    
    /**
     * 
     * @param firstName
     * @param lastName 
     */
    public User(String firstName, String lastName) {
        if (firstName.length() >= 2) {
            this.firstName = firstName;
        }
        if (lastName.length() >= 2) {
            this.lastName = lastName;
        }
        //userItems = new ArrayList<>();
    }
    
    /**
     * 
     * @return 
     */
    public String getUserInfo() {
        return (firstName +" "+ lastName);
    }
    
}
