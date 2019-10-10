package cn.sigo.sigocloudprovider.service.spartan.impl;

import cn.sigo.sigocloudprovider.dao.spartan.ProductDao;
import cn.sigo.sigocloudprovider.model.Product;
import cn.sigo.sigocloudprovider.service.spartan.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/10/9 15:56
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;


    @Override
    public List<Product> queryProductInfo(Long companyId) {
        return productDao.queryProductInfo(companyId);
    }
}
