package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.reports.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
