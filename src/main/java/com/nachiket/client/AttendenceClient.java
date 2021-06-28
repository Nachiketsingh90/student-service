package com.nachiket.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nachiket.vo.Batch;

@FeignClient(name="BATCH-SERVICE")

public interface AttendenceClient {

	@GetMapping("/batch/findBatchByID/{batchID}")
	public Batch findBatchByID(@PathVariable int batchID) ;	


}
