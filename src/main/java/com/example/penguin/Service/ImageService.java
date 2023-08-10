package com.example.penguin.Service;

import com.example.penguin.Entities.ImagesEntity;

import java.util.List;

public interface ImageService {

    List<ImagesEntity> findAll();
    ImagesEntity saveImage(ImagesEntity imagesEntity);

    List<ImagesEntity> findByIdPro(int id);

    void DeleteImage(ImagesEntity imagesEntity);

}
