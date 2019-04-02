package com.vedantu.models;

import com.vedantu.enums.BookStatus;
import com.vedantu.utils.AbstractMongoEntityBean;





public class BookMongo extends AbstractMongoStringIdEntity {
	
	private String title;
	private String author;
	private String image;
	private BookStatus status;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public BookStatus getStatus() {
		return status;
	}
	public void setStatus(BookStatus status) {
		this.status = status;
	}
    public static class Constants extends AbstractMongoEntityBean.Constants {

        public static final String TITLE = "title";
        public static final String AUTHOR = "author";
        public static final String IMAGE = "image";
    }
}
