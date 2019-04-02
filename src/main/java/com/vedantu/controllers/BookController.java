package com.vedantu.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vedantu.exception.VException;
import com.vedantu.managers.BooksManager;
import com.vedantu.models.BookMongo;
import com.vedantu.requests.BookReq;


@RestController
@RequestMapping("test")
public class BookController {

	
	@Autowired
	private BooksManager bookManager;
	

//customer APIS	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addBook(@Valid @RequestBody BookReq bookreq) throws Exception,VException {
		return bookManager.addBook(bookreq);
	}

	// update
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateBook(@Valid @RequestBody BookReq bookreq) throws Exception,VException  {
		return bookManager.updateBook(bookreq); 
}


	// get
	@RequestMapping(value = "/getBooks", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<BookMongo> getParam() throws Exception {
		return bookManager.getAllBooks();
	}
	

	// delete
	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteBook(@RequestBody BookReq bookReq) throws Exception,VException  {
		return bookManager.deleteBook(bookReq);
	}
}
