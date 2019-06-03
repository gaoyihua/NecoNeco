package com.gary.neconeco.dao;

import com.gary.neconeco.pojo.Video;

public interface VideoDao {
    void save(Video video);
    void find(int videoId);
    void findAll();
    void delete(int videoId);
}
