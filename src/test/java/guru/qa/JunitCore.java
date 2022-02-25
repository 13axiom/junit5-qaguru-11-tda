package guru.qa;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//пишем свой аналог JUnit, чтобы понимать, как он работает
public class JunitCore {
    public static void main(String[] args) throws Exception {
        //search classes with annotation @Test
        //here we go with class SimpleTest
        Class clazz = SimpleTest.class;
        for (Method method : clazz.getDeclaredMethods()) {
            Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotation != null) {
                //run method with @Test
                try {
                    method.invoke(clazz.getConstructor().newInstance());
                    //print results
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("Test failed: " + method.getName());
                        continue;
                    } else {
                        System.out.println("Test broken: " + method.getName());
                        continue;
                    }
                }
                System.out.println("Test passed: " + method.getName());
            }
        }
    }
}
