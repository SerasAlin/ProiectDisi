package com.ps.backend.repository;

import com.ps.backend.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug,Long> {
}
