package com.sample.kss.shortenUrl.service.impl;

import java.util.List;

import org.bitcoinj.core.Base58;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sample.kss.common.util.CommonUtil;
import com.sample.kss.shortenUrl.dao.ShortenUrlDao;
import com.sample.kss.shortenUrl.entity.ShortenUrlEntity;
import com.sample.kss.shortenUrl.service.ShortenUrlService;

@Service("shortenUrlService")
public class ShortenUrlServiceImpl implements  ShortenUrlService{
	@Autowired
	ShortenUrlDao shortenUrlDao;
	
	@Value("${kss.server.port}")
	String serverPort;
	
	@Value("${kss.server.name}")
	String serverName;
	
	@Override
	public ShortenUrlEntity createShortenUrl(ShortenUrlEntity sue) {
		ShortenUrlEntity shortenUrlData = null;
		String originUrl = sue.getOriginUrl();
		
		if( StringUtils.isEmpty(originUrl) ) {
			return null;
		}
		
		// TODO : Validate Origin URL
		originUrl = originUrl.trim();
		
		int count = shortenUrlDao.countByOriginUrl(originUrl);
		
		// 중복된 Origin의 경우에는 기존 Data를 리턴
		if( count > 0 ) {
			shortenUrlData = shortenUrlDao.findByOriginUrl(originUrl);
			return shortenUrlData;
		}
		
		// make number string and encode origin url by base58
		int currSeq = shortenUrlDao.getCurrentSeriesId();
		String targetSeq = String.format("%5d", currSeq + 1).replace(' ', CommonUtil.getRandomFlagChar());
		String encodedUrl = new String(Base58.encode(String.valueOf(targetSeq).getBytes()));
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(serverName);
		if( !StringUtils.isEmpty(serverPort) ) {
			sb.append(":").append(serverPort);
		}
		sb.append("/").append(CommonUtil.getRandomFlagString()).append(encodedUrl);
		sue.setShortenUrl(sb.toString());
		
		// repository save
		shortenUrlData =  shortenUrlDao.save(sue);		
		return shortenUrlData;
	}

	@Override
	public void deleteShortenUrl(ShortenUrlEntity sue) {
		// repository delete
		shortenUrlDao.delete(sue);
		return;
	}

	@Override
	public List<ShortenUrlEntity> retrieveShortenUrlList() {
		// repository find
		List<ShortenUrlEntity> shortenUrlList = shortenUrlDao.findAll();
		return shortenUrlList;
	}

	@Override
	public String retrieveOriginUrl(String shortenUrl) {
		String originUrl = null;
		// check shortenUrl is null
		if( StringUtils.isEmpty(shortenUrl) ) return null;
		
		// Validate ShortenURL Using Decode Base58
		byte[] indexBytes = Base58.decode(shortenUrl);
		
		// check nullable
		if( indexBytes == null || indexBytes.length <= 0 ) return null;
		
		// convert to string
		String indexString = new String(indexBytes).replaceFirst("^(Y|K|G|F|U)+(?!$)", "");
		
		// get Url
		Long index = Long.valueOf(indexString);
		ShortenUrlEntity sue = shortenUrlDao.findByIndex(index);
		if ( sue != null ) {
			originUrl = sue.getOriginUrl();
		}
		
		return originUrl;
	}
}
