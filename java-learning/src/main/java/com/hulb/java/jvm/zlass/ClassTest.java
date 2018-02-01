package com.hulb.java.jvm.zlass;

/**
 * @author hulb
 * @date 2018/1/25 下午2:17
 *
 * 在Java里，所有的类的根源都是Object类，而Class也不例外，它是继承自Object的一个特殊的类，它内部可以记录类的成员、接口等信息，
 * 也就是在Java里，Class是一个用来表示类的类
 *
 * 我们知道java世界是运行在JVM之上的，我们编写的类代码，在经过编译器编译之后，会为每个类生成对应的.class文件，
 * 这个就是JVM可以加载执行的字节码。
 *
 * 运行时期间，当我们需要实例化任何一个类时，JVM会首先尝试看看在内存中是否有这个类，
 * 如果有，那么会直接创建类实例；
 * 如果没有，那么就会根据类名去加载这个类，
 *
 * 当加载一个类，或者当加载器(class loader)的defineClass()被JVM调用，
 * *******   便会为这个类产生一个Class对象（一个Class类的实例），用来表达这个类，*******
 * 该类的所有实例都共同拥有着这个Class对象，而且是唯一的。
 */
public class ClassTest {

   public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
       /**
        * 1.运用 类名.class 的方式来获取Class实例；
        */
       ClassTest classTest = ClassTest.class.newInstance();
       classTest.printLine();

       /**
        * 2.利用对象实例调用getClass()方法获取该对象的Class实例；
        */
       if(classTest.getClass()==ClassTest.class){
           System.out.println("true...");
       }

       Class<ClassTest> classTestClass = ClassTest.class;

       /**
        * 3.使用Class类的静态方法forName("包名+类名")，用类的名字获取一个Class实例
        * 一定要包名+类名
        */
       Class c = Class.forName("com.hulb.java.jvm.zlass.ClassTest");
       ClassTest classTest1 = (ClassTest) c.newInstance();
       classTest1.printLine();
   }

   public void printLine(){
       System.out.println("out...");
   }
}
