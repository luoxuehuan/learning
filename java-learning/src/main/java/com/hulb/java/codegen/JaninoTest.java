package com.hulb.java.codegen;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.IScriptEvaluator;
import org.codehaus.janino.*;

import java.lang.reflect.InvocationTargetException;

/**
 * @author hulb
 * @date 2018/2/2 下午2:20
 */
public class JaninoTest {

    public static void main(String[] args) throws CompileException, InvocationTargetException {

        /**
         * 指定ab值为3+4
         */
        ExpressionEvaluator ee1 = new ExpressionEvaluator();
        ee1.cook("3 + 4");
        // Prints "7".
        System.out.println(ee1.evaluate(null));


        /**
         * 可以传入不同的值 ，但是 指定了参数类型
         */
        ExpressionEvaluator ee = new ExpressionEvaluator();

        // The expression will have two "int" parameters: "a" and "b".
        ee.setParameters(new String[] { "a", "b" }, new Class[] { int.class, int.class });

        // And the expression (i.e. "result") type is also "int".
        ee.setExpressionType(int.class);

        // And now we "cook" (scan, parse, compile and load) the fabulous expression.
        ee.cook("a + b");

        // Eventually we evaluate the expression - and that goes super-fast.
        int result = (Integer) ee.evaluate(new Object[] { 19, 23 });
        System.out.println(result);




//        ScriptEvaluator se = new ScriptEvaluator();
//
//        se.cook(
//                ""
//                        + "static void method1() {\n"
//                        + "    System.out.println(1);\n"
//                        + "}\n"
//                        + "\n"
//                        + "method1();\n"
//                        + "method2();\n"
//                        + "\n"
//                        + "static void method2() {\n"
//                        + "    System.out.println(2);\n"
//                        + "}\n"
//        );
//
//        se.evaluate(new Object[0]);


        IScriptEvaluator se = new ScriptEvaluator();
//        MyInterface

    }


}
