package com.example.LegalJudgement.controller;

import com.example.LegalJudgement.model.ApiResponse;
import com.example.LegalJudgement.model.CrimeResult;
import com.example.LegalJudgement.model.Law;
import com.example.LegalJudgement.repository.LawRepository;
import com.example.LegalJudgement.service.LawService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final LawService lawService;

    public SearchController(LawService lawService) {
        this.lawService = lawService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<Law>> search(@RequestParam String keyword) {
        List<Law> results = lawService.searchCrime(keyword);
        if (results.isEmpty()) {
            ApiResponse<Law> response = new ApiResponse<>
                    (HttpStatus.NOT_FOUND.value(),
                    "No results found for: " + keyword,
                    Collections.emptyList()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else {
            ApiResponse<Law> response = new ApiResponse<>
                    (HttpStatus.OK.value(),
                            "Results found for: " + keyword,
                            results
                    );
            return ResponseEntity.ok(response);
        }
    }
}
