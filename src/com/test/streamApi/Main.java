package com.test.streamApi;

import com.test.streamApi.helper.Employee;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // Find the even numbers from a list using stream
        List<Integer> list1 = Arrays.asList(32, 43, 23, 454, 2, 9, 4, 6, 8);
        int[] array = list1.stream().mapToInt(s -> s).filter(t -> t % 2 == 0).toArray();
        list1.stream().filter(t -> t % 2 == 0).forEach(t -> System.out.println(t));


        // You got a string, count the number of vowels in it.
        System.out.println();
        String temp = "jsahdjkhioweuiewqjs";
        long count = temp.chars().filter(t -> t == 'a' || t == 'e' || t == 'i' || t == 'o' || t == 'u').count();
        System.out.println(count);
        System.out.println();

        //Sort a String in Descending
        List<Character> sortedChars = temp.chars().mapToObj(s -> (char) s).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sortedChars.stream().forEach(s -> sb.append(s));
        System.out.println("Sorted : " + sb.toString());

        // Sort the array in descending order
        int arr[] = {32, 34, 44, 3, 4, 5, 45, 44, 44};
        int[] arrayw = IntStream.of(arr).boxed().sorted(Comparator.reverseOrder()).mapToInt(s -> s).toArray();
        IntStream.of(arrayw).boxed().forEach(s -> System.err.print(s + " "));
        System.out.println();
        IntStream.of(arr).boxed().sorted(Comparator.reverseOrder()).forEach(s -> System.err.print(s + " "));
        System.out.println();

        //Create a map of Integer
        Map<Integer, Long> collect2 = IntStream.of(arr).boxed()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        collect2.forEach((k, v) -> System.out.println(k + " " + v));


        // Find the duplicated words in a string using stream API
        String sentence = "Devesh is GOAT of All time Devesh is DDevesh GOAT";
        Map<String, Long> collect = Arrays.asList(sentence.split("\\s")).stream().map(str -> str)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        collect.forEach((k, v) -> System.out.println(k + "-" + v));


        // Find most repeated element in a array using steam API -- Most IMP
        System.out.println();
        List<String> strings = Arrays.asList("java", "devesh", "java", "devesh", "java", "opts", "mouse", "wqoi");
        // First we need to group the data and count
        Map.Entry<String, Long> entry = strings.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(entry.getKey() + " - " + entry.getValue());
        //Find second-Highest repeated word
        strings.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream().sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed()).skip(1L).limit(1L).forEach(e->{
            System.out.println("Second Highest : "+e.getKey());
        });

        //Find highest paid employees in each department
        Employee emp1 = new Employee("Sales", 101, "Devesh", "Kharghar", 1200.0);
        Employee emp2 = new Employee("Developer", 102, "Beetle", "CBD", 12000.0);
        Employee emp3 = new Employee("Sales", 103, "Roshan", "Mumbai", 1200.0);
        Employee emp4 = new Employee("Developer", 104, "Bhavika", "Kharghar", 1234.0);
        Employee emp5 = new Employee("Developer", 105, "Bhau", "Kharghar", 1204.0);
        Employee emp6 = new Employee("Sales", 106, "Vedanti", "Kharghar", 12059.0);
        Employee emp7 = new Employee("Sales", 107, "ABC", "Kharghar", 1204.0);

        List<Employee> empList = Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7);
//        1. Grouping each Employee based on Department
//        2. Get the highest paid employee

        List<Employee> collect1 = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment))
                .values().stream().map(deptData -> deptData.stream()
                        .max(Comparator.comparing(Employee::getSalary)).orElse(null)).collect(Collectors.toList());

        collect1.forEach(s -> System.out.println(s.getDepartment() + " " + s.getName() + " " + s.getSalary()));

        //Simplified Way
        Map<String, List<Employee>> emp = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        List<Employee> collect3 = emp.values().stream().map(s -> s.stream()
                        .max(Comparator.comparing(Employee::getSalary)).orElse(null))
                .collect(Collectors.toList());

        System.out.println();

        //Counting the employees based on Department
        Map<String, Long> collect4 = empList.stream().collect(Collectors.groupingBy(s -> s.getDepartment(), Collectors.counting()));
        collect4.forEach((k,v)->System.out.println("k - "+k+" v : "+v));

        //Sorting by salary and then sorting by EmpId
        System.out.println("");
        System.out.println("Sorting by salary and then sorting by EmpId");
        empList.stream().map(s-> s).sorted(Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getEmpID)).forEach(s-> System.out.println(s.toString()));


        //Count Empty String
        System.out.println();
        List<String> list = List.of("", "advait", "CR7","is", "the", "goat", "", "");
        long count1 = list.stream().map(s -> s)
                .filter(t -> Objects.nonNull(t) && t.trim().isBlank()).count();
        System.out.println("Empty String are : "+count1);;


        //Square numbers in list
        System.out.println();
        System.out.println("Square numbers in list");
        List<Integer> numList = new ArrayList<>(Arrays.asList(1, 10 , 4, 100));
        numList.stream().map(i -> i * i).forEach(t-> System.out.print(t+" "));


        //Duplicate in a list using stream using hashmap
        System.out.println();
        System.out.println("Duplicate in a list using stream using hashmap");
        numList.add(10);
        numList.stream().collect(Collectors.groupingBy(s->s, Collectors.counting()))
                .forEach((k,v)-> System.out.println(k +" - "+v));


        //Reverse a string
        System.out.println();
        System.out.println("Reverse a string");
        String temp3 = "Devesh";
        var reversed = temp3.chars().mapToObj(s->String.valueOf((char)s)).reduce((s1, s2)-> s2+s1).get();
        System.out.println(reversed);

        //Reverse a string  by words
        System.out.println();
        String stringOfWords = "CR7 is the only GOAT";
        String s = Arrays.asList(stringOfWords.split("\\s")).stream().reduce((s1, s2) -> s2 +" "+ s1).get();
        System.out.println(s);

        //Reverse a List
        List<String> clothes = new ArrayList<>();
        clothes.add("T-shirt");
        clothes.add("Pants");
        clothes.add("Socks");
        clothes.add("Shoes");

        System.out.println("\nBefore reversing:");
        System.out.println(clothes);

        List<String> reverseClothes = IntStream.range(0, clothes.size()).map(i -> clothes.size() - 1- i).mapToObj(clothes::get).collect(Collectors.toList());
        System.out.println(reverseClothes);


        System.out.println("\n");
        System.out.println("Flattern the List of objects into list of integer");
        Object[] arraysss = { 1, 2, new Object[]{ 3, 4, new Object[]{ 5 }, 6, 7 }, 8, 9, 10 };
        Integer[] array1 = Arrays.stream(arraysss).flatMap(t -> {
            if (t instanceof Integer) {
                return Stream.of((Integer)t);
            } else if (t instanceof Object[]) {
                return Arrays.stream((Object[]) t).flatMap(obj -> {
                    if (obj instanceof Integer) {
                        return Stream.of((Integer)obj);
                    } else if (obj instanceof Object[]) {
                        return Arrays.stream((Object[]) obj);
                    } else {
                        return Stream.empty();
                    }
                });
            } else {
                return Stream.empty();
            }
        }).toArray(Integer[]::new);
        Arrays.stream(array1).forEach(j->System.out.print(j.intValue()+" "));


    }

}