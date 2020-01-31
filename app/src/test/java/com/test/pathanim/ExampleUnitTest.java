package com.test.pathanim;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void asdf() {
        User a = new User();
        a.name = "张三";
        a.age = 20;
        System.out.println("===" + a.toString());

        User b= (User) a.clone();
        System.out.println("===" + b.toString());

        b.age=11;
        b.name="李四";
        b.test.attr="222";
        System.out.println("===" + a.toString());
        System.out.println("===" + b.toString());

    }

    public class User implements Cloneable{
        public int age;
        public String name;
        public TestBean test;

        public   User() {
            TestBean t = new TestBean();
            t.attr = "attr";
            this.test = t;
        }
        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", test=" + test +
                    '}';
        }

        @Override
        public Object clone()  {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public class TestBean   implements Cloneable{
        public String attr;

        @Override
        public String toString() {
            return "Test{" +
                    "attr='" + attr + '\'' +
                    '}';
        }
    }
}