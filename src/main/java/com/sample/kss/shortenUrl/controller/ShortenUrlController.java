package com.sample.kss.shortenUrl.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.kss.shortenUrl.service.ShortenUrlService;

/**
 * Shorten URL Controller
 * @author seungshins
 *
 */
@Controller
public class ShortenUrlController {
	@Value("${spring.application.name}")
	String appName;
	
	@Resource(name="shortenUrlService")
	ShortenUrlService shortenUrlService;
	
	/**
	 * index 화면을 리턴하는 API
	 * @return index view
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getMainView() {
		return "index";
	}
	
	/**
	 * Redirect할 ShortenURL을 받아서 처리하는 API
	 * @param shortenUrl
	 * @return redirect하는 url
	 */
	@RequestMapping(value= {"/Y{shortenUrl}", "/K{shortenUrl}", "/G{shortenUrl}", "/F{shortenUrl}", "/U{shortenUrl}"}, method=RequestMethod.GET)
	public String redirectShortenUrl(@PathVariable(value="shortenUrl") String shortenUrl) {
		String originUrl = shortenUrlService.retrieveOriginUrl(shortenUrl);
		if( StringUtils.isEmpty(originUrl) ) return null;
		return "redirect:" + originUrl;
	}
	
	/**
	 * ShortenURL을 등록하는 화면을 불러오는 API
	 * @param model
	 * @return ShortenURL 등록 View
	 */
	@RequestMapping(value="/shortenUrl", method=RequestMethod.GET)
	public String getShortenUrlView(Map<String, Object> model) {
		model.put("appName", appName);
		return "shortenUrl/shortenUrl";
	}
}
