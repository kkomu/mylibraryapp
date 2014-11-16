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
public class DVD extends LibraryItem {
    private String director;
    final private int borrowTime = 7;
    
    public DVD(String director, String title, String genre, int year, int isbn) {
        super(title, genre, year, isbn);
        this.director = director;
    }
    
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    
    @Override
    public String getItemInformation() {
        return ("[Nimi: "+getTitle()+ "] [Ohjaaja: "+getDirector()+ 
                "] [Genre: "+getGenre()+"] [Julkaisuvuosi: "+getYear()+
                "] [Tyyppi: DVD]");
    }

    @Override
    public void borrowItem(User user, boolean status) {
        LocalDateTime now = LocalDateTime.now();
        super.setBorrowStatus(true);
        super.setUser(user);
        super.setBorrowDate(now);
        super.setDueDate(now.plusDays(borrowTime));
    }
    
}
