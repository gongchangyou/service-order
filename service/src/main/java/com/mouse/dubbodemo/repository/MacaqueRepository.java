package com.mouse.dubbodemo.repository;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.mouse.dubbodemo.repository.db.model.Macaque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2021/11/17 8:37 下午
 */
@Component
public class MacaqueRepository {
    @Autowired
    com.mouse.dubbodemo.repository.db.mapper.MacaqueMapper macaqueMapper;

    public int insertMacaque(Macaque macaque) {
        return macaqueMapper.insert(macaque);
    }

    public int updateMacaque(Macaque macaque) {
        return macaqueMapper.updateById(macaque);
    }

    public Macaque getMacaqueById(Long id) {
        return macaqueMapper.selectById(id);
    }

    public Macaque getMacaqueByName(String name) {
        return new LambdaQueryChainWrapper<>(macaqueMapper)
                .eq(Macaque::getName, name)
                .one();
    }


}
