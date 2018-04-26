package com.sample.kss.shortenUrl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sample.kss.shortenUrl.entity.ShortenUrlEntity;

/**
 * H2 DB를 접근하는 JPA Repository
 * @author seungshins
 *
 */
@Repository
public interface ShortenUrlDao extends JpaRepository <ShortenUrlEntity, Long> {
	@Query(value = "SELECT SHORTEN_URL_INDEX_SEQ.CURRVAL FROM dual", nativeQuery = true)
	 int getCurrentSeriesId();
	
	//Find by index
	ShortenUrlEntity findByIndex(Long index);
	
	//Find by originUrl
	ShortenUrlEntity findByOriginUrl(String originUrl);
	
	//Count Record by originUrl
	int countByOriginUrl(String originUrl);
}