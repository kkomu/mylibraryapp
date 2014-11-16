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
public abstract class LibraryItem {
    private boolean isBorrowed = false;
    private boolean isReserved = false;
    private User user;
    private String title;
    private String genre;
    private int year;
    private int isbn;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    
    public abstract String getItemInformation();
    public abstract void borrowItem(User user, boolean status);
    
    /**
     * 
     * @param title
     * @param genre
     * @param isbn
     * @param year 
     */
    public LibraryItem(String title, String genre, int year, int isbn) {
        this.title = title;
        this.genre = genre;
        this.isbn = isbn;
        this.year = year;
        this.user = null;
    }
    
    protected void setUser(User user) {
        this.user = user;
    }
    
    protected void setBorrowDate(LocalDateTime loanDate) {
        this.borrowDate = loanDate;
    }
    
    protected void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    
    protected void setBorrowStatus(boolean status) {
        this.isBorrowed = status;
    }
    
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    public void returnItem() {
        this.user = null;
        this.isBorrowed = false;
    }
    
    public User getUser() {
        return user;
    }
    
    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }
    
    public boolean getBorrowStatus() {
        return isBorrowed;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    

}
