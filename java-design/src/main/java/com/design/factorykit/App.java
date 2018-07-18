package com.design.factorykit;
/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/5/9
 *  
 * @name: 工厂套件
 *
 * @Description: 1.ToolFactory是一个工厂接口，factory方法定义了生产类实例的逻辑。
 */
public class App {


    public static void main(String[] args) {

        ToolFactory factory = ToolFactory.factory(builder -> {
            builder.add(ToolType.Hammer,Hammer::new);
            builder.add(ToolType.Wrench,Wrench::new);
            builder.add(ToolType.Scissors,()->new Scissors());

        });

        Tool hammer = factory.create(ToolType.Hammer);
        Tool wrench = factory.create(ToolType.Wrench);
        Tool scissors = factory.create(ToolType.Scissors);
        Tool scissors2 = factory.create(ToolType.Scissors);

        hammer.show();
        wrench.show();
        scissors.show();

        System.out.println(scissors.equals(scissors2));

    }
}
