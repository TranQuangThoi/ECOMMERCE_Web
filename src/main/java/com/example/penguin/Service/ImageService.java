package com.example.penguin.Service;

import com.example.penguin.Entities.ImagesEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Repository.ImageReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {


    @Autowired
    ImageReposity imageReposity;

   public   List<ImagesEntity> findAll()
    {
        return imageReposity.findAll();
    }
    public ImagesEntity saveImage(ImagesEntity imagesEntity)
    {

       return imageReposity.save(imagesEntity);
    }

  public List<ImagesEntity> findByIdPro(int id)
  {
      return imageReposity.findByPro(id);
  }

  public void DeleteImage(ImagesEntity imagesEntity)
  {
      imageReposity.delete(imagesEntity);
  }
}
