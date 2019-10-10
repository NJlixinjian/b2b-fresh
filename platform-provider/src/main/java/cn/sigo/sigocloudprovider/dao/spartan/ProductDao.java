package cn.sigo.sigocloudprovider.dao.spartan;

import cn.sigo.sigocloudprovider.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: lxj
 * @Date: 2019/10/9 18:06
 * @Description:
 */
public interface ProductDao {

    List<Product> queryProductInfo(@Param("companyId") Long companyId);
}
