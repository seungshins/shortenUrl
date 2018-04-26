package com.sample.kss.shortenUrl.service;

import java.util.List;

import com.sample.kss.shortenUrl.entity.ShortenUrlEntity;

/**
 * ShortenURL 처리 Service
 * @author seungshins
 *
 */
public interface ShortenUrlService {
	/**
	 * Shorten URL을 생성하는 메서드
	 * @param sue
	 * @return 생성된 ShortenUrlEntity
	 */
	public abstract ShortenUrlEntity createShortenUrl(ShortenUrlEntity sue);
	
	/**
	 * Shorten URL을 삭제하는 메서드
	 * @param sue
	 */
	public abstract void deleteShortenUrl(ShortenUrlEntity sue);
	
	/**
	 * Shorten URL 리스트를 조회하는 메서드
	 * @return
	 */
	public abstract List<ShortenUrlEntity> retrieveShortenUrlList();
	
	/**
	 * Shorten URL로 Origin URL을 조회하는 메서드
	 * @param shortenUrl
	 * @return
	 */
	public abstract String retrieveOriginUrl(String shortenUrl);
}
