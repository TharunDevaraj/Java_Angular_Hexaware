package com.hexaware.rentalservice.service;

import java.util.List;

import com.hexaware.rentalservice.entity.Feedback;

public interface IFeedbackService {
	
	public Feedback submitFeedback(Feedback feedback);
	
	public Feedback getFeedbackById(Long id);
	
    public List<Feedback> getAllFeedback();
    
    public List<Feedback> getFeedbackByCustomerId(Long customerId);
    
    public void deleteFeedback(Long feedbackId);
}
