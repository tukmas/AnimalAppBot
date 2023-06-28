package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.reports.CatReport;
import com.example.demoanimalbot.model.reports.DogReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogReportRepository extends JpaRepository<DogReport, Long> {
    DogReport findFirstByDogIdOrderBySendDateDesc(long dogId);
}
