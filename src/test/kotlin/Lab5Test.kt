import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.Exception
import java.time.Year

internal class Lab5Test {

    @Test
    fun testBookAdding() {
        val lib = Library()
        var result: Exception? = null
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        lib.addBook(book)
        try {
            lib.addBook(book)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testUserRegistration() {
        val lib = Library()
        var result: Exception? = null
        val user = User("Тарабурин Александр")
        lib.registerUser(user)
        try {
            lib.registerUser(user)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testUserUnregistration() {
        val lib = Library()
        var result: Exception? = null
        val user = User("Тарабурин Александр")
        lib.registerUser(user)
        lib.unregisterUser(user)
        try {
            lib.unregisterUser(user)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testTakeBookCorrect() {
        val lib = Library()
        val user = User("Тарабурин Александр")
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        lib.registerUser(user)
        lib.addBook(book)
        lib.takeBook(user, book)
        assertTrue(lib.countBooks(user) == 1)
    }

    @Test
    fun testTakeBookUserError() {
        val lib = Library()
        var result: Exception? = null
        val user = User("Тарабурин Александр")
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        lib.addBook(book)
        try {
            lib.takeBook(user, book)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testTakeBookBookError() {
        val lib = Library()
        var result: Exception? = null
        val user = User("Тарабурин Александр")
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        lib.registerUser(user)
        try {
            lib.takeBook(user, book)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testTakeBookAvailableError() {
        val lib = Library()
        var result: Exception? = null
        val user = User("Тарабурин Александр")
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        lib.registerUser(user)
        lib.addBook(book)
        lib.takeBook(user, book)
        try {
            lib.takeBook(user, book)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testTakeBookLimitError() {
        val lib = Library()
        var result: Exception? = null
        val user = User("Тарабурин Александр")
        val book1 =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        val book3 = Book("Кладбище домашних животных", Author("Стивен Кинг"), Genre.FANTASY, Year.parse("1983"))
        val book4 =
            Book("Преступление и наказание", Author("Фёдор Достоевский"), Genre.CLASSIC, Year.parse("1866"))
        lib.registerUser(user)
        lib.addBook(book1)
        lib.takeBook(user, book1)
        lib.addBook(book2)
        lib.takeBook(user, book2)
        lib.addBook(book3)
        lib.takeBook(user, book3)
        try {
            lib.takeBook(user, book4)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testGetBookStatusCorrect() {
        val lib = Library()
        val user = User("Тарабурин Александр")
        val book1 =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        lib.registerUser(user)
        lib.addBook(book1)
        lib.addBook(book2)
        lib.takeBook(user, book2)
        assertTrue(lib.getBookStatus(book1) == Status.Available && lib.getBookStatus(book2) == Status.UsedBy(user))
    }

    @Test
    fun testGetBookStatusBookError() {
        val lib = Library()
        var result: Exception? = null
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        try {
            lib.getBookStatus(book)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testSetBookStatusCorrect() {
        val lib = Library()
        val book1 =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        lib.addBook(book1)
        lib.addBook(book2)
        lib.setBookStatus(book2, Status.ComingSoon)
        assertTrue(lib.getBookStatus(book1) == Status.Available && lib.getBookStatus(book2) == Status.ComingSoon)
    }

    @Test
    fun testSetBookStatusBookError() {
        val lib = Library()
        var result: Exception? = null
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        try {
            lib.setBookStatus(book, Status.ComingSoon)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testReturnBookCorrect() {
        val lib = Library()
        val user = User("Тарабурин Александр")
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        lib.registerUser(user)
        lib.addBook(book)
        lib.takeBook(user, book)
        lib.returnBook(book)
        assertNotNull(lib.countBooks(user) == 0)
    }

    @Test
    fun testReturnBookBookError() {
        val lib = Library()
        var result: Exception? = null
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        try {
            lib.returnBook(book)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testReturnBookStatusError() {
        val lib = Library()
        var result: Exception? = null
        val book =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        lib.addBook(book)
        try {
            lib.returnBook(book)
        } catch (e: Exception) {
            result = e
        }
        assertNotNull(result)
    }

    @Test
    fun testGetAllBookStatuses() {
        val lib = Library()
        val book1 =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        val book3 = Book("Кладбище домашних животных", Author("Стивен Кинг"), Genre.FANTASY, Year.parse("1983"))
        lib.addBook(book1)
        lib.addBook(book2)
        lib.addBook(book3)
        lib.setBookStatus(book2, Status.ComingSoon)
        lib.setBookStatus(book3, Status.Restoration)
        val bookStatuses: Map<Book, Status> =
            mapOf(book1 to Status.Available, book2 to Status.ComingSoon, book3 to Status.Restoration)
        assertTrue(bookStatuses == lib.getAllBookStatuses())
    }

    @Test
    fun testGetAllBooks() {
        val lib = Library()
        val book1 =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        val book3 = Book("Кладбище домашних животных", Author("Стивен Кинг"), Genre.FANTASY, Year.parse("1983"))
        lib.addBook(book1)
        lib.addBook(book2)
        lib.addBook(book3)
        val books: List<Book> = listOf(book1, book2, book3)
        assertTrue(books == lib.getAllBooks())
    }

    @Test
    fun testGetAllAvailableBooks() {
        val lib = Library()
        val book1 =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        val book3 = Book("Кладбище домашних животных", Author("Стивен Кинг"), Genre.FANTASY, Year.parse("1983"))
        lib.addBook(book1)
        lib.addBook(book2)
        lib.addBook(book3)
        lib.setBookStatus(book2, Status.ComingSoon)
        val books: List<Book> = listOf(book1, book3)
        assertTrue(books == lib.getAllAvailableBooks())
    }

    @Test
    fun testFindBooksSubstring() {
        val lib = Library()
        val book1 =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        val book3 = Book("Кладбище домашних животных", Author("Стивен Кинг"), Genre.FANTASY, Year.parse("1983"))
        lib.addBook(book1)
        lib.addBook(book2)
        lib.addBook(book3)
        val books: List<Book> = listOf(book1)
        assertTrue(books == lib.findBooks("Девушка"))
    }

    @Test
    fun testFindBooksAuthor() {
        val lib = Library()
        val book1 =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        val book3 = Book("Кладбище домашних животных", Author("Стивен Кинг"), Genre.FANTASY, Year.parse("1983"))
        lib.addBook(book1)
        lib.addBook(book2)
        lib.addBook(book3)
        val books: List<Book> = listOf(book1)
        assertTrue(books == lib.findBooks(Author("Стиг Ларссон")))
    }

    @Test
    fun testFindBooksYear() {
        val lib = Library()
        val book1 =
            Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        val book3 = Book("Кладбище домашних животных", Author("Стивен Кинг"), Genre.FANTASY, Year.parse("1983"))
        lib.addBook(book1)
        lib.addBook(book2)
        lib.addBook(book3)
        val books: List<Book> = listOf(book1)
        assertTrue(books == lib.findBooks(Year.parse("2005")))
    }

    @Test
    fun testFindBooksGenre() {
        val lib = Library()
        val book1 = Book("Девушка с татуировкой дракона", Author("Стиг Ларссон"), Genre.DETECTIVE, Year.parse("2005"))
        val book2 = Book("Алмазная колесница", Author("Борис Акунин"), Genre.DETECTIVE, Year.parse("1999"))
        val book3 = Book("Кладбище домашних животных", Author("Стивен Кинг"), Genre.FANTASY, Year.parse("1983"))
        lib.addBook(book1)
        lib.addBook(book2)
        lib.addBook(book3)
        val books: List<Book> = listOf(book1, book2)
        assertTrue(books == lib.findBooks(Genre.DETECTIVE))
    }
}