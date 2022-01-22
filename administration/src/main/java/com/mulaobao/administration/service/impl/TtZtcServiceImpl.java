package com.mulaobao.administration.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mulaobao.administration.dao.TtZtcDao;
import com.mulaobao.administration.entity.DyXssj;
import com.mulaobao.administration.entity.LsShop;
import com.mulaobao.administration.entity.TmZtc;
import com.mulaobao.administration.entity.TtZtc;
import com.mulaobao.administration.listener.DyXssjListener;
import com.mulaobao.administration.listener.TmTktgListener;
import com.mulaobao.administration.listener.TtZtcListener;
import com.mulaobao.administration.service.DyXssjService;
import com.mulaobao.administration.service.TtZtcService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * (TtZtc)表服务实现类
 *
 * @author makejava
 * @since 2021-09-16 17:44:17
 */
@Service("ttZtcService")
public class TtZtcServiceImpl extends ServiceImpl<TtZtcDao, TtZtc> implements TtZtcService {
    @Resource
    private TtZtcService ttZtcService;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void getInformation(MultipartFile files, String ms, LsShop shop_name,Date data1) {
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("platformBh",shop_name.getPlatformBh());
        stringObjectHashMap.put("ShopBh",shop_name.getShopBh());
        stringObjectHashMap.put("DateTime",data1);
        try {
            EasyExcel.read(files.getInputStream(), TtZtc.class,new TtZtcListener(ttZtcService,stringObjectHashMap)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> getNum(String shopBh, Date data1) {
        QueryWrapper<TtZtc> ew = new QueryWrapper<>();
        ew.eq("Shop_bh", shopBh );
        ew.eq("Date_time",data1);
        ew.select("SUM(Spend) as MoreSearchfy ,SUM(Hits_num) as MoreSearchll");
        Map<String, Object> map = ttZtcService.getMap(ew);

        return map;
    }

    @Override
    public void getInformation1(String file, String ms, LsShop shop_name, Date date2) {
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("platformBh",shop_name.getPlatformBh());
        stringObjectHashMap.put("ShopBh",shop_name.getShopBh());
        stringObjectHashMap.put("DateTime",date2);
            EasyExcel.read(new File(file), TtZtc.class,new TtZtcListener(ttZtcService,stringObjectHashMap)).sheet().doRead();
    }
}

