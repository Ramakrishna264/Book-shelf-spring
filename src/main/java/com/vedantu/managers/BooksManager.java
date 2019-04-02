package com.vedantu.managers;
import java.util.Collection;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;


import com.vedantu.daos.AbstractMongoDAO;
import com.vedantu.daos.BookMongoDAO;
import com.vedantu.enums.BookStatus;
import com.vedantu.exception.BadRequestException;
import com.vedantu.exception.VException;
import com.vedantu.enums.ErrorCode;
import com.vedantu.models.BookMongo;
import com.vedantu.requests.BookReq;
import com.vedantu.utils.LogFactory;
import com.vedantu.utils.StringUtils;



@Service
public class BooksManager extends AbstractMongoDAO{
	 @Autowired
	    private LogFactory logFactory;
	 
	  private Logger logger = logFactory.getLogger(BookMongoDAO.class);
	  
	@Autowired
	private BookMongoDAO bookDAO;
	
	@SuppressWarnings("null")
	public String addBook(BookReq bookReq) throws VException,Exception {
		bookReq.verify();
		if(StringUtils.isEmpty(bookReq.getTitle())) {
			throw new BadRequestException(ErrorCode.BAD_REQUEST_ERROR, "title is null");
		}
		
		BookMongo b = bookDAO.getByTitle(bookReq.getTitle());
		
		if(b!=null) {
			throw new BadRequestException(ErrorCode.ALREADY_EXIST, "Book Already Exist with this Title");
		}
		
		if(StringUtils.isEmpty(bookReq.getAuthor())) {
			throw new BadRequestException(ErrorCode.BAD_REQUEST_ERROR, "Author is null");
		}
		
		if(StringUtils.isEmpty(bookReq.getImage())) {
			throw new BadRequestException(ErrorCode.BAD_REQUEST_ERROR, "Image Url is null");
		}
		BookMongo image = bookDAO.getByImageUrl(bookReq.getImage());
		if(image !=null) {
			throw new BadRequestException(ErrorCode.ALREADY_EXIST, "Book Already Exist with this image");
		}
			b.setTitle(bookReq.getTitle());
			b.setAuthor(bookReq.getAuthor());
			b.setImage(bookReq.getImage());
			b.setStatus(BookStatus.NONE);
			bookDAO.create(b);
			return "Successfully Added";
		}
	
	public String updateBook(BookReq bookReq) throws Exception {
		bookReq.verify();
		BookMongo b = bookDAO.getById(bookReq.getId());
		if (b == null) {
			throw new BadRequestException(ErrorCode.BOOK_NOT_FOUND, "No Book Found With this id");
		}
		if(StringUtils.isEmpty(bookReq.getTitle())) {
			throw new BadRequestException(ErrorCode.BAD_REQUEST_ERROR, "Title is Empty");
		}
		BookMongo book = bookDAO.getByTitle(bookReq.getTitle());
		if(book !=null && !book.getId().equals(bookReq.getId())) {
			throw new BadRequestException(ErrorCode.ALREADY_EXIST, "Book Already Exist with this Title");
		}
		if(StringUtils.isEmpty(bookReq.getAuthor())) {
			throw new BadRequestException(ErrorCode.BAD_REQUEST_ERROR, "Author is Empty");
		}
		if(StringUtils.isEmpty(bookReq.getImage())) {
			throw new BadRequestException(ErrorCode.BAD_REQUEST_ERROR, "ImageUrl is Empty");
		}
		BookMongo image = bookDAO.getByImageUrl(bookReq.getImage());
		if(book !=null && !image.getId().equals(bookReq.getId())) {
			throw new BadRequestException(ErrorCode.ALREADY_EXIST, "Book Already Exist with this image");
		}
		b.setTitle(bookReq.getTitle());
		b.setAuthor(bookReq.getAuthor());
		b.setImage(bookReq.getImage());
		b.setStatus(bookReq.getStatus());
		bookDAO.update(b,null);
		return "successfully Updated";
	}
	
	public Collection<BookMongo> getAllBooks() throws Exception {
		Collection<BookMongo> book_info = bookDAO.getAll();
		if(book_info == null || book_info == isEmpty()) {
			return null;
		}
		return book_info;
	}
	

	public String deleteBook(BookReq bookReq) throws Exception {
		bookReq.verify();
		BookMongo b = bookDAO.getById(bookReq.getId());
		if(b==null) {
			throw new BadRequestException(ErrorCode.BOOK_NOT_FOUND, "There is no book with this id");
		}
		bookDAO.delete(b, null);
		return "success";
	}

	@Override
	protected MongoOperations getMongoOperations() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Collection<BookMongo> isEmpty() {
		// TODO Auto-generated method stub
		return null;
	}
}