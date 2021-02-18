package com.sen.thread;

import com.fy.security.cipher.sm3.SM3Utils;
import org.bouncycastle.asn1.O;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;

public class Main {
    static int threadCounts = 10;//使用的线程数
    static long sum = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(threadCounts);
        List<Callable<Long>> callList = new ArrayList<Callable<Long>>();

        List<Integer> list = new ArrayList<Integer>();

        for (int j = 0; j <= 1000000; j++) {
            list.add(j);
        }

        int len = list.size() / threadCounts;//平均分割List
        //List中的数量没有线程数多（很少存在）
        if (len == 0) {
            threadCounts = list.size();//采用一个线程处理List中的一个元素
            len = list.size() / threadCounts;//重新平均分割List
        }
        for (int i = 0; i < threadCounts; i++) {
            final List<Integer> subList;
            if (i == threadCounts - 1) {
                subList = list.subList(i * len, list.size());
            } else {
                subList = list.subList(i * len, Math.min(len * (i + 1), list.size()));
            }
            //采用匿名内部类实现
            callList.add(() -> {
                long subSum = 0L;
                for (Integer i1 : subList) {
                    subSum += i1;
                }
                System.out.println("分配给线程：" + Thread.currentThread().getName() + "那一部分List的整数和为：\tSubSum:" + subSum);
                return subSum;
            });
        }
        List<Future<Long>> futureList = exec.invokeAll(callList);
        for (Future<Long> future : futureList) {
            sum += future.get();
        }
        exec.shutdown();
        System.out.println(sum);
    }

    public static String listToStr(List<String> list) {
        StringBuffer str = new StringBuffer();
        Random random = new Random(10);
        Map<Integer, List<String>> group = list.parallelStream().collect(Collectors.groupingBy(e -> random.nextInt(100)));
        group.values().parallelStream().forEach(e -> e.forEach(str::append));
        System.out.println("method1: " + str.toString());
        return str.toString();
    }

    public static String list2Str(List<String> list, final int nThreads) throws Exception {
        if (list == null || list.isEmpty()) {
            return null;
        }

        StringBuffer ret = new StringBuffer();
        int size = list.size();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        List<Future<String>> futures = new ArrayList<>(nThreads);

        for (int i = 0; i < nThreads; i++) {
            final List<String> subList = list.subList(size / nThreads * i, size / nThreads * (i + 1));
            Callable<String> task = () -> {
                StringBuilder sb = new StringBuilder();
                for (String str : subList) {
                    sb.append(str);
                }
                return sb.toString();
            };
            futures.add(executorService.submit(task));
        }

        for (Future<String> future : futures) {
            ret.append(future.get());
        }
        executorService.shutdown();
        System.out.println("method2: " + ret.toString());
        return ret.toString();
    }

    public static String sm3Encrypt_ECB(String args0) {
        String str;
        args0 += "_PTGX2021";
        str = SM3Utils.encrypt(args0);
        return str;
    }


    public static List groupListByQuantity(List list, int quantity) {
        if (list == null || list.size() == 0) {
            return list;
        }

        if (quantity <= 0) {
            new IllegalArgumentException("Wrong quantity.");
        }

        List wrapList = new ArrayList<>();
        int count = 0;
        while (count < list.size()) {
            wrapList.add(list.subList(count, Math.min((count + quantity), list.size())));
            count += quantity;
        }

        return wrapList;
    }
}


class UuidUtil {

    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            System.out.println(get32UUID());
        }
    }
}

class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        //11、 以原子方式先对下标加1再获取值
        AtomicIntegerArray atomicIntegerArray9 = new AtomicIntegerArray(10);
        atomicIntegerArray9.set(4, 10);
        System.out.println("下标为4的值为：" + atomicIntegerArray9.get(4));
        Integer result6 = atomicIntegerArray9.incrementAndGet(4);
        System.out.println("result6的值为：" + result6);
        System.out.println("下标为4的值为：" + atomicIntegerArray9.get(4));
    }
}

class User {
    private String name;
    private int age;
    private String sex;

    public User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }
}


class Test1 {
    public static void main(String[] args) {
//        List<User> list = new ArrayList<User>();
//        list.add(new User("李师师",23,"女"));
//        list.add(new User("李圆圆",26,"女"));
//        list.add(new User("李安",24,"男"));
//        list.add(new User("赵飞燕",26,"女"));
//        //统计为性别为女的个数
//        //list.stream().filter(s->s.getName()equals("0")).count()
//        Object l = list.stream().filter(s -> s.getName().startsWith("李")).collect(Collectors.groupingBy(User::getSex, Collectors.counting()));
//        System.out.println(l.toString());
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        System.out.println(System.currentTimeMillis());
        Arrays.stream(arr).sorted().forEach((x)->{});
        System.out.println(System.currentTimeMillis());
    }
}
