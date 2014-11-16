/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.time.LocalDateTime;

/**
 *
 * @author Ohjelmistokehitys
 */
public class Magazine extends LibraryItem {
    private String publisher;
    private int issue;
    final private int borrowTime = 14;

    public String getPublisher() {
        return publisher;
    }

    public int getIssue() {
        return issue;
    }
   
    public Magazine(String publisher, String title, String genre, int year, int issue, int isbn) {
        super(title, genre, year, isbn);
        this.publisher = publisher;
        this.issue = issue;
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
        return ("[Nimi: "+getTitle()+ "] [Julkaisija: "+getPublisher()+ 
                "] [Genre: "+getGenre()+"] [Numero: "+getIssue()+
                "] [Julkaisuvuosi: "+getYear()+"] [Tyyppi: Lehti]");
    }
    
    
}
