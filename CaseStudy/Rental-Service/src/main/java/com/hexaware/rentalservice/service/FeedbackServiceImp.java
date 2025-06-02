package com.hexaware.rentalservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.rentalservice.dto.FeedbackDTO;
import com.hexaware.rentalservice.entity.Feedback;
import com.hexaware.rentalservice.exception.FeedbackNotFoundException;
import com.hexaware.rentalservice.repository.FeedbackRepository;

/**
 * Date: 02-06-2025
 * Author: Tharun D
 * Handles all business logic related to feedback such as 
 * submitting feedback , resolving feedback etc.
 */

@Service
public class FeedbackServiceImp implements IFeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;

	public Feedback submitFeedback(FeedbackDTO feedbackDTO) {

		Feedback feedback = new Feedback();
		feedback.setReservationId(feedbackDTO.getReservationId());
		feedback.setCustomerId(feedbackDTO.getCustomerId());
		feedback.setComment(feedbackDTO.getComment());
		feedback.setRating(feedbackDTO.getRating());
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

	public Feedback getFeedbackById(Long id) throws FeedbackNotFoundException {
		Feedback feedback= feedbackRepository.findById(id).orElse(null);
		
		if(feedback==null)
		{
			throw new FeedbackNotFoundException();
		}
		return feedback;
	}

	public Feedback setFeedbackStatus(Long id) throws FeedbackNotFoundException {
		Feedback feedback = getFeedbackById(id);
		if (feedback == null) {
			throw new FeedbackNotFoundException();
		}
		feedback.setStatus("Resolved");
		return feedbackRepository.save(feedback);
	}

}
