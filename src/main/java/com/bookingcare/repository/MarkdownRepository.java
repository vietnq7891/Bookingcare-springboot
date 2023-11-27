package com.bookingcare.repository;

import com.bookingcare.model.entity.Markdown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarkdownRepository extends JpaRepository<Markdown, Integer> {
    Optional<Markdown> findByDoctorId(Integer doctorId);
}
