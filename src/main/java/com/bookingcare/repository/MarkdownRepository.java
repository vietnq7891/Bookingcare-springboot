package com.bookingcare.repository;

import com.bookingcare.model.entity.Markdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MarkdownRepository extends JpaRepository<Markdown, Integer> {
    Optional<Markdown> findByDoctorId(Integer doctorId);
}
