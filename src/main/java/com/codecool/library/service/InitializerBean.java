package com.codecool.library.service;

import com.codecool.library.model.*;
import com.codecool.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitializerBean {

    @Autowired
    public InitializerBean(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, BookInstanceRepository bookInstanceRepository, PlaceRepository placeRepository, AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin("admin", bCryptPasswordEncoder.encode("admin"), true);
            adminRepository.save(admin);
        }

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("László", "Bock"));
        authors.add(new Author("Sue", "Hadfield"));
        authors.add(new Author("Gill", "Hasson"));
        authors.add(new Author("Josh", "Seiden"));
        authors.add(new Author("Jeff", "Gothelf"));
        authors.add(new Author("Chris", "McChesney"));
        authors.add(new Author("Sean", "Covey"));
        authors.add(new Author("Jim", "Huling"));
        authors.add(new Author("Ádám", "Nagy"));
        authors.add(new Author("Ágnes", "Antal"));
        authors.add(new Author("Mónika", "Holzer"));
        authors.add(new Author("Daniel H", "Pink"));
        authors.add(new Author("Herbert", "Schildt"));
        authors.add(new Author("Patric", "Lencioni"));
        authors.add(new Author("Carrie Anne", "Phillhin"));
        authors.add(new Author("Robert", "C. Martin"));
        authors.add(new Author("Max", "Kanat-Alexander"));
        authors.add(new Author("Domonkos", "Kotsis"));
        authors.add(new Author("Gábor", "Légrádi"));
        authors.add(new Author("Gergely", "Nagy"));
        authors.add(new Author("Sándor", "Szénási"));
        authors.add(new Author("Andrew", "Hunt"));
        authors.add(new Author("David", "Thomas"));
        authors.add(new Author("István", "Reiter"));
        authors.add(new Author("Carol S.", "Dweck"));
        authors.add(new Author("Josh", "Carter"));
        authors.add(new Author("József", "Kovács"));
        authors.add(new Author("Gábor", "Takács"));
        authors.add(new Author("Miklós", "Takács"));
        authors.add(new Author("Frederic", "Laloux"));
        authorRepository.saveAll(authors);

        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher("Bookline"));
        publishers.add(new Publisher("Scolar"));
        publishers.add(new Publisher("Harvard Business Review Press"));
        publishers.add(new Publisher("Simon & Schuster"));
        publishers.add(new Publisher("ISZT Alapítvány"));
        publishers.add(new Publisher("Riverhead Books"));
        publishers.add(new Publisher("Oracle Press"));
        publishers.add(new Publisher("HVG"));
        publishers.add(new Publisher("Wiley"));
        publishers.add(new Publisher("Prentice Hall"));
        publishers.add(new Publisher("O'Reilly"));
        publishers.add(new Publisher("Panem"));
        publishers.add(new Publisher("Addison-Wesley"));
        publishers.add(new Publisher("Jedlik Oktatási Stúdió"));
        publishers.add(new Publisher("The Pragmatic Bookshelf"));
        publishers.add(new Publisher("Nemzeti Tankönyvkiadó"));
        publishers.add(new Publisher("TalentSmart"));
        publishers.add(new Publisher("Aquilone"));
        publisherRepository.saveAll(publishers);

        List<Place> places = new ArrayList<>();
        places.add(new Place("Budapest"));
        places.add(new Place("Miskolc"));
        places.add(new Place("Krakow"));
        places.add(new Place("Warsaw"));
        placeRepository.saveAll(places);


//        Author author, String title, Publisher publisher, String location, Integer publicationYear
//        String title, String location, List<Author> authorList, Publisher publisher, Integer publicationYear
        List<Book> books = new ArrayList<>();
        books.add(new Book(authors.get(0),"A google-titok", publishers.get(0), "Szekszárd", 2015));
        books.add(new Book("Hogyan érvényesítsük sikeresen az érdekeinket? Asszertivitás", "Budapest", authors.subList(1,3), publishers.get(1), 2012));
        books.add(new Book("Sense &r respond", "Boston, Massachusetts", authors.subList(3,5), publishers.get(2), 2017));
        books.add(new Book("The 4 Disciplines of Execution", "London", authors.subList(5,8), publishers.get(3), 2012));
        books.add(new Book("Ifjúságügy-Módszertár 100 nonformális módszer és szituáció megoldása", "Budapest", authors.subList(8,11), publishers.get(4), 2015));
        books.add(new Book(authors.get(11),"DRIVE The Surprising Truth About What Motivate Us", publishers.get(5), "USA", 2009));
        books.add(new Book(authors.get(12),"Java A Beginner's Guide sixth edition", publishers.get(6), "USA", 2014));
        books.add(new Book(authors.get(13),"Az ideális csapatjátékos Hogyan fejlesszük az együttműködés alapkészségeit?", publishers.get(7), "Budapest", 2016));
        books.add(new Book(authors.get(14),"Adventures in Raspberry Pi", publishers.get(8), "UK", 2014));
        books.add(new Book(authors.get(15),"Clean Code", publishers.get(9), "USA", 2009));
        books.add(new Book(authors.get(16),"Code Simplicity", publishers.get(10), "USA", 2012));
        books.add(new Book("Többnyelvű programozástechnika", "Budapest", authors.subList(17,21), publishers.get(11), 2007));
        books.add(new Book("The Pragmatic Programmer", "USA", authors.subList(21,23), publishers.get(12), 2000));
        books.add(new Book("Analízis", "Budapest", authors.subList(26,29), publishers.get(16), 2007));
        books.add(new Book(authors.get(23),"C# programozás lépésről lépésre", publishers.get(13), "Budapest", 2014));
        books.add(new Book(authors.get(24),"Szemlélet váltás A siker új pszichológiája", publishers.get(7), "Budapest", 2015));
        books.add(new Book(authors.get(25),"New Programmer's Survival Manual", publishers.get(14), "Dallas, Texas", 2011));
        books.add(new Book(authors.get(29),"A jövő szervezetei", publishers.get(16), "Budapest", 2016));

        List<BookInstance> bookInstances = new ArrayList<>();
        for (Book book:
             books) {
            BookInstance bookInstance = new BookInstance(places.get(0));
            book.addInstance(bookInstance);
            bookInstances.add(bookInstance);
        }
        bookInstanceRepository.saveAll(bookInstances);
        bookRepository.saveAll(books);


    }
}
