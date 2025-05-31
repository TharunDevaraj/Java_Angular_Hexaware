package com.hexaware.rentalservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.rentalservice.entity.Feedback;
import com.hexaware.rentalservice.repository.FeedbackRepository;

@Service
public class FeedbackServiceImp implements IFeedbackService{

	@Autowired
	FeedbackRepository feedbackRepository;
	
	public Feedback submitFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackByCustomerId(Long customerId) {
        return feedbackRepository.findByCustomerId(customerId);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

}
