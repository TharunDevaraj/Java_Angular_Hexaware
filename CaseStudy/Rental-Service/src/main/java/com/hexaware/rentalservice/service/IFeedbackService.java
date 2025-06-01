package com.hexaware.rentalservice.service;

import java.util.List;

import com.hexaware.rentalservice.dto.FeedbackDTO;
import com.hexaware.rentalservice.entity.Feedback;
import com.hexaware.rentalservice.exception.FeedbackNotFoundException;

public interface IFeedbackService {
	
	public Feedback submitFeedback(FeedbackDTO feedbackDTO);
	
	public Feedback getFeedbackById(Long id);
	
    public List<Feedback> getAllFeedback();
    
    public List<Feedback> getFeedbackByCustomerId(Long customerId);
    
    public void deleteFeedback(Long feedbackId);
    
    public Feedback setFeedbackStatus(Long id) throws FeedbackNotFoundException;
}
