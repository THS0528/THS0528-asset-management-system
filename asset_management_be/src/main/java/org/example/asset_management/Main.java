package org.example.asset_management;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @author THS.
 * @version 1.0
 * @project asset_management_be
 * @date 2024/4/21 23:57:56
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        // 获取当前时间
        LocalDateTime time = LocalDateTime.now();
        //设置时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        //格式化时间
        String now = time.format(formatter);
        now = "zc"+now;
        System.out.println(now.length());
        int count = 0;
        int a [] = {1,5,4,9,8,6,75,4,44,12,21,10};
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a.length; j++){
                if (a[i] < a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    count++;
                }
            }
        }
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println(count);
    }
}
