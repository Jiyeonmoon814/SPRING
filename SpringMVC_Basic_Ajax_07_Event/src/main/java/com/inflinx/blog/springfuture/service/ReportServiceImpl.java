package com.inflinx.blog.springfuture.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.inflinx.blog.springfuture.domain.Report;

@Service("reportsService")
public class ReportServiceImpl implements ReportService {

	@Async
	public Future<Report> generateReport() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Report report = new Report();
		report.setName("New Report");
		report.setDescription("New Report Description");
		report.setNumber(5);
		
		return new AsyncResult<Report>(report);
	}
	
}
