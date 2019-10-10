package cn.sigo.sigocloudprovider.service.spartan;

import cn.sigo.sigocloudprovider.model.Product;

import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/10/9 15:55
 * @Description:
 */
public interface ProductService {

    /**
     * 查询商品信息
     *
     * @param companyId
     * @return
     */
    List<Product> queryProductInfo(Long companyId);
}
