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
    private User userBorrowed;
    private User userReserved;
    private String title;
    private String genre;
    private int year;
    private int isbn;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    
    public abstract String getItemInformation();
    public abstract void borrowItem(User user);
        
    /**
     * Constructor
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
        this.userBorrowed = null;
        this.userReserved = null;
    }
    
    /**
     * Mark item reserved by user
     * @param user 
     */
    public void reserveItem(User user) {
        this.userReserved = user;
        this.isReserved = true;
    }
    
    /**
     *
     * @param user 
     */
    protected void setUserBorrowed(User user) {
        this.userBorrowed = user;
    }
    
    /**
     * 
     * @param status 
     */
    protected void setBorrowStatus(boolean status) {
        this.isBorrowed = status;
    }
    
    /**
     * 
     * @param loanDate 
     */
    protected void setBorrowDate(LocalDateTime loanDate) {
        this.borrowDate = loanDate;
    }
    
    /**
     * 
     * @param dueDate 
     */
    protected void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    
    /**
     * 
     * @return 
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    /**
     * 
     */
    public void returnItem() {
        this.userBorrowed = null;
        this.isBorrowed = false;
    }
    
    /**
     * 
     * @return 
     */
    protected User getUserBorrowed() {
        return userBorrowed;
    }
    
    /**
     * 
     * @return 
     */
    protected LocalDateTime getBorrowDate() {
        return borrowDate;
    }
    
    /**
     * 
     * @return 
     */
    protected boolean getBorrowStatus() {
        return isBorrowed;
    }
    
    /**
     * 
     * @return 
     */
    protected String getTitle() {
        return title;
    }
    
    /**
     * 
     * @param title 
     */
    protected void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * 
     * @return 
     */
    protected String getGenre() {
        return genre;
    }
    
    /**
     * 
     * @param genre 
     */
    protected void setGenre(String genre) {
        this.genre = genre;
    }
    
    /**
     * 
     * @return 
     */
    protected int getYear() {
        return year;
    }
    
    /**
     * 
     * @param year 
     */
    protected void setYear(int year) {
        this.year = year;
    }
    
    /**
     * 
     * @return 
     */
    protected int getIsbn() {
        return isbn;
    }
    
    /**
     * 
     * @param isbn 
     */
    protected void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}
