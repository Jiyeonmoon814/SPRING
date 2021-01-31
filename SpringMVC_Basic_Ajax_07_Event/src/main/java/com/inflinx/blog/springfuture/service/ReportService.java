package com.inflinx.blog.springfuture.service;

import java.util.concurrent.Future;

import com.inflinx.blog.springfuture.domain.Report;

public interface ReportService {
	
	public Future<Report> generateReport();
	
	
	
}
