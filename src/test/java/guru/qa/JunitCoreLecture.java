package guru.qa;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//пишем свой аналог JUnit, чтобы понимать, как он работает
public class JunitCoreLecture {
    public static void main(String[] args) throws Exception {
        //search classes with annotation @Test
        //here we go with class SimpleTest
        Class clazz = SimpleTest.class; //расскажет мета-инфу о классе, какие есть поля,методы,контрукторы у объекта такого класса/типа
        //clazz.getDeclaredAnnotations() // какие аннотации есть над этим классом (выше паблик класс {{нейм_класса}},
        // в нашем примере этоаннотация на паблик класс SimpleTest, и это аннотация @DisplayName("Класс с простыми тестами")
        for (Method method : clazz.getDeclaredMethods()) { //clazz.getDeclaredMethods - какие методы есть в классе объекта clazz
            //for (Method method : clazz.getDeclaredMethods()) - мы идем циклом по всем методам которые есть в этом классе
            //то есть getDeclaredMethods() - возвращает массив методов из класса, и это и явл-ся мета-инфой о методе, а не о классе в общем
            //у каждого полученного метода мы можем запросить аннотацию и какую именно аннотацию getAnnotation(Test.class) (этот метод вернет анноатцию если она есть у метода, либо null)
            //и эту инфу(аннотаицю метода кладем в перемнную methodAnnotation:
            Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotation != null) {
                //run method with @Test
                try {
                    //у нас есть метод(объект который мы создали выше Method method и у него  есть метод invoke с 2мя аргументами
                    //, кот позволяет выполнить этот метод. Аргументы эти clazz.getConstructor().newInstance() как раз отвечают за содание
                    // объекта для вызова у него метода с помощью Reflection (https://java-course.ru/begin/reflection/). Суть в том что
                    // рефлексия позволяет вам получить информацию о внутреннем строении класса — поля, методы и т.д. — и позволяет обратиться к
                    // полям, методам и другим артифактам через эту информацию в абсолютно неизвестном методе абсолютно неизвестного класса
                    // уже во время своего исполнения. То есть в нашем примере это SimpleTest(мы знаем его и фейкового подставили в начале
                    //для того чтобы понят ькак работает JUnit, а JUnit ищет класса с аннотацией @Test и подтягивает название классов в такой
                    // обработчик и подставляет сюда. То есть фактически в такой код попадет не наш конкретный класс а абсолютно
                    // неизвестный класс, для которого рефлекшен позволяет узнать информацию об этом классе и запустить такой @Test с помощью invoke
                    //запуская методы этого самого неизвестного подстваленног ов фреймворк класса из-под аннотации
                    method.invoke(clazz.getConstructor().newInstance());
                    //далее после вызова метода класса мы ождаем от метода некое поведение - выполнится/упадет/сломается
                    //и описываем это поведение кодом Java через условия(if)
                    //для этого используем tre перед вызовом метода и catch после с InvocationTargetException - любой эксепшен который
                    // произведет и выкинет метод он будет внутри выполнения запуска метода с помощью invoke
                    //и из него мы сможем при выполнении вытянуть уже какой именно экспешен прилетел чтобы использовать его для передачи статуса
                    // через  if (e.getCause() instanceof AssertionError)
                    //continue; - нужно для того чтобы после получения эксепшена код понял, что можно не продолжать выполнение,
                    //а вызывать следующий тест
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
        //все описанное выше под созданием объекта(Class clazz = SimpleTest.class;) это Reflection API (кроме sout),
        //который позволяет в RUNTIME на основе получаемой мета-инфы изменять поведение программы
        //(это был ответ на вопрос для собеса: зачем нужен SimpleTestReflection API)

        //run all methods with @Test

        //print results
    }
}
