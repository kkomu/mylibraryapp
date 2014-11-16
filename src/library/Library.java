/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 *
 * @author Ohjelmistokehitys
 */
public class Library {
    private List<LibraryItem> collection;
     
    /**
     * Constructor
     */
    public Library() {
        collection = new ArrayList<>();
        
        collection.add(new Book("King, Stephen","Carrie","Kauhu",1974,199,1111));
        collection.add(new Book("King, Stephen","Tukikohta","Kauhu",1974,199,1122));
        collection.add(new Book("Simmons, Dan","Kesäyön kauhu","Kauhu",1992,473,1133));
        collection.add(new Book("Hornby, Nick","Hornankattila","omaelämänkerta",1992,247,1144));
        
        collection.add(new CD("Nirvana","Nevermind","Alt.rock",1991,2211));
        collection.add(new CD("Nirvana","In Utero","Alt.rock",1993,2222));
        collection.add(new CD("Metallica","Master of Puppets","Thrash metal",1986,2233));
        collection.add(new CD("Metallica","Ride the Lightning","Thrash metal",1984,2244));
        
        collection.add(new DVD("Cameron, James","The Terminator","Toiminta",1984,3311));
        collection.add(new DVD("Carpenter, John","The Thing","Kauhu",1982,3322));
        collection.add(new DVD("Scott, Ridley","Alien","Kauhu",1977,3333));
        collection.add(new DVD("Ferrelly bros","Dumb & Dumber","Komedia",1994,3344));
                
        collection.add(new Magazine("Fokusmedia","Pelit","Harrastukset",1992,6,2235));
        collection.add(new Magazine("Fokusmedia","Pelit","Harrastukset",1992,7,2236));
        collection.add(new Magazine("Sanoma","Tiede","Tiedelehdet",2002,1,2245));
        collection.add(new Magazine("Sanoma","Vauva","Perhelehdet",1999,11,2256));
    }
    
    /**
     * Borrows item from collection by isbn and returns item
     * @param user
     * @param isbn
     * @return 
     */
    public LibraryItem borrowItem(User user, int isbn) {
        LibraryItem foundItem = null;
        //System.out.printf("ISBN: %d\n",isbn);
        for(LibraryItem item: collection) {
            if ( isbn == item.getIsbn() ) {
                if (!item.getBorrowStatus()) {
                    item.borrowItem(user);
                    foundItem = item;
                }
            }
        }
        return foundItem;
    }
    
    public void reserveItem(User user, int isbn) {
        //LibraryItem foundItem = null;
        //System.out.printf("ISBN: %d\n",isbn);
        for(LibraryItem item: collection) {
            if ( isbn == item.getIsbn() ) {
                if (!item.getBorrowStatus()) {
                    item.reserveItem(user);
          //          foundItem = item;
                }
            }
        }
        //return foundItem;
    }
    
    
    /**
     * Searches from collection by keyword and returns ArrayList
     * @param title
     * @return 
     */
    public List<LibraryItem> searchItemByTitle(String title) {
        List<LibraryItem> foundItems = new ArrayList<>();
        for(LibraryItem item: collection) {
            if ( item.getTitle().contains(title) ) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }
    
    /**
     * Finds all borrowed items for user
     * @param user
     * @return 
     */
    public List<LibraryItem> getUserBorrowList(User user) {
        List<LibraryItem> foundItems = new ArrayList<>();

        for(LibraryItem item: collection) {
            if ( item.getUserBorrowed() == user ) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }
    
    /**
     * Returns item to collection
     * @param isbn
     * @return 
     */
    public LibraryItem returnItem(int isbn) {
        LibraryItem foundItem = null;
        
        for(LibraryItem item: collection) {
            if (isbn == item.getIsbn()) {
                if (item.getBorrowStatus()) {
                    item.returnItem();
                    foundItem = item;
                }
            }
        }
        return foundItem;
    }
    
    /**
     * Returns whole collection as ListArray
     * @return 
     */
    public List<LibraryItem> getCollection() {
        return collection;
    }
    
    /**
     * Deletes item from library collection
     * @param index
     * @return 
     */
    public LibraryItem deleteItemFromCollection(int index) {
        LibraryItem deletedItem = null;
        deletedItem = collection.remove(index);
        /*
        System.out.printf("ISBN: %d\n",isbn);
        for(LibraryItem item: collection) {
            System.out.printf("%d\n",item.getIsbn());
            if (isbn == item.getIsbn()) {
                    collection.remove(item);
                    deletedItem = item;
                }
            }
        */
        return deletedItem;
    }
    
    /**
     * Adds item to library collection
     * @param item 
     */
    public void addItemToCollection(LibraryItem item) {
        collection.add(item);
    }
    
    /**
     * Finds items with due date in past
     * @return
     */
    public List<LibraryItem> getDueDateList() {
        List<LibraryItem> dueDateItems = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        //System.out.printf("%s\n", now.toString());
        for(LibraryItem item: collection) {
            if (item.getBorrowStatus()) {
                LocalDateTime dueDate = item.getDueDate();
                if (now.isAfter(dueDate)) {
                    dueDateItems.add(item);
                }
            }
         }
        return dueDateItems;
    }
    
}
