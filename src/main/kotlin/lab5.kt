import java.time.Year

data class Book(val name: String, val author: Author, val genre: Genre, val year: Year)
data class Author(val name: String)
data class User(val name: String)

enum class Genre {
    ADVENTURE,
    CLASSIC,
    DETECTIVE,
    FANTASY,
    HORROR,
    UTOPIA
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}

interface LibraryService {
    fun findBooks(substring: String): List<Book>
    fun findBooks(author: Author): List<Book>
    fun findBooks(year: Year): List<Book>
    fun findBooks(genre: Genre): List<Book>

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status?
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book, status: Status = Status.Available)

    fun registerUser(user: User)
    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
}

class Library(
    private val bookList: MutableList<Book> = mutableListOf(),
    private val userList: MutableList<User> = mutableListOf(),
    private val bookStatuses: MutableMap<Book, Status> = mutableMapOf(),
    private val bookLimit : Int = 3
) : LibraryService {
    override fun findBooks(substring: String): List<Book> {
        return bookList.filter { book -> book.name.contains(substring) }
    }

    override fun findBooks(genre: Genre): List<Book> {
        return bookList.filter { book -> book.genre == genre }
    }

    override fun getAllBooks(): List<Book> {
        return bookList.toList()
    }

    override fun getAllAvailableBooks(): List<Book> {
        return bookList.filter { book -> bookStatuses[book] == Status.Available }
    }

    override fun getBookStatus(book: Book): Status? {
        return bookStatuses[book]
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        return bookStatuses.toMap()
    }

    override fun setBookStatus(book: Book, status: Status) {
        if (book !in bookList)
            throw IllegalArgumentException("This book not in library")
        bookStatuses[book] = status
    }

    override fun addBook(book: Book, status: Status) {
        if (book !in bookList) {
            bookList.add(book)
            bookStatuses[book] = status
        } else throw IllegalArgumentException("This book is already in library")
    }

    override fun registerUser(user: User) {
        if (user !in userList)
            userList.add(user)
        else throw IllegalArgumentException("This user is already in library")
    }

    override fun unregisterUser(user: User) {
        if (user in userList)
            userList.remove(user)
        else throw IllegalArgumentException("This user not in library")
    }

    override fun takeBook(user: User, book: Book) {
        if (user !in userList)
            throw IllegalArgumentException("This user not in library")
        if (book !in bookList)
            throw IllegalArgumentException("This book not in library")
        if (bookStatuses[book] != Status.Available)
            throw IllegalArgumentException("This book isn`t available to take")
        if (countBooks(user) > bookLimit)
            throw IllegalArgumentException("User have got more than 3 book on loan")
        bookStatuses[book] = Status.UsedBy(user)
    }

    override fun returnBook(book: Book) {
        if (book !in bookList)
            throw IllegalArgumentException("This book not in library")
        if (bookStatuses[book] == Status.Available || bookStatuses[book] == Status.ComingSoon || bookStatuses[book] == Status.Restoration)
            throw IllegalArgumentException("This book isn`t taken")
        bookStatuses[book] = Status.Available
    }

    override fun findBooks(author: Author): List<Book> {
        return bookList.filter { book -> book.author.name == author.name}
    }

    override fun findBooks(year: Year): List<Book> {
        return bookList.filter { book -> book.year == year }
    }

    fun countBooks(user: User): Int {
        var cnt = 0
        for (curValue in bookStatuses.values)
            if (curValue is Status.UsedBy && curValue.user == user) cnt++
        return cnt
    }
}
