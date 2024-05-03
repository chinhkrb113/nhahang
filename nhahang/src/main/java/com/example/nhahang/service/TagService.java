package com.example.nhahang.service;

import java.util.List;

import com.example.nhahang.entity.Tag;
import com.example.nhahang.model.request.CreateTagRequest;

public interface TagService {
    
    List<Tag> getListTag();

    Tag createTag(CreateTagRequest request);

    Tag updateTag(long id,CreateTagRequest request);

    void enableTag(long id);

    void deleleTag(long id);

}
