package com.hulb.java.jvm.zlass;

/**
 * @author hulb
 * @date 2018/1/25 下午2:36
 *
 * 1、类装载 2、链接 3、初始化 4、实例化；
 * 而初始化阶段做的事情是初始化静态变量和执行静态方法等的工作，而且永远只执行一次。
 */
public class Test {

    static {
        Name mName;
        System.out.println("Test Class loaded");
    }

    public  static void main(String[] args) {
        System.out.println("entern Test main()");

        // Name.class
        Class mClassPointClass;
        // Class.forName("完整包名+类名")
        Class mClassForName;
        // new 对象后，对象.getClass()
        Class mClassObjectPointClass1;
        Class mClassObjectPointClass2;

        try {

            /**
             * 使用 类名.class获得Class实例时，并不会触发类的初始化
             */
            //测试 类名.class
            mClassPointClass = Name.class;
            System.out.println("mClassPointClass = " + mClassPointClass);

            /**
             * 而 Class.forName方法就会触发
             */
            //测试Class.forName()
            mClassForName = Class.forName("com.hulb.java.jvm.zlass.Name");
            System.out.println("mClassForName = " + mClassForName);

            /**
             * 实例化对象肯定也是会触发的，但因为static代码块只执行一次，所以不会再有打印
             */
            //测试Object.getClass()
            Name name1 = new Name();
            mClassObjectPointClass1 = name1.getClass();
            System.out.println("mClassObjectPointClass1 = " + mClassObjectPointClass1);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        Name name2;
        System.out.println("defined one Name object");
        name2 = new Name();
        System.out.println("Name object instance done!");

        mClassObjectPointClass2 = name2.getClass();

        /**
         * 最后的打印，说明一个类的Class实例只有唯一的一个。
         */
        if (mClassForName == mClassPointClass
                && mClassPointClass == mClassObjectPointClass1
                && mClassObjectPointClass1 == mClassObjectPointClass2) {
            System.out.println("all the Class object equal...");
        }
    }
}
