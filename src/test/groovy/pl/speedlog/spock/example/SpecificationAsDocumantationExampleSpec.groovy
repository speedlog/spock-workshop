package pl.speedlog.spock.example

import spock.lang.Specification

/**
 * Przykład stylu dokumentowania testów - "specification as documentation".
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
class SpecificationAsDocumantationExampleSpec extends Specification {

    def "Should return books written by specified author"() {
        given: "library with a book 'Green mile' written by Stephen King"
            def library = new Library()
            def greenMileBook = new Book(title: "Green mile", author: "Stephen King")
            library.books << greenMileBook
        and: "with a book 'Inferno' written by Dan Brown"
            def infernoBook = new Book(title: "Inferno", author: "Dan Brown")
            library.books << infernoBook
        when: "search all books written by Dan Brown"
            def result = library.searchByAuthor("Dan Brown")
        then: "returns 'Inferno' book"
            result.size() == 1
            result[0] == infernoBook
    }

    class Library {

        List<Book> books = []

        List<Book> searchByAuthor(String author) {
            books.findAll({it.author == author })
        }
    }

    class Book {
        String author
        String title
    }

}