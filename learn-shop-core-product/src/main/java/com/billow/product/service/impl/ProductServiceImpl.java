package com.billow.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.billow.product.dao.ProductDao;
import com.billow.product.dao.ProductImageDao;
import com.billow.product.pojo.po.ProductPo;
import com.billow.product.pojo.vo.ProductImageVo;
import com.billow.product.pojo.vo.ProductVo;
import com.billow.product.service.ProductService;
import com.billow.tools.utlis.ConvertUtils;
import com.billow.tools.utlis.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImageDao productImageDao;

    private final static String COMMON_PATH_MIN = "D:/project/IDEAProjects/learn/learn-shop-ui-admin/src";
    private final static String BASE_PATH = "/static/proudct/min/";

    @Override
    public IPage<ProductPo> findProductList(ProductVo productVo) {
        IPage<ProductPo> page = new Page<>(productVo.getPageNo(), productVo.getPageSize());
        LambdaQueryWrapper<ProductPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(ToolsUtils.isNotEmpty(productVo.getCommodityName()), ProductPo::getCommodityName, productVo.getCommodityName())
                .like(ToolsUtils.isNotEmpty(productVo.getLocalityGrowth()), ProductPo::getLocalityGrowth, productVo.getLocalityGrowth());
        IPage<ProductPo> productPos = productDao.selectPage(page, queryWrapper);
        return productPos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveProduct(ProductVo productVo) throws Exception {
        productVo.setDeleFlag("1");
        ProductPo convert = ConvertUtils.convert(productVo, ProductPo.class);
        productDao.insert(convert);
        ConvertUtils.convert(convert, productVo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateProduct(ProductVo productVo) throws Exception {
        ProductPo convert = ConvertUtils.convert(productVo, ProductPo.class);
        productDao.updateById(convert);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ProductVo deleteProductById(String id) throws Exception {
        // 逻辑删除商品信息
        productDao.deleteById(id);
//        // 删除商品图片
//        List<ProductImagePo> productImagePos = productImageDao.findByProductIdAndValidInd(id, true);
//        if (ToolsUtils.isNotEmpty(productImagePos)) {
//            productImagePos.stream().forEach(item -> {
//                item.setValidInd(false);
//            });
//            productImageDao.save(productImagePos);
//        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void uploadProductImage(ProductImageVo productImageVo) throws Exception {
        // 保存图片信息
//        productImageVo.setValidInd(true);
//        productImageVo.setId(SequenceUtil.createSequence());
//        String newFileName = SequenceUtil.createSequenceBySuffix(".jpg");
//        productImageVo.setNewImageName(newFileName);
//        ProductImagePo productImagePo = ConvertUtils.convert(productImageVo, ProductImagePo.class);
//        productImageDao.save(productImagePo);
//
//        // 保存图片
//        String filePath = COMMON_PATH_MIN + BASE_PATH + newFileName;
//        FileUtil.writeFromStream(productImageVo.getInputStream(), filePath);
    }

    @Override
    public List<ProductImageVo> findProductImageByProductId(String productId, ProductImageVo productImageVo) throws Exception {
//        ProductImagePo productImagePo = ConvertUtils.convert(productImageVo, ProductImagePo.class);
//        productImagePo.setProductId(productId);
//        productImagePo.setValidInd(true);
//        DefaultSpec<ProductImagePo> defaultSpec = new DefaultSpec<>(productImagePo);
//        List<ProductImagePo> productImagePos = productImageDao.findAll(defaultSpec, new Sort(Sort.Direction.ASC, "id"));
//
//        List<ProductImageVo> convert = ConvertUtils.convert(productImagePos, ProductImageVo.class);
//        if (ToolsUtils.isNotEmpty(convert)) {
//            convert.stream().forEach(item -> {
//                item.setImagePath(BASE_PATH + item.getNewImageName());
//            });
//        }
//        return convert;
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteProductImageById(String id) throws Exception {
//        ProductImagePo productImagePo = productImageDao.findOne(id);
//        if (productImagePo != null) {
//            productImagePo.setValidInd(false);
//            productImageDao.save(productImagePo);
//        }
    }
}