package com.example.LegalJudgement.controller;

import com.example.LegalJudgement.model.ApiResponse;
import com.example.LegalJudgement.model.CrimeResult;
import com.example.LegalJudgement.model.Law;
import com.example.LegalJudgement.repository.LawRepository;
import com.example.LegalJudgement.service.LawService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/search")
public class SearchController {

    private final LawService lawService;

    public SearchController(LawService lawService) {
        this.lawService = lawService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<Law>> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "crime") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Page<Law> resultPage = lawService.searchCrime(keyword, page, size, sortBy, sortOrder);

        if (resultPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            404,
                            "No results found",
                            Collections.emptyList(),
                            page,
                            size,
                            0,
                            0
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Success",
                        resultPage.getContent(),
                        resultPage.getNumber(),
                        resultPage.getSize(),
                        resultPage.getTotalElements(),
                        resultPage.getTotalPages()
                )
        );
    }
}
