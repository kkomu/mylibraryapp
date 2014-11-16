/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.time.LocalDateTime;

/**
 *
 * @author kyosti
 */
public class CD extends LibraryItem {
    
    private String composer;
    final private int borrowTime = 7;
    
    public CD(String composer, String title, String genre, int year, int isbn) {
        super(title, genre, year, isbn);
        this.composer = composer;
    }

    public String getComposer() {
        return composer;
    }
    
    @Override
    public void borrowItem(User user)
    {
        LocalDateTime now = LocalDateTime.now();
        super.setBorrowStatus(true);
        super.setUserBorrowed(user);
        super.setBorrowDate(now);
        super.setDueDate(now.plusDays(borrowTime));
    }
    
    @Override
    public String getItemInformation() {
        return ("[Nimi: "+getTitle()+ "] [Esittäjä: "+getComposer()+ 
                "] [Genre: "+getGenre()+" [Julkaisuvuosi: "+getYear()+
                "] [Tyyppi: CD]");
    }
    
    
}
