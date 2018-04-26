package com.sample.kss.shortenUrl.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.kss.shortenUrl.entity.ShortenUrlEntity;
import com.sample.kss.shortenUrl.service.ShortenUrlService;

/**
 * Shorten URL Rest Controller
 * @author seungshins
 *
 */
@RestController
@RequestMapping("/api/shortenUrl")
public class ShortenUrlRestController {	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShortenUrlRestController.class);
	
	@Resource(name="shortenUrlService")
	ShortenUrlService shortenUrlService;
	
	/**
	 * ShortenURL 생성하는 POST API
	 * @param sue
	 * @return
	 */
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ShortenUrlEntity createShortenUrl(ShortenUrlEntity sue) {
		ShortenUrlEntity shortenUrlData = null;
		LOGGER.debug("createShortenUrl");
		shortenUrlData = shortenUrlService.createShortenUrl(sue);
		return shortenUrlData;
	}
	
	/**
	 * ShortenURL 삭제하는 POST API
	 * @param sue
	 */
	@RequestMapping(value="/deleteShortenUrl", method=RequestMethod.POST)
	public void deleteShortenUrl(ShortenUrlEntity sue) {
		shortenUrlService.deleteShortenUrl(sue);
		return;
	}
	
	/**
	 * ShortenURL List를 조회하는 API
	 * @param sue
	 */
	@RequestMapping(value="/retrieveShortenUrlList", method=RequestMethod.GET)
	public List<ShortenUrlEntity> retrieveShortenUrlList() {
		List<ShortenUrlEntity> shortenUrlList = shortenUrlService.retrieveShortenUrlList();
		return shortenUrlList;
	}
}
