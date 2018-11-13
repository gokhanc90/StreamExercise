import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExercise {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        //test6ReusingException();
        //test7Reusing();
        //test8();
        //test9();
        //test10();
        //test11();
        //test12();
        //test13Reduce();
        test14ReduceSum();
    }

    public static List<Person> getPersonList(){
        List<Person> people = Arrays.asList(
                new Person("Groot", 18), new Person("Doctor_Strange", 43),
                new Person("Black_Panter", 35), new Person("Drax", 43),
                new Person("Vision", 42),new Person("Loki", 100),
                new Person("Thanos", 150),new Person("Thor", 150));
        return people;
    }

    public static void test1() {
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
    public static void test2(){
        Arrays.asList("a2", "a1", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);
    }
    public static void test3(){
        Arrays.stream(new int[]{3, 5, 7, 9})
                .map(n -> 3 * n + 1)
                .average()
                .ifPresent(System.out::println);
    }
    public static void test4(){
        Stream.of("a09", "e26", "i35")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
    }
    public static void test5(){
        IntStream.range(1, 4)
                .mapToObj(i -> "n" + i)
                .forEach(System.out::println);
    }
    public static void  test6ReusingException(){
        Stream<String> stream =
                Stream.of("t2", "a2", "w1", "p3", "hc")
                        .filter(s -> s.startsWith("a"));

        System.out.println(stream.anyMatch(s -> true));
        System.out.println(stream.noneMatch(s -> true));
    }

    public static void  test7Reusing(){
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        System.out.println(streamSupplier.get().anyMatch(s -> true));
        System.out.println(streamSupplier.get().noneMatch(s -> true));
    }

    public static void test8(){
        List<Person> people = getPersonList();

        List<Person> filtered = people.stream()
                        .filter(p -> p.name.startsWith("T"))
                        .collect(Collectors.toList());

        System.out.println(filtered);

    }

    public static void test9(){
        List<Person> people = getPersonList();

        Map<Integer, List<Person>> peopleByAge = people
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        peopleByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

    }

    public static void test10(){
        List<Person> people = getPersonList();

        Map<String, List<Person>> peopleByFirstCharacter = people
                .stream()
                .collect(Collectors.groupingBy(p -> p.name.substring(0,1)));

        peopleByFirstCharacter.forEach((firstCharacter, p) -> System.out.format("First Character %s: %s\n", firstCharacter, p));

    }

    public static void test11(){
        List<Person> people = getPersonList();

        Double averageAge = people
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge);

        IntSummaryStatistics ageSummary = people
                .stream()
                .collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ageSummary);
    }

    public static void test12(){
        List<Person> people = getPersonList();

        String phrase = people
                .stream()
                .filter(p -> p.age > 50)
                .map(p -> p.name + "(" + p.age + ")")
                .collect(Collectors.joining(" and ", "People ", " are older than 50 years old."));

        System.out.println(phrase);
    }

    public static void test13Reduce(){
        List<Person> people = getPersonList();

        people
                .stream()
                .reduce((p1, p2) -> p1.age < p2.age ? p1 : p2)
                .ifPresent(System.out::println);
    }

    public static void test14ReduceSum(){
        List<Person> people = getPersonList();

        Integer ageSum = people
                .stream()
                .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

        System.out.println(ageSum);
    }
}
