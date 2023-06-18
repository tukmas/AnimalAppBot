package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.reports.CatReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatReportRepository extends JpaRepository<CatReport, Long> {
}
