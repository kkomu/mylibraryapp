/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

/**
 *
 * @author Ohjelmistokehitys
 */
public class LibraryMain {
    
    public static void main(String[] args) {
        LibraryUI lib = new LibraryUI();
        lib.start(args);
    }
}

// Iterator<LibraryItem> i = items.iterator();
// for(; i.hasNext();) {
//  LibraryItem item = i.next();
//  item.printInformation();
// }