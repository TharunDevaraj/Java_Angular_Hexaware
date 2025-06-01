package com.hexaware.rentalservice.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.rentalservice.dto.FeedbackDTO;
import com.hexaware.rentalservice.entity.Feedback;
import com.hexaware.rentalservice.exception.FeedbackNotFoundException;
import com.hexaware.rentalservice.service.FeedbackServiceImp;

@RestController
@RequestMapping("api/feedback/")
public class FeedbackRestController {

	@Autowired
	FeedbackServiceImp feedbackService;
	
	@PostMapping("/submit")
	@PreAuthorize("hasRole('user','customer')")
	public Feedback submitFeedback(@RequestBody FeedbackDTO feedbackDTO) {
	    return feedbackService.submitFeedback(feedbackDTO);
	}
	
	@GetMapping("/get")
	@PreAuthorize("hasRole('admin')")
	public List<Feedback> getAll() {
	    return feedbackService.getAllFeedback();
	}
	
	@DeleteMapping("/remove/{feedbackId}")
	@PreAuthorize("hasRole('admin')")
	public String removeFeedback(@PathVariable Long feedbackId) throws FeedbackNotFoundException
	{
		Feedback feedback=feedbackService.getFeedbackById(feedbackId);
		if(feedback==null)
		{
			throw new FeedbackNotFoundException();
		}
		feedbackService.deleteFeedback(feedbackId);
		return "Feedback deleted!";
	}
	
	@GetMapping("/get/{feedbackId}")
	@PreAuthorize("hasRole('admin')")
	public Feedback getByFeedbackId(@PathVariable Long feedbackId) throws FeedbackNotFoundException {
		Feedback feedback=feedbackService.getFeedbackById(feedbackId);
		if(feedback==null)
		{
			throw new FeedbackNotFoundException();
		}
		return feedback;
	}
	
	@GetMapping("/getbycustomerid/{customerId}")
	@PreAuthorize("hasRole('admin')")
	public List<Feedback> getFeedbackByCustomerId(@PathVariable Long customerId) {
	    return feedbackService.getFeedbackByCustomerId(customerId);
	}
	
	@PutMapping("/setstatus/{id}")
	@PreAuthorize("hasRole('admin')")
	public Feedback setFeedbackStatus(@PathVariable Long id) throws FeedbackNotFoundException
	{
		return feedbackService.setFeedbackStatus(id);
	}
	
}
