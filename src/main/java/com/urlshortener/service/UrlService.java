package com.urlshortener.service;

import com.urlshortener.entity.Url;
import com.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public void saveUrl(List<Url> urls) {
       urlRepository.saveAllAndFlush(urls);
    }

    public List<Url> findAll() {
        return urlRepository.findAll();
    }
}
