package com.iu.JaWa.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iu.JaWa.entity.ArticleStock;

public interface ArticleStockRepository extends JpaRepository<ArticleStock, Integer>{

	Optional<ArticleStock> findByArticleIdAndMhd(int articleId, LocalDate mhd);

}
