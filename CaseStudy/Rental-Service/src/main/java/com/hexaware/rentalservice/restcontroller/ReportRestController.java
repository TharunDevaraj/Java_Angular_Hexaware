package com.hexaware.rentalservice.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.rentalservice.service.IReportService;

/**
 * Date: 02-06-2025
 * Author: Tharun D
 * REST controller for managing Admin operations such as
 * generating total revenue, count total reservations.
 */

@RestController
@RequestMapping("/api/report")
public class ReportRestController {

	@Autowired
    private IReportService reportService;

    @GetMapping("/total-revenue")
    @PreAuthorize("hasRole('admin')")
    public double getTotalRevenue() {
        return reportService.getTotalRevenue();
    }
    
    @GetMapping("/total-reservations")
    @PreAuthorize("hasRole('admin')")
    public double getTotalReservations() {
        return reportService.getTotalReservations();
    }
}
