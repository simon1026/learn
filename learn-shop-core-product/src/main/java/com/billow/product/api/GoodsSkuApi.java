package com.billow.product.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.billow.product.pojo.po.GoodsSkuPo;
import com.billow.product.pojo.vo.GoodsSkuVo;
import com.billow.product.service.GoodsSkuService;
import com.billow.tools.utlis.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * sku表 前端控制器
 * </p>
 *
 * @author billow
 * @since 2019-11-27
 * @version v1.0
 */
@Api(tags = {"GoodsSkuApi"},value = "sku表")
@RestController
@RequestMapping("/goodsSkuApi")
public class GoodsSkuApi {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GoodsSkuService goodsSkuService;

    @ApiOperation(value = "查询分页sku表数据")
    @PostMapping(value = "/list")
    public IPage<GoodsSkuPo> findListByPage(@RequestBody GoodsSkuVo goodsSkuVo){
        return goodsSkuService.findListByPage(goodsSkuVo);
    }

    @ApiOperation(value = "根据id查询sku表数据")
    @GetMapping(value = "/getById/{id}")
    public GoodsSkuVo getById(@PathVariable("id") String id){
        GoodsSkuPo po = goodsSkuService.getById(id);
        return ConvertUtils.convert(po, GoodsSkuVo.class);
    }

    @ApiOperation(value = "新增sku表数据")
    @PostMapping(value = "/add")
    public GoodsSkuVo add(@RequestBody GoodsSkuVo goodsSkuVo){
        GoodsSkuPo po = ConvertUtils.convert(goodsSkuVo, GoodsSkuPo.class);
        goodsSkuService.save(po);
        return ConvertUtils.convert(po, GoodsSkuVo.class);
    }

    @ApiOperation(value = "删除sku表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return goodsSkuService.removeById(id);
    }

    @ApiOperation(value = "更新sku表数据")
    @PutMapping(value = "/update")
    public GoodsSkuVo update(@RequestBody GoodsSkuVo goodsSkuVo){
        GoodsSkuPo po = ConvertUtils.convert(goodsSkuVo, GoodsSkuPo.class);
        goodsSkuService.updateById(po);
        return ConvertUtils.convert(po, GoodsSkuVo.class);
    }

    @ApiOperation("根据ID禁用sku表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return goodsSkuService.prohibitById(id);
    }
//
//    @ApiOperation(value = "通过 spuId 获取商品 sku 信息")
//    @GetMapping(value = "/findGoodsSku/{spuId}")
//    public List<Map<String,Object>> findGoodsSku(@PathVariable String spuId){
//        return goodsSkuService.findGoodsSku(spuId);
//    }
}