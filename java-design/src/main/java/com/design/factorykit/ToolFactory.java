package com.design.factorykit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author: 林嘉宝
 * @Date: 2018/5/9
 * @name:
 * @Description:
 */
public interface ToolFactory {

    Tool create(ToolType type);

    static ToolFactory factory(Consumer<ToolBuilder> consumer){

        Map<ToolType, Supplier<Tool>> map = new HashMap<>();
        //三种形式实现
        consumer.accept(map::put);
        /*
        consumer.accept((type, supplier) -> map.put(type,supplier));
        */
        /*
        consumer.accept(new ToolBuilder() {
            @Override
            public void add(ToolType type, Supplier<Tool> supplier) {
                map.put(type, supplier);
            }
        });
        */

        return factory->map.get(factory).get();
    }
}
