package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.ImagesEntity;
import com.example.penguin.Repository.ImageReposity;
import com.example.penguin.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {


    @Autowired
    ImageReposity imageReposity;

    @Override
   public   List<ImagesEntity> findAll()
    {
        return imageReposity.findAll();
    }
    @Override
    public ImagesEntity saveImage(ImagesEntity imagesEntity)
    {

       return imageReposity.save(imagesEntity);
    }

    @Override
    public List<ImagesEntity> findByIdPro(int id)
  {
      return imageReposity.findByPro(id);
  }

    @Override
    public void DeleteImage(ImagesEntity imagesEntity) {
        imageReposity.delete(imagesEntity);
    }


}
