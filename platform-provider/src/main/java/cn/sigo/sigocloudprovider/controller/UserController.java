package cn.sigo.sigocloudprovider.controller;

import cn.sigo.sigocloudprovider.model.Product;
import cn.sigo.sigocloudprovider.model.vo.CompanyInfoVo;
import cn.sigo.sigocloudprovider.service.CompanyService;
import cn.sigo.sigocloudprovider.service.spartan.ProductService;
import cn.sigo.sigocloudprovider.util.HttpHelper;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户服务，与前端交互的服务
 * fegin 实现的spring mvc已经支持RequestMapping，这里应该是接口定义外的服务
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "hello";
    }

    @Value("${url.soldOutProduct}")
    private String soldOutProduct;

    /**
     * 开启/关闭账号
     *
     */
    @RequestMapping(value = "/account/controlAccount", method = RequestMethod.POST)
    public void controlAccount(@RequestBody CompanyInfoVo companyInfoVo){
        //更新账号//开启/关闭账号
        companyService.updateAccount(companyInfoVo);
        //查询商品id,code
        List<Product> productList = productService.queryProductInfo(companyInfoVo.getCompanyId());
        for (Product product : productList) {
            log.info(product.getProductCode());
        }
        String requestStr = HttpHelper.doPost(soldOutProduct, JSON.toJSONString(productList));
        log.info(requestStr);
    }
}
