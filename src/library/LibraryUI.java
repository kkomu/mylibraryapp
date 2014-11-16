/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

//import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author Ohjelmistokehitys
 */
public class LibraryUI {
    private Library library;
    private User user;
    private Scanner scan;
    
    /**
     * Constructor with arguments
     */
    public LibraryUI() {
        scan = new Scanner(System.in);
        library = new Library();
    }
    
    /**
     * Ask user name and create user object
     */
    private void addUser() {
        String firstName;
        String lastName;
        
        System.out.print("Anna etunimi: ");
        firstName = scan.nextLine();
        
        System.out.print("Anna sukunimi: ");
        lastName = scan.nextLine();
        
        user = new User(firstName, lastName);
    }
    
    /**
     * Reset scanner pipe and get ready for next input
     */
    public void resetScanner() {
        scan.reset();
        scan.skip("\n");
    }
    
    /**
     * Show menu options for Normal user
     */
    private void showUserUI() {
        int selection = 0;
        
        do {
            System.out.println("******** Library ********");
            System.out.println("1. Lainaa");
            System.out.println("2. Palauta");
            System.out.println("3. Näytä lainat");
            System.out.println("4. Lopeta");
            System.out.print("Valinta: ");
            selection = scan.nextInt();
            resetScanner();
                        
            switch (selection) {
                case 1:
                    handleMenuSelectionBorrow();
                    break;
                    
                case 2:
                    handleMenuSelectionReturn();
                    break;
                    
                case 3:
                    handleMenuSelectionList();
                    break;
                case 4:
                    // Exit
                    break;
                default:
                    System.out.println("Väärä valinta!");
            }
        } while (selection != 4);
        
    }
    
    /**
     * Handle user menu option 'Lainaa'
     */
    private void handleMenuSelectionBorrow() {
        LibraryItem item = null;
        
        System.out.print("Anna teoksen nimi: ");
        //Iterator<LibraryItem> i = library.searchItemByTitle(scan.nextLine()).iterator();
        List<LibraryItem> foundItems = library.searchItemByTitle(scan.nextLine());
        if (foundItems.isEmpty()) {
            System.out.println("Ei löytynyt mitään");
        }
        else {
            // Tulostetaan lista löytyneistä teoksista
            int index = 0;
            for(LibraryItem i: foundItems) {
                System.out.printf("%d: %s\n",index++,i.getItemInformation());
            }
            System.out.printf("%d: Lopeta\n",index);
            System.out.print("Lainaa teos valitsemalla numero: ");
            int borrowSelection = 0;
            do {
                borrowSelection = scan.nextInt();
                resetScanner();
            } while( borrowSelection > index);
            // Jos valittiin jotain muuta kuin poistu
            if (borrowSelection != index) {
                item = library.borrowItem(user, foundItems.get(borrowSelection).getIsbn());

                if (item == null) {
                    System.out.println("Teos on jo lainassa!");
                }
                else {
                System.out.print("Lainattiin: ");
                System.out.println(item.getItemInformation());
                }
            }
        }
    }
    
    /**
     * Handle user menu option 'Palauta'
     */
    private void handleMenuSelectionReturn() {
        LibraryItem item = null;
        List<LibraryItem> returnItems = library.getUserBorrowList(user);
        
        if (returnItems.isEmpty()) {
            System.out.println("Ei löytynyt mitään");
        }
        else {
            // Tulostetaan lista löytyneistä teoksista
            int index = 0;
            for(LibraryItem i: returnItems) {
                System.out.printf("%d: %s\n",index++,i.getItemInformation());
            }
            System.out.printf("%d: Lopeta\n",index);
            System.out.print("Palauta teos valitsemalla numero: ");
            int returnSelection = 0;
            do {
                returnSelection = scan.nextInt();
                resetScanner();
            } while( returnSelection > index);
            // Jos valittiin jotain muuta kuin poistu
            if (returnSelection != index)
                item = library.returnItem(returnItems.get(returnSelection).getIsbn());
        }
    }
    
    /**
     * Handle user menu option 'Näytä lainat'
     */
    private void handleMenuSelectionList() {
        List<LibraryItem> userItems = library.getUserBorrowList(user);
                
        if (userItems.isEmpty()) {
            System.out.println("Ei löytynyt mitään");
        }
        else {
            // Tulostetaan lista löytyneistä teoksista
            int index = 0;
            for(LibraryItem i: userItems) {
                System.out.printf("%d: %s\n",index++,i.getItemInformation());
            }
        }
    }
    
    /**
     * Show menu options for Administrator
     */
    private void showAdminUI() {
        int selection = 0;
        
        do {
            System.out.println("******** Library ********");
            System.out.println("1. Lisää");
            System.out.println("2. Poista");
            System.out.println("3. Listaa kaikki");
            System.out.println("4. Etsi nimellä");
            System.out.println("5. Näytä myöhässä olevat");
            System.out.println("6. Lopeta");
            System.out.print("Valinta: ");
            selection = scan.nextInt();
            resetScanner();
            //handleAdminMenuSelection(selection);
            switch (selection) {
                case 1:
                    handleAdminMenuSelectionAdd();
                    break;
                case 2:
                    handleAdminMenuSelectionDelete();
                    break;
                case 3:
                    handleAdminMenuSelectionList();
                    break;
                case 4:
                    handleAdminMenuSelectionSearch();
                    break;
                case 5:
                    handleAdminMenuSelectionShowDue();
                    break;
                case 6:
                    // Exit
                    break;
                default:
                    System.out.printf("Virheellinen valinta!");
                    break;
            }
        } while (selection != 6);
    }
    
    /**
     * Handle admin menu option 'Lisää'
     */
    private void handleAdminMenuSelectionAdd() {
        String type;
        
        System.out.print("Anna tyyppi [CD/Kirja/Lehti/DVD]: "); 
        do {
            type = scan.nextLine();
        } while (!type.matches("CD") && !type.matches("Kirja") && !type.matches("Lehti") && !type.matches("DVD"));
        
        switch (type) {
            case "CD":
                System.out.print("Anna esittäjä: ");
                String composer = scan.nextLine();
                System.out.print("Anna levyn nimi: ");
                String title = scan.nextLine();
                System.out.print("Anna levyn genre: ");
                String genre = scan.nextLine();
                System.out.print("Anna julkaisuvuosi: ");
                int year = scan.nextInt();
                System.out.print("Anna ISBN: ");
                int isbn = scan.nextInt();
                LibraryItem CD = new CD(composer,title,genre,year,isbn);
                library.addItemToCollection(CD);
                break;
            case "Kirja":
                System.out.print("Anna kirjailija [sukunimi, etunimi]: ");
                String author = scan.nextLine();
                System.out.print("Anna kirjan nimi: ");
                title = scan.nextLine();
                System.out.print("Anna kirjan genre: ");
                genre = scan.nextLine();
                System.out.print("Anna julkaisuvuosi: ");
                year = scan.nextInt();
                System.out.print("Anna sivumäärä: ");
                int pages = scan.nextInt();
                System.out.print("Anna ISBN: ");
                isbn = scan.nextInt();
                LibraryItem book = new Book(author,title,genre,year,pages,isbn);
                library.addItemToCollection(book);
                break;
            case "Lehti":
                System.out.print("Anna julkaisija: ");
                String publisher = scan.nextLine();
                System.out.print("Anna lehden nimi: ");
                title = scan.nextLine();
                System.out.print("Anna lehden genre: ");
                genre = scan.nextLine();
                System.out.print("Anna julkaisuvuosi: ");
                year = scan.nextInt();
                System.out.print("Anna lehden numero: ");
                int issue = scan.nextInt();
                System.out.print("Anna ISBN: ");
                isbn = scan.nextInt();
                LibraryItem magazine = new Magazine(publisher,title,genre,year,issue,isbn);
                library.addItemToCollection(magazine);
                break;
            case "DVD":
                System.out.print("Anna ohjaaja: ");
                String director = scan.nextLine();
                System.out.print("Anna elokuvan nimi: ");
                title = scan.nextLine();
                System.out.print("Anna elokuvan genre: ");
                genre = scan.nextLine();
                System.out.print("Anna julkaisuvuosi: ");
                year = scan.nextInt();
                System.out.print("Anna ISBN: ");
                isbn = scan.nextInt();
                LibraryItem DVD = new DVD(director,title,genre,year,isbn);
                library.addItemToCollection(DVD);
                break;
            default:
        }
    }
    
    /**
     * Handle admin menu option 'Poista'
     */
    private void handleAdminMenuSelectionDelete() {
        LibraryItem item = null;
        List<LibraryItem> allItems = library.getCollection();
        
        int index = 0;
        for(LibraryItem i: allItems) {
            System.out.printf("%d: %s\n",index++,i.getItemInformation());
        }
        System.out.printf("%d: Lopeta\n",index);
        System.out.print("Poista teos valitsemalla numero: ");
        int deleteSelection = 0;
        do {
            deleteSelection = scan.nextInt();
            resetScanner();
        } while( deleteSelection > index);
        // Jos valittiin jotain muuta kuin poistu
        if (deleteSelection != index) {
            
            //library.deleteItemFromCollection(allItems.get(deleteSelection).getIsbn());
            item = library.deleteItemFromCollection(deleteSelection);
        
            if (item == null) {
                System.out.println("Ei onnistunut");
            }
            else {
                System.out.print("Poistettiin: ");
                System.out.println(item.getItemInformation());
            }
        }
        
    }
    
    /**
     * Handle admin menu option 'Listaa kaikki'
     */
    private void handleAdminMenuSelectionList() {
        List<LibraryItem> allItems = library.getCollection();
        for(LibraryItem i: allItems) {
            System.out.printf("%s",i.getItemInformation());
            if (i.getBorrowStatus()) {
                System.out.printf(" Lainassa: %s",i.getUser().getUserInfo());
            }
            System.out.println();
        }
    }
    
    /**
     * Handle admin menu option 'Etsi nimellä'
     */
    private void handleAdminMenuSelectionSearch() {
        System.out.print("Anna teoksen nimi: ");
        //Iterator<LibraryItem> i = library.searchItemByTitle(scan.nextLine()).iterator();
        List<LibraryItem> foundItems = library.searchItemByTitle(scan.nextLine());
        if (foundItems.isEmpty()) {
            System.out.println("Ei löytynyt mitään");
        }
        else {
            // Tulostetaan lista löytyneistä teoksista
            for(LibraryItem i: foundItems) {
                System.out.printf("%s\n",i.getItemInformation());
            }
        }
    }
    
    /**
     * Handle admin menu option 'Näytä myöhässä olevat'
     */
    private void handleAdminMenuSelectionShowDue() {
        List<LibraryItem> dueDateItems = library.getDueDateList();
        
        if (dueDateItems.isEmpty()) {
            System.out.println("Ei löytynyt mitään");
        }
        else {
            for(LibraryItem i: dueDateItems) {
                System.out.printf("%s",i.getItemInformation());
            }
        }
    }
    
    /**
     * 
     * @param args 
     */
    public void start(String[] args) {
        //if (args.length == 0) {
        //    System.out.println("Please give 'user' or 'admin' as parameter");
        //}
        //else {
        int selection = 0;
        do {
            System.out.println("Valitse:");
            System.out.println("1) Normaali käyttäjä");
            System.out.println("2) Admin");
            System.out.println("3) Lopeta");
            System.out.print("Valinta: ");
        
            selection = scan.nextInt();
            resetScanner();
            
            switch (selection) {
                case 1:
                    //System.out.println("I'm user");
                    addUser();
                    System.out.printf("Tervetuloa: %s\n",user.getUserInfo());
                    showUserUI();
                    break;
                case 2:
                    //System.out.println("I'm admin");
                    showAdminUI();
                    break;
                case 3:
                    // Option to exit
                    break;
                default:
                    System.out.println("Neither 'user' or 'admin'");
                    System.exit(1);
            }

        } while (selection != 3);
    }
}
    
