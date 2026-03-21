package com.example.LegalJudgement.service;

import com.example.LegalJudgement.exception.ResourceNotFoundException;
import com.example.LegalJudgement.model.Law;
import com.example.LegalJudgement.repository.LawRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LawService {

    private final LawRepository lawRepository;

    public LawService(LawRepository lawRepository) {
        this.lawRepository = lawRepository;
    }

    public Page<Law> searchCrime(String keyword, int page, int size, String sortBy, String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase("desc")?
                    Sort.by(Sort.Order.desc(sortBy).ignoreCase()) :
                    Sort.by(Sort.Order.asc(sortBy).ignoreCase());

        Pageable pageable = PageRequest.of(page, size, sort);

        return lawRepository
                .findByCrimeContainingIgnoreCaseOrSectionContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        keyword, keyword, keyword, pageable);
    }
}
