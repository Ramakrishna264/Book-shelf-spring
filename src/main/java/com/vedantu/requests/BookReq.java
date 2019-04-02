package com.vedantu.requests;



import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vedantu.enums.BookStatus;
import com.vedantu.models.BookMongo;
import com.vedantu.util.fos.request.ReqLimits;
import com.vedantu.util.fos.request.ReqLimitsErMsgs;



public class BookReq extends AbstractReq{
	private String id;
	@NotNull(message = ReqLimitsErMsgs.RQD)
    @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErMsgs.MAX_NAME_TYPE)
	private  String title;
	
	@NotNull(message = ReqLimitsErMsgs.RQD)
    @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErMsgs.MAX_NAME_TYPE)
	private String author;
	
	@NotNull(message = ReqLimitsErMsgs.RQD)
	private String image;
	
	@NotNull(message = ReqLimitsErMsgs.RQD)
	private BookStatus status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
	 @Override
	    protected List<String> collectVerificationErrors() {

	        List<String> errors = super.collectVerificationErrors();
	        if (null == title) {
	            errors.add(BookMongo.Constants.TITLE);
	        }
	        if(null == author) {
	        	errors.add(BookMongo.Constants.AUTHOR);
	        }
	        if(null == image) {
	        	errors.add(BookMongo.Constants.IMAGE);
	        }
	        return errors;
	    }

}
