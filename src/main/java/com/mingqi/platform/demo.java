package com.mingqi.platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author lidefu
 * @date 2021年05月31日 9:24
 */
public class demo {
    public static void main(String[] args) {
        //使用Collection下的 stream() 和 parallelStream() 方法
        List<String> list =new ArrayList<String>();
        Stream<String> stream=list.stream();//获取一个顺序流
        Stream<String> parallelStream=list.parallelStream();//获取一个顺序流

        // 使用Arrays 中的 stream() 方法，将数组转成流
        Integer[] nums = new Integer[10];
        Stream<Integer> arrystream = Arrays.stream(nums);

       //使用Stream中的静态方法：of()、iterate()、generate()
         Stream<Integer> stream3=Stream.of(1,2,3,5,6);
         Stream<Integer> stream4=Stream.iterate(0,(x)-> x+2).limit(6);
         stream4.forEach(System.out::println);//// 0 2 4 6 8 10



    }
}
