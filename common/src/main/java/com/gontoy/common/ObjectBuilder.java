package com.gontoy.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 对象建造者
 *
 * @author gzw
 */
public class ObjectBuilder<T> {
    private List<Consumer> dInjects = new ArrayList<>();
    private Supplier<T> constructor;

    private ObjectBuilder(Supplier<T> constructor) {
        this.constructor = constructor;
    }

    @FunctionalInterface
    public interface DInjectConsumer<T, V> {
        void accept (T t, V v);
    }

    public static <T> ObjectBuilder<T> builder(Supplier<T> constructor) {
        return new ObjectBuilder<>(constructor);
    }

    public <V> ObjectBuilder<T> with(DInjectConsumer<T, V> consumer, V value) {
        Consumer<T> c = instance -> consumer.accept(instance, value);
        dInjects.add(c);
        return this;
    }

    public T build() {
        T instance = constructor.get();
        dInjects.forEach(dInject -> dInject.accept(instance));
        return instance;
    }

    public static void main(String[] args) {
        Student student = ObjectBuilder.builder(Student::new).with(Student::setName, "张三").with(Student::setAge, 18).build();
        System.out.println(student);

        List<Integer> list = Arrays.asList(1, 2, 2, 3);
        List<Integer> res = list.stream().filter(num -> num > 1).distinct().collect(Collectors.toList());
        System.out.println(res);
    }

    static class Student {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
