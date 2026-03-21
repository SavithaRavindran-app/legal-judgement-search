package com.example.LegalJudgement.service;

import com.example.LegalJudgement.model.Law;
import com.example.LegalJudgement.repository.LawRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LawService {

    private final LawRepository lawRepository;

    public LawService(LawRepository lawRepository) {
        this.lawRepository = lawRepository;
    }

    public List<Law> searchCrime(String keyword) {
        return lawRepository.findByCrimeContainingIgnoreCaseOrSectionContainingIgnoreCaseOrDescriptionContainingIgnoreCase
                (keyword,keyword,keyword);

    }
}
