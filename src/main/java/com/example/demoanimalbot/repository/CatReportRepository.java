package com.example.demoanimalbot.repository;

import com.example.demoanimalbot.model.reports.CatReport;
import com.example.demoanimalbot.model.reports.DogReport;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CatReportRepository extends JpaRepository<CatReport, Long> {


}
