package com.dummy.dummy_endpoints.repository;

import com.dummy.dummy_endpoints.model.Card;
import com.dummy.dummy_endpoints.model.enums.CardPriority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>, JpaSpecificationExecutor<Card> {

    // Pagination and filtering methods
    Page<Card> findByIsActive(Boolean isActive, Pageable pageable);

    Page<Card> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Card> findByPriority(CardPriority priority, Pageable pageable);

    Page<Card> findByTitleContainingIgnoreCase(String titleKeyword, Pageable pageable);

    Page<Card> findByDescriptionContainingIgnoreCase(String descriptionKeyword, Pageable pageable);

    @Query("SELECT c FROM Card c WHERE " +
            "(:title IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:description IS NULL OR LOWER(c.description) LIKE LOWER(CONCAT('%', :description, '%'))) AND " +
            "(:isActive IS NULL OR c.isActive = :isActive) AND " +
            "(:priority IS NULL OR c.priority = :priority) AND " +
            "(:categoryId IS NULL OR c.category.id = :categoryId)")
    Page<Card> findWithFilters(
            @Param("title") String title,
            @Param("description") String description,
            @Param("isActive") Boolean isActive,
            @Param("priority") CardPriority priority,
            @Param("categoryId") Long categoryId,
            Pageable pageable);

    @Query("SELECT c FROM Card c WHERE " +
            "c.createdAt BETWEEN :startDate AND :endDate AND " +
            "c.isActive = true")
    List<Card> findActiveCardsBetweenDates(
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate);
}