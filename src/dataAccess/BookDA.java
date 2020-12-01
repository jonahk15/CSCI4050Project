package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controllers.CryptoHelper;
import models.Book;
import models.BookMarketingAttribute;
import models.User;
import models.UserStatus;
import models.UserType;

public class BookDA implements IBookDA {

	private static final String useDBQuery = "USE BookBayDB";
	
	private static final String addBookQuery = "INSERT INTO Book(Title, Author, SellingPrice,"
			+ " ISBN, Genre, `Description`, PublicationYear, ImagePath, BuyPrice, Edition, Publisher,"
			+ " Quantity, MinThreshold, Archived, MarketingAttribute) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String getAllBooksQuery = "SELECT * FROM Book;";
	
	private static final String getAllFeaturedBooksQuery = "SELECT * FROM Book WHERE MarketingAttribute = 'FEATURED';";
	
	private static final String getAllTopSellingBooksQuery = "SELECT * FROM Book WHERE MarketingAttribute = 'TOPSELLER';";
	
	private static final String getBooksByKeywordQuery = "SELECT * FROM Book "
													   + "WHERE BookID like ? "
													   + "or Title like ? "
													   + "or Author like ? "
													   + "or SellingPrice like ? "
													   + "or ISBN like ? "
													   + "or Genre like ? "
													   + "or `Description` like ? "
													   + "or PublicationYear like ? "
													   + "or ImagePath like ? "
													   + "or BuyPrice like ? "
													   + "or Edition like ? "
													   + "or Publisher like ? "
													   + "or Quantity like ? "
													   + "or MinThreshold like ? "
													   + "or Archived like ? "
													   + "or MarketingAttribute like ?;";
	
	private static final String getBookByIDQuery = "SELECT * FROM Book "
												 + "WHERE BookID = ?;";
	
	private static final String getBookByISBNQuery = "SELECT * FROM Book "
			 								        + "WHERE ISBN = ?;";
	
	private static final String getLastBookQuery = "SELECT * FROM Book "
		    									 + "ORDER BY BookID DESC LIMIT 1;";
	
	private static final String updateBookQuery = "UPDATE Book "
			 									+ "SET Title = ?,"
			 									+ "Author = ?,"
			 									+ "SellingPrice = ?,"
			 									+ "ISBN = ?,"
			 									+ "Genre = ?,"
			 									+ "`Description` = ?,"
			 									+ "PublicationYear = ?,"
			 									+ "ImagePath = ?,"
			 									+ "BuyPrice = ?,"
			 									+ "Edition = ?,"
			 									+ "Publisher = ?,"
			 									+ "Quantity = ?,"
			 									+ "MinThreshold = ?,"
			 									+ "Archived = ?,"
			 									+ "MarketingAttribute = ? "
			 									+ "WHERE BookID = ?;";

	private static String deleteBookQuery = "DELETE FROM Book WHERE BookID = ?;";
	
	@Override
	public void createBook(Book book) throws SQLException {
		String title = book.getTitle();
		String author = book.getAuthor();
		float sellingPrice = book.getSellingPrice();
		String isbn = book.getIsbn();
		String genre = book.getGenre();
		String description = book.getDescription();
		int publicationYear = book.getPublicationYear();
		String imagePath = book.getImagePath();
		float buyPrice = book.getBuyPrice();
		int edition = book.getEdition();
		String publisher = book.getPublisher();
		int quantity = book.getQuantity();
		int minThreshold = book.getMinThreshold();
		boolean archived = book.getArchived();
		String marketingAttribute = book.getMarketingAttribute().name();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
			
		PreparedStatement addBookStmt = connection.prepareStatement(addBookQuery);
		addBookStmt.setString(1, title);
		addBookStmt.setString(2, author);
		addBookStmt.setFloat(3, sellingPrice);
		addBookStmt.setString(4, isbn);
		addBookStmt.setString(5, genre);
		addBookStmt.setString(6, description);
		addBookStmt.setInt(7, publicationYear);
		addBookStmt.setString(8, imagePath);
		addBookStmt.setFloat(9, buyPrice);
		addBookStmt.setInt(10, edition);
		addBookStmt.setString(11, publisher);
		addBookStmt.setInt(12, quantity);
		addBookStmt.setInt(13, minThreshold);
		addBookStmt.setBoolean(14, archived);
		addBookStmt.setString(15, marketingAttribute);
		
		addBookStmt.executeUpdate();
		
		connection.close();
	}
	
	@Override
	public List<Book> getAllBooks() throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getAllBooksStmt = connection.prepareStatement(getAllBooksQuery);
		
		ResultSet allBooksRS = getAllBooksStmt.executeQuery();
		
		while(allBooksRS.next()) {
			Book book = new Book();
			
			int bookID = allBooksRS.getInt(1);
			String title = allBooksRS.getString(2);
			String author = allBooksRS.getString(3);
			float sellingPrice = allBooksRS.getFloat(4);
			String isbn = allBooksRS.getString(5);
			String genre = allBooksRS.getString(6);
			String description = allBooksRS.getString(7);
			int publicationYear = allBooksRS.getInt(8);
			String imagePath = allBooksRS.getString(9);
			float buyPrice = allBooksRS.getFloat(10);
			int edition = allBooksRS.getInt(11);
			String publisher = allBooksRS.getString(12);
			int quantity = allBooksRS.getInt(13);
			int minThreshold = allBooksRS.getInt(14);
		    boolean archived = allBooksRS.getBoolean(15);
			BookMarketingAttribute marketingAttribute = BookMarketingAttribute.valueOf(allBooksRS.getString(16));
		    
			book.setBookID(bookID);
			book.setTitle(title);
			book.setAuthor(author);
			book.setSellingPrice(sellingPrice);
			book.setIsbn(isbn);
			book.setGenre(genre);
			book.setDescription(description);
			book.setPublicationYear(publicationYear);
			book.setImagePath(imagePath);
			book.setBuyPrice(buyPrice);
			book.setEdition(edition);
			book.setPublisher(publisher);
			book.setQuantity(quantity);
			book.setMinThreshold(minThreshold);
			book.setArchived(archived);
			book.setMarketingAttribute(marketingAttribute);
			
			if(!book.getArchived())
				books.add(book);
		}
		
		connection.close();
		
		return books;
	}
	
	public List<Book> getAllFeaturedBooks() throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getAllBooksStmt = connection.prepareStatement(getAllFeaturedBooksQuery);
		
		ResultSet allBooksRS = getAllBooksStmt.executeQuery();
		
		while(allBooksRS.next()) {
			Book book = new Book();
			
			int bookID = allBooksRS.getInt(1);
			String title = allBooksRS.getString(2);
			String author = allBooksRS.getString(3);
			float sellingPrice = allBooksRS.getFloat(4);
			String isbn = allBooksRS.getString(5);
			String genre = allBooksRS.getString(6);
			String description = allBooksRS.getString(7);
			int publicationYear = allBooksRS.getInt(8);
			String imagePath = allBooksRS.getString(9);
			float buyPrice = allBooksRS.getFloat(10);
			int edition = allBooksRS.getInt(11);
			String publisher = allBooksRS.getString(12);
			int quantity = allBooksRS.getInt(13);
			int minThreshold = allBooksRS.getInt(14);
		    boolean archived = allBooksRS.getBoolean(15);
			BookMarketingAttribute marketingAttribute = BookMarketingAttribute.valueOf(allBooksRS.getString(16));
		    
			book.setBookID(bookID);
			book.setTitle(title);
			book.setAuthor(author);
			book.setSellingPrice(sellingPrice);
			book.setIsbn(isbn);
			book.setGenre(genre);
			book.setDescription(description);
			book.setPublicationYear(publicationYear);
			book.setImagePath(imagePath);
			book.setBuyPrice(buyPrice);
			book.setEdition(edition);
			book.setPublisher(publisher);
			book.setQuantity(quantity);
			book.setMinThreshold(minThreshold);
			book.setArchived(archived);
			book.setMarketingAttribute(marketingAttribute);
			
			if(!book.getArchived())
				books.add(book);
		}
		
		connection.close();
		
		return books;
	}
	
	public List<Book> getAllTopSellingBooks() throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getAllBooksStmt = connection.prepareStatement(getAllTopSellingBooksQuery);
		
		ResultSet allBooksRS = getAllBooksStmt.executeQuery();
		
		while(allBooksRS.next()) {
			Book book = new Book();
			
			int bookID = allBooksRS.getInt(1);
			String title = allBooksRS.getString(2);
			String author = allBooksRS.getString(3);
			float sellingPrice = allBooksRS.getFloat(4);
			String isbn = allBooksRS.getString(5);
			String genre = allBooksRS.getString(6);
			String description = allBooksRS.getString(7);
			int publicationYear = allBooksRS.getInt(8);
			String imagePath = allBooksRS.getString(9);
			float buyPrice = allBooksRS.getFloat(10);
			int edition = allBooksRS.getInt(11);
			String publisher = allBooksRS.getString(12);
			int quantity = allBooksRS.getInt(13);
			int minThreshold = allBooksRS.getInt(14);
		    boolean archived = allBooksRS.getBoolean(15);
			BookMarketingAttribute marketingAttribute = BookMarketingAttribute.valueOf(allBooksRS.getString(16));
		    
			book.setBookID(bookID);
			book.setTitle(title);
			book.setAuthor(author);
			book.setSellingPrice(sellingPrice);
			book.setIsbn(isbn);
			book.setGenre(genre);
			book.setDescription(description);
			book.setPublicationYear(publicationYear);
			book.setImagePath(imagePath);
			book.setBuyPrice(buyPrice);
			book.setEdition(edition);
			book.setPublisher(publisher);
			book.setQuantity(quantity);
			book.setMinThreshold(minThreshold);
			book.setArchived(archived);
			book.setMarketingAttribute(marketingAttribute);
			
			if(!book.getArchived())
				books.add(book);
		}
		
		connection.close();
		
		return books;
	}
	
	@Override
	public List<Book> getBooksByKeyword(String keyword) throws SQLException {
		if(keyword == null)
			keyword = "";
		
		keyword = "%" + keyword + "%";
		
		List<Book> books = new ArrayList<Book>();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getBooksByKeywordStmt = connection.prepareStatement(getBooksByKeywordQuery);
		getBooksByKeywordStmt.setString(1, keyword);
		getBooksByKeywordStmt.setString(2, keyword);
		getBooksByKeywordStmt.setString(3, keyword);
		getBooksByKeywordStmt.setString(4, keyword);
		getBooksByKeywordStmt.setString(5, keyword);
		getBooksByKeywordStmt.setString(6, keyword);
		getBooksByKeywordStmt.setString(7, keyword);
		getBooksByKeywordStmt.setString(8, keyword);
		getBooksByKeywordStmt.setString(9, keyword);
		getBooksByKeywordStmt.setString(10, keyword);
		getBooksByKeywordStmt.setString(11, keyword);
		getBooksByKeywordStmt.setString(12, keyword);
		getBooksByKeywordStmt.setString(13, keyword);
		getBooksByKeywordStmt.setString(14, keyword);
		getBooksByKeywordStmt.setString(15, keyword);
		getBooksByKeywordStmt.setString(16, keyword);
		
		ResultSet keywordBooksRS = getBooksByKeywordStmt.executeQuery();
		
		while(keywordBooksRS.next()) {
			Book book = new Book();
			
			int bookID = keywordBooksRS.getInt(1);
			String title = keywordBooksRS.getString(2);
			String author = keywordBooksRS.getString(3);
			float sellingPrice = keywordBooksRS.getFloat(4);
			String isbn = keywordBooksRS.getString(5);
			String genre = keywordBooksRS.getString(6);
			String description = keywordBooksRS.getString(7);
			int publicationYear = keywordBooksRS.getInt(8);
			String imagePath = keywordBooksRS.getString(9);
			float buyPrice = keywordBooksRS.getFloat(10);
			int edition = keywordBooksRS.getInt(11);
			String publisher = keywordBooksRS.getString(12);
			int quantity = keywordBooksRS.getInt(13);
			int minThreshold = keywordBooksRS.getInt(14);
		    boolean archived = keywordBooksRS.getBoolean(15);
			BookMarketingAttribute marketingAttribute = BookMarketingAttribute.valueOf(keywordBooksRS.getString(16));
		    
			book.setBookID(bookID);
			book.setTitle(title);
			book.setAuthor(author);
			book.setSellingPrice(sellingPrice);
			book.setIsbn(isbn);
			book.setGenre(genre);
			book.setDescription(description);
			book.setPublicationYear(publicationYear);
			book.setImagePath(imagePath);
			book.setBuyPrice(buyPrice);
			book.setEdition(edition);
			book.setPublisher(publisher);
			book.setQuantity(quantity);
			book.setMinThreshold(minThreshold);
			book.setArchived(archived);
			book.setMarketingAttribute(marketingAttribute);
			
			if(!book.getArchived())
				books.add(book);
		}
		
		connection.close();
		
		return books;
	}

	@Override
	public Book getBookByID(int bookID) throws SQLException {
		Book book = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getBookByIDPstmt = connection.prepareStatement(getBookByIDQuery);
		getBookByIDPstmt.setInt(1, bookID);    
		
		ResultSet bookRS = getBookByIDPstmt.executeQuery();
		    
		while(bookRS.next()) {
			book = new Book();
			
			int dbBookID = bookRS.getInt(1);
			String title = bookRS.getString(2);
			String author = bookRS.getString(3);
			float sellingPrice = bookRS.getFloat(4);
			String isbn = bookRS.getString(5);
			String genre = bookRS.getString(6);
			String description = bookRS.getString(7);
			int publicationYear = bookRS.getInt(8);
			String imagePath = bookRS.getString(9);
			float buyPrice = bookRS.getFloat(10);
			int edition = bookRS.getInt(11);
			String publisher = bookRS.getString(12);
			int quantity = bookRS.getInt(13);
			int minThreshold = bookRS.getInt(14);
		    boolean archived = bookRS.getBoolean(15);
			BookMarketingAttribute marketingAttribute = BookMarketingAttribute.valueOf(bookRS.getString(16));
		    
			book.setBookID(dbBookID);
			book.setTitle(title);
			book.setAuthor(author);
			book.setSellingPrice(sellingPrice);
			book.setIsbn(isbn);
			book.setGenre(genre);
			book.setDescription(description);
			book.setPublicationYear(publicationYear);
			book.setImagePath(imagePath);
			book.setBuyPrice(buyPrice);
			book.setEdition(edition);
			book.setPublisher(publisher);
			book.setQuantity(quantity);
			book.setMinThreshold(minThreshold);
			book.setArchived(archived);
			book.setMarketingAttribute(marketingAttribute);
		    
		    connection.close();
		    
		    return book;
		}
		    
		connection.close();
		
		return book;
	}
	
	@Override
	public Book getBookByISBN(String isbn) throws SQLException {
		Book book = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getBookByISBNPstmt = connection.prepareStatement(getBookByISBNQuery);
		getBookByISBNPstmt.setString(1, isbn);    
		
		ResultSet bookRS = getBookByISBNPstmt.executeQuery();
		    
		while(bookRS.next()) {
			book = new Book();
			
			int dbBookID = bookRS.getInt(1);
			String title = bookRS.getString(2);
			String author = bookRS.getString(3);
			float sellingPrice = bookRS.getFloat(4);
			String dbIsbn = bookRS.getString(5);
			String genre = bookRS.getString(6);
			String description = bookRS.getString(7);
			int publicationYear = bookRS.getInt(8);
			String imagePath = bookRS.getString(9);
			float buyPrice = bookRS.getFloat(10);
			int edition = bookRS.getInt(11);
			String publisher = bookRS.getString(12);
			int quantity = bookRS.getInt(13);
			int minThreshold = bookRS.getInt(14);
		    boolean archived = bookRS.getBoolean(15);
			BookMarketingAttribute marketingAttribute = BookMarketingAttribute.valueOf(bookRS.getString(16));

		    
			book.setBookID(dbBookID);
			book.setTitle(title);
			book.setAuthor(author);
			book.setSellingPrice(sellingPrice);
			book.setIsbn(dbIsbn);
			book.setGenre(genre);
			book.setDescription(description);
			book.setPublicationYear(publicationYear);
			book.setImagePath(imagePath);
			book.setBuyPrice(buyPrice);
			book.setEdition(edition);
			book.setPublisher(publisher);
			book.setQuantity(quantity);
			book.setMinThreshold(minThreshold);
		    book.setArchived(archived);
		    book.setMarketingAttribute(marketingAttribute);
			
		    connection.close();
		    
		    return book;
		}
		    
		connection.close();
		
		return book;
	}
	
	@Override
	public Book getLastBook() throws SQLException {
		Book book = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		    
		PreparedStatement getLastBookPstmt = connection.prepareStatement(getLastBookQuery); 
		
		ResultSet bookRS = getLastBookPstmt.executeQuery();
		    
		while(bookRS.next()) {
			book = new Book();
			
			int dbBookID = bookRS.getInt(1);
			String title = bookRS.getString(2);
			String author = bookRS.getString(3);
			float sellingPrice = bookRS.getFloat(4);
			String isbn = bookRS.getString(5);
			String genre = bookRS.getString(6);
			String description = bookRS.getString(7);
			int publicationYear = bookRS.getInt(8);
			String imagePath = bookRS.getString(9);
			float buyPrice = bookRS.getFloat(10);
			int edition = bookRS.getInt(11);
			String publisher = bookRS.getString(12);
			int quantity = bookRS.getInt(13);
			int minThreshold = bookRS.getInt(14);
		    boolean archived = bookRS.getBoolean(15);
			BookMarketingAttribute marketingAttribute = BookMarketingAttribute.valueOf(bookRS.getString(16));

		    
			book.setBookID(dbBookID);
			book.setTitle(title);
			book.setAuthor(author);
			book.setSellingPrice(sellingPrice);
			book.setIsbn(isbn);
			book.setGenre(genre);
			book.setDescription(description);
			book.setPublicationYear(publicationYear);
			book.setImagePath(imagePath);
			book.setBuyPrice(buyPrice);
			book.setEdition(edition);
			book.setPublisher(publisher);
			book.setQuantity(quantity);
			book.setMinThreshold(minThreshold);
		    book.setArchived(archived);
		    book.setMarketingAttribute(marketingAttribute);
			
		    connection.close();
		    
		    return book;
		}
		    
		connection.close();
		
		return book;
	}

	@Override
	public void updateBook(Book book) throws SQLException {
		int bookID = book.getBookID();
		String title = book.getTitle();
		String author = book.getAuthor();
		float sellingPrice = book.getSellingPrice();
		String isbn = book.getIsbn();
		String genre = book.getGenre();
		String description = book.getDescription();
		int publicationYear = book.getPublicationYear();
		String imagePath = book.getImagePath();
		float buyPrice = book.getBuyPrice();
		int edition = book.getEdition();
		String publisher = book.getPublisher();
		int quantity = book.getQuantity();
		int minThreshold = book.getMinThreshold();
		boolean archived = book.getArchived();
		String marketingAttribute = book.getMarketingAttribute().name();

		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement updateBookStmt = connection.prepareStatement(updateBookQuery);
		
		updateBookStmt.setString(1, title);
		updateBookStmt.setString(2, author);
		updateBookStmt.setFloat(3, sellingPrice);
		updateBookStmt.setString(4, isbn);
		updateBookStmt.setString(5, genre);
		updateBookStmt.setString(6, description);
		updateBookStmt.setInt(7, publicationYear);
		updateBookStmt.setString(8, imagePath);
		updateBookStmt.setFloat(9, buyPrice);
		updateBookStmt.setInt(10, edition);
		updateBookStmt.setString(11, publisher);
		updateBookStmt.setInt(12, quantity);
		updateBookStmt.setInt(13, minThreshold);
		updateBookStmt.setBoolean(14, archived);
		updateBookStmt.setInt(15, bookID);
		updateBookStmt.setString(16, marketingAttribute);
		
		updateBookStmt.executeUpdate();
		
		connection.close();
	}
	
	@Override
	public void deleteBook(Book book) throws SQLException {
		int bookID = book.getBookID();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement deleteBookStmt = connection.prepareStatement(deleteBookQuery);
		deleteBookStmt.setInt(1, bookID);
		
		deleteBookStmt.executeUpdate();
		
		connection.close();
	}

}
