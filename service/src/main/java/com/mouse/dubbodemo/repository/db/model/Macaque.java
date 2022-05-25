package com.mouse.dubbodemo.repository.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("macaque")
public class Macaque {

  @TableId(type = IdType.AUTO)
  private long id;
  private String name;
  private String memo;

}
