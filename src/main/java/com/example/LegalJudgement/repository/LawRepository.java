package com.example.LegalJudgement.repository;

import com.example.LegalJudgement.model.Law;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface LawRepository extends JpaRepository<Law, Long> {
   // List<Law> findByCrimeContainingIgnoreCase(String keyword);

    //Enhanced multi-field search
    Page<Law> findByCrimeContainingIgnoreCaseOrSectionContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String crime, String section, String description, Pageable pageable);
}
