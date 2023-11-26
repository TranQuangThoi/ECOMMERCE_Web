package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.ImagesEntity;
import com.example.penguin.Entities.ProductEntity;
import com.example.penguin.Repository.ImageReposity;
import com.example.penguin.Repository.ProductReposity;
import com.example.penguin.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductReposity productReposity;
    @Autowired
    ImageReposity imageReposity;

    @Override
    public List<ProductEntity> findAll()
    {
        return productReposity.findAll();
    }

    @Override
    public void saveProduct(ProductEntity product)
    {
        productReposity.save(product);
    }

    @Override
    public ProductEntity findById(int id)
    {
        return productReposity.findById(id);
    }

    @Override
    public Page<ProductEntity> findPage(int pageNumber , int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber -1,pageSize);
        return this.productReposity.findAll(pageable);
    }

    @Override
    public void delete(ProductEntity product)
    {
        productReposity.delete(product);
    }


    // trả về danh sách sản phẩm theo category
    @Override
    public List<ProductEntity> findProByCategoryId(int id)
    {

        return (List<ProductEntity>)productReposity.findByCate(id);
    }

    @Override
    public void deleteImageByPro(int id)
    {
        List<ImagesEntity> imageList = imageReposity.findByPro(id);
        for(ImagesEntity itemImage : imageList)
        {
            imageReposity.delete(itemImage);
        }
    }
    @Override
    public void deleteProById(int id)
    {
        List<ImagesEntity> imageList = imageReposity.findByPro(id);
        for(ImagesEntity itemImage : imageList)
        {
            imageReposity.delete(itemImage);
        }
        productReposity.deleteById(id);
    }

    @Override
    public List<ProductEntity> findRelateProduce(int id, Pageable pageable) {
        List<ProductEntity> productEntities = productReposity.findProdcutRelateCate(id,pageable);
        return productEntities;
    }


}
