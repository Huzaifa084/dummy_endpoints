package com.dummy.dummy_endpoints.repository;

import com.dummy.dummy_endpoints.model.CardCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardCategoryRepository extends JpaRepository<CardCategory, Long>, JpaSpecificationExecutor<CardCategory> {

    Optional<CardCategory> findByName(String name);

    Page<CardCategory> findByIsActive(Boolean isActive, Pageable pageable);

    @Query("SELECT cc FROM CardCategory cc LEFT JOIN FETCH cc.cards WHERE cc.id = :id")
    Optional<CardCategory> findByIdWithCards(Long id);

    @Query("SELECT DISTINCT cc FROM CardCategory cc WHERE " +
           "SIZE(cc.cards) > 0 AND cc.isActive = true")
    List<CardCategory> findAllActiveWithCards();
}