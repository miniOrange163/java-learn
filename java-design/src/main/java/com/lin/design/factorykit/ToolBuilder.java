package com.lin.design.factorykit;

import java.util.function.Supplier;

/**
 * @author: 林嘉宝
 * @Date: 2018/5/9
 * @name:
 * @Description:
 */
public interface ToolBuilder {

    void add(ToolType type, Supplier<Tool> supplier);
}
