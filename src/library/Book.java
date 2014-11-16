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
public class Book extends LibraryItem {
    private String author;
    private int pageCount;
    private final int borrowTime = 30;
    
    /**
     * 
     * @param author
     * @param title
     * @param genre
     * @param year
     * @param pageCount
     * @param isbn 
     */
    public Book(String author, String title, String genre, int year, int pageCount, int isbn) {
        super(title, genre, year, isbn);
        this.author = author;
        this.pageCount = pageCount;
    }
    
    /**
     * 
     * @return 
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * 
     * @return 
     */
    public int getPageCount() {
        return pageCount;
    }
    
    /**
     * Sets book status as borrowed
     * @param user
     * @param status 
     */
    @Override
    public void borrowItem(User user, boolean status)
    {
        LocalDateTime now = LocalDateTime.now();
        super.setBorrowStatus(true);
        super.setUser(user);
        super.setBorrowDate(now);
        super.setDueDate(now.plusDays(borrowTime));
    }
    
    /**
     * Constructs string and returns information about the Book
     * @return 
     */
    @Override
    public String getItemInformation() {
        return ("[Nimi: "+getTitle()+ "] [Kirjailija: "+getAuthor()+ 
                "] [Genre: "+getGenre()+"] Sivumäärä: "+getPageCount()+
                "[Julkaisuvuosi: "+getYear()+"] [Tyyppi: Kirja]");
    }
}
