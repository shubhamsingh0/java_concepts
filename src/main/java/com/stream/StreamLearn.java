package com.stream;

import com.learn.javaconcepts.Artist;
import com.models.ExportList;
import com.models.Person;
import com.models.Product;
import com.models.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.util.GeneratorUtils.*;

public class StreamLearn {

    // There are total 9 static methods, 32 Abstract methods and 8 default methods in JDK 24,
    // Instance Methods = Abstract Methods + Default Methods => 40 instance methods

    public static void main(String[] args) throws IOException {
//        streamStaticMethods();
        streamAbstractMethods();
        System.out.println(fifthNearestPalindromeNumber(45,5));
    }


    public static void streamAbstractMethods() throws IOException {
/*
        Modifier & Type || Method                                                                                        || Description
        Intermediate Operations
        Stream<T>       || filter(Predicate<? super T> predicate)                                                        || Returns a stream consisting of the elements of this stream that match the given predicate.
        <R> Stream<R>   || map(Function<? super T, ? extends R> mapper)                                                  || Returns a stream consisting of the results of applying the given function to the elements of this stream.
        IntStream       || mapToInt(ToIntFunction<? super T> mapper)                                                     || Returns an IntStream consisting of the results of applying the given function to the elements of this stream.
        LongStream      || mapToLong(ToLongFunction<? super T> mapper)                                                   || Returns a LongStream consisting of the results of applying the given function to the elements of this stream.
        DoubleStream    || mapToDouble(ToDoubleFunction<? super T> mapper)                                               || Returns a DoubleStream consisting of the results of applying the given function to the elements of this stream.
        <R> Stream<R>   || flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)                            || Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.
        IntStream       || flatMapToInt(Function<? super T, ? extends IntStream> mapper)                                 || Returns an IntStream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.
        LongStream      || flatMapToLong(Function<? super T, ? extends LongStream> mapper)                               || Returns an LongStream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.
        DoubleStream    || flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper)                           || Returns an DoubleStream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.
        Stream<T>       || peek(Consumer<? super T> action)                                                              || Returns a stream consisting of the elements of this stream, additionally performing the provided action on each element as elements are consumed from the resulting stream.

        Stateful Intermediate Operation
        Stream<T>       || distinct()                                                                                    || Returns a stream consisting of the distinct elements (according to Object.equals(Object)) of this stream.
        Stream<T>       || sorted()                                                                                      || Returns a stream consisting of the elements of this stream, sorted according to natural order.
        Stream<T>       || sorted(Comparator<? super T> comparator)                                                      || Returns a stream consisting of the elements of this stream, sorted according to the provided Comparator.
        Stream<T>       || skip(long n)                                                                                  || Returns a stream consisting of the remaining elements of this stream after discarding the first n elements of the stream.

        Short-circuiting Stateful Intermediate Operation
        Stream<T>       || limit(long maxSize)                                                                           || Returns a stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.

        Short-Circuiting Terminal Operation
        boolean         || allMatch(Predicate<? super T> predicate)                                                      || Returns whether all elements of this stream match the provided predicate.
        boolean         || anyMatch(Predicate<? super T> predicate)                                                      || Returns whether any elements of this stream match the provided predicate.
        boolean         || noneMatch(Predicate<? super T> predicate)                                                     || Returns whether no elements of this stream match the provided predicate.
        Optional<T>     || findAny()                                                                                     || Returns an Optional describing some element of the stream, or an empty Optional if the stream is empty.
        Optional<T>     || findFirst()                                                                                   || Returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty.

        Terminal Operation
        void            || forEach(Consumer<? super T> action)                                                           || Performs an action for each element of this stream.
        void            || forEachOrdered(Consumer<? super T> action)                                                    || Performs an action for each element of this stream, in the encounter order of the stream if the stream has a defined encounter order.
        Optional<T>     || max(Comparator<? super T> comparator)                                                         || Returns the maximum element of this stream according to the provided Comparator.
        Optional<T>     || min(Comparator<? super T> comparator)                                                         || Returns the minimum element of this stream according to the provided Comparator.
        long            || count()                                                                                       || Returns the count of elements in this stream.
        Object[]        || toArray()                                                                                     || Returns an array containing the elements of this stream.
        <A> A[]         || toArray(IntFunction<A[]> generator)                                                           || Returns an array containing the elements of this stream, using the provided generator function to allocate the returned array, as well as any additional arrays that might be required for a partitioned execution or for resizing.
        Optional<T>     || reduce(BinaryOperator<T> accumulator)                                                         || Performs a reduction on the elements of this stream, using an associative accumulation function, and returns an Optional describing the reduced value, if any.
        T               || reduce(T identity, BinaryOperator<T> accumulator)                                             || Performs a reduction on the elements of this stream, using the provided identity value and an associative accumulation function, and returns the reduced value.
        <U> U           || reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)       || Performs a reduction on the elements of this stream, using the provided identity, accumulation and combining functions.
        <R,A> R         || collect(Collector<? super T, A, R> collector)                                                 || Performs a mutable reduction operation on the elements of this stream using a Collector.
        <R> R           || collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R,R> combiner) || Performs a mutable reduction operation on the elements of this stream.
*/

        // Data
        List<Person> personList = readPersonData();
        List<Product> productList = generateProductData();
        List<Student> studentList = generateStudentData();

        // Intermediate Operation

        // filter - Intermediate Operation - non-interfering, stateless - Filter out all Person objects who live in "New York".
        Predicate<Person> filterByCityNewYork = (i) -> i.getAddress().contains("New York");
        System.out.println(personList.stream().filter(filterByCityNewYork).toList());

        // --------------------------------------- MAP -----------------------------------------------
        // map - Intermediate Operation - non-interfering, stateless - Convert a list of Person objects to a list of their full names.
        System.out.println(personList.stream().map(person -> person.getFirstName().concat(" ").concat(person.getSecondName())).toList());

        // mapToInt - Intermediate Operation - non-interfering, stateless - Convert a list of Person objects to a stream of their ages as ints.
        ToIntFunction<? super Person> toIntFunction = Person::getAge;
        System.out.println(Arrays.toString(personList.stream().mapToInt(toIntFunction).toArray()));

        // mapToLong - Intermediate Operation - non-interfering, stateless - Convert a list of File objects to a stream of their sizes as longs.
        Stream<Path> paths = Files.list(Path.of("src/main/java/com/util")); // reads files present in directory
        System.out.println(Arrays.toString(paths.filter(Files::isRegularFile).mapToLong(path -> {
            try {
                return Files.size(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toArray()));

        // mapToDouble - Intermediate Operation - non-interfering, stateless -  Convert a list of Product objects to a stream of their prices as doubles.
        System.out.println(Arrays.toString(productList.stream().mapToDouble(Product::getPrice).toArray()));

        // flatMap(Function<? super T, ? extends Stream> mapper), - Intermediate Operation - non-interfering, stateless - Given a list of Person objects, each with a list of phone numbers, get a flat list of all phone numbers.
        // when to we don't want to use below implementation to get the list of phone numbers of all person =>
        List<String> phoneNums = new ArrayList<>();
        personList.stream().forEach(person -> {
            // either directly add in list using addAll
            phoneNums.addAll(person.getPhoneNumber());
            // or
            person.getPhoneNumber().forEach(p -> {
                phoneNums.add(p);
            });
        });
        // using flat map, which takes function which needs to return a stream.
        System.out.println(personList.stream().flatMap(person -> person.getPhoneNumber().stream()).toList().size());

        // flatMapToInt(Function<? super T, ? extends IntStream> mapper) - Intermediate Operation - non-interfering, stateless - Given a list of Student objects, each with a list of grades, get a stream of all grades as ints.
        // to collect IntStream, LongStream, DoubleStream used boxed() method and then toList(). list.stream().mapToInt(i->i).boxed().toList();
        System.out.println(studentList.stream().flatMapToInt(student -> student.getGrades().stream().mapToInt(i -> i)).boxed().toList());

        // flatMapToLong(Function<? super T, ? extends LongStream> mapper) - Intermediate Operation - non-interfering, stateless - Given a list of Event objects, each with a list of timestamps, get a stream of all timestamps as longs.
        System.out.println(productList.stream().flatMapToLong(product -> product.getOrderModificationTimestamp().stream().mapToLong(Timestamp::getTime)).boxed().toList());

        // flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) - Intermediate Operation - non-interfering, stateless - Given a list of Order objects, each with a list of item prices, get a stream of all prices as doubles.
        System.out.println(productList.stream().flatMapToDouble(product -> product.getPrices().stream().mapToDouble(i -> i)).boxed().toList());

        // peek(Consumer<? super T> action) - Intermediate Operation - non-interfering - While filtering Person objects above 30, print their names as you process them. - Like printing element for debug purpose.
        System.out.println(personList.stream().filter(person->person.getAge()>30).peek(person -> System.out.println(person.getFirstName())).limit(10).toList());


        // Stateful Intermediate Operation

        // distinct() - Stateful Intermediate Operation - From a list of emails (with duplicates), get a list of unique emails.
        List<String> emails = new ArrayList<>();
        emails.add("john@gmail.com");
        emails.add("baby@gmail.com");
        emails.add("John@gmail.com");
        emails.add("john@gmail.com");
        emails.add("dida@yahoo.com");
        System.out.println(emails.stream().distinct().toList());

        /*
         sorted() - Stateful Intermediate Operation - Sort a list of Person objects by their last name.
         If you use sorted() without a comparator, it sorts the elements according to their natural order.
         This means the elements in the stream must implement the Comparable interface.
         For example, for a list of String, it sorts alphabetically; for numbers, numerically.
         If Person does not implement Comparable, using sorted() will throw a ClassCastException.
         System.out.println(personList.stream().sorted().toList()); // Exception in thread "main" java.lang.ClassCastException: class com.models.Person cannot be cast to class java.lang.Comparable
         */
        System.out.println(personList.stream().sorted(Comparator.comparing(Person::getSecondName)).toList()); // since secondName is of String Class which already implements Comparable there will not be any issue.

        // sorted(Comparator<? super T> comparator) - Stateful Intermediate Operation - Sort a list of Person objects by age descending.
        System.out.println(personList.stream().sorted(Comparator.comparing(Person::getAge, (a,b)->b-a)).toList());
        System.out.println(personList.stream().sorted(Comparator.comparing(Person::getAge, Comparator.reverseOrder())).toList());
        System.out.println(personList.stream().sorted(Comparator.comparing(Person::getAge).reversed()).toList());
        System.out.println(personList.stream().sorted(Comparator.comparing(Person::getAge, Comparator.reverseOrder())
                .thenComparing(Person::getFirstName,Comparator.reverseOrder())).toList());

        // skip(long size) - Stateful Intermediate Operation - Skip the first 3 Person objects and process the rest.
        System.out.println(personList.stream().skip(3).toList());


        // limit(long size) - Short-Circuiting Terminal Operation - Get the first 5 Person objects from a list.
        System.out.println(personList.stream().limit(5).toList());

        // allMatch(Predicate<? super T> p) - Short-circuiting Terminal Operation - non-interfering, stateless - Check if all Person objects are older than 18.
        Predicate<Person> ageCheck = (person)-> person.getAge()>18;
        System.out.println(personList.stream().allMatch(ageCheck));

        // anyMatch(Predicate<? super T> p) - Short-Circuiting Terminal Operation - non-interfering, stateless - Check if any Person in the list has an email ending with @gmail.com.
        Predicate<Person> checkGmail = (person) -> person.getEmail().contains("gmail");
        System.out.println(personList.stream().anyMatch(checkGmail));

        // noneMatch(Predicate<? super T> p) - Short-Circuiting Terminal Operation - non-interfering, stateless are properties of parameters passed - Check if no Person in the list has a null address.
        System.out.println(personList.stream().noneMatch((person)-> person.getAddress() == null));

        // findAny() - Short-Circuiting Terminal Operation - Find any Person whose last name starts with "S".
        System.out.println(personList.stream().filter(person->person.getSecondName().startsWith("S")).findAny());

        // findFirst() - Short-Circuiting Terminal Operation - Find the first Person who are born after 2000.
        System.out.println(personList.stream().filter(person->person.getBirthDate().after(Date.from(Instant.parse("2024-09-12T12:00:00Z")))).findFirst());


        // Terminal Operations

        // forEach(Consumer<? super T>) - Terminal Operation - non-interfering - Print all Person emails.
        personList.stream().forEach(person-> System.out.println(person.getEmail()));

        // forEachOrdered(Consumer<? super T> action> - Terminal Operation - non-interfering - Print all Person first names in the order they appear in the list.
        personList.stream().forEachOrdered(person -> System.out.println(person.getFirstName()));

        // max(Comparator<? super T> - Terminal Operation - non-interfering, stateless - Find the Person with the maximum age.
        System.out.println(personList.stream().max(Comparator.comparing(Person::getAge)).get());

        // min(Comparator<? super T> - Terminal Operation - non-interfering, Stateless - Find the Person with the minimum age.
        System.out.println(personList.stream().min(Comparator.comparing(Person::getAge)).get());

        // count - Terminal Operation - equivalent to .mapToLong(i->1L).sum() - Count how many Person objects have a non-null phone number.
        System.out.println(personList.stream().filter(person -> Objects.nonNull(person.getPhoneNumber())).count());

        // toArray() - Terminal Operation - Convert a stream of Person objects to an array.
        System.out.println(Arrays.toString(personList.stream().toArray()));

        // toArray(IntFunction<? super T> generator) - Terminal Operation - Convert a stream of Person objects to an array.
        System.out.println(Arrays.toString(personList.stream().toArray(Person[]::new)));

        // reduce(BinaryOperator<T> accumulator - Terminal Operation - accumulator-> an associative, non-interfering, Stateless function for combining 2 values.
        // .reduce(BinaryOperator) is more general. It combines elements using any associative operation [a op (b op c)] == [(a op b) op c] (not just max/min), such as sum, product, or custom logic.
        System.out.println(productList.stream().filter(product -> product.getProductName().equalsIgnoreCase("Laptop")).map(Product::getPrice).reduce(Double::sum));
        System.out.println(productList.stream().filter(product -> product.getProductName().equalsIgnoreCase("Laptop")).map(Product::getPrice).reduce((product1,product2)->product1+product2));
        // If you want the product name with the highest total sales (price * quantity)
        Map<String, List<Product>> as = productList.stream().collect(Collectors.groupingBy(Product::getProductName));
        Set<Map.Entry<String, List<Product>>> entries = as.entrySet();
        Map<String, Double> max = new HashMap<>();
        for(Map.Entry<String, List<Product>> entry:entries){
            max.put(entry.getKey(), entry.getValue().stream().map(pr->pr.getPrice()*pr.getQuantity()).reduce(Double::sum).get());
        }
        System.out.println(max.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).findFirst().get());
        // Optimized Code: Find product name with highest total sales (price * quantity)
        Map.Entry<String, Double> maxEntry = productList.stream()
                .collect(Collectors.groupingBy(Product::getProductName, Collectors.summingDouble(p -> p.getPrice() * p.getQuantity())))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        System.out.println(maxEntry);
        // If you want the single product instance with the highest sales
        BinaryOperator<Product> productBinaryOperator = (p1, p2) ->
                (p1.getPrice() * p1.getQuantity() >= p2.getPrice() * p2.getQuantity()) ? p1 : p2;
        Optional<Product> topProduct = productList.stream()
                .reduce(productBinaryOperator
                );
        System.out.println(topProduct.get());

        // reduce(T identity, BinaryOperator<T> accumulator) identity is initial value
        // You have a list of Product objects, each with a price (double) and a quantity (int). Calculate the total
        // revenue for all products, but only for products whose price is above 100 and quantity is at least 5.
        double ans = productList.stream().filter(product -> product.getQuantity()>=5 && product.getPrice()>100)
                .mapToDouble(pr->pr.getPrice()*pr.getQuantity())
                .reduce(0.0, Double::sum);
        System.out.println(ans);

        /*
         reduce(U identity, BiFunction<U,? super T, U> accumulator, BinaryOperator<U> combiner) => it a combination of .map + .collect
         You want to combine stream elements into a single result using an associative operation (e.g., sum, product, min, max, custom logic).
         The result is a single value (e.g., a number, a string, or a single object).
         Example: Summing numbers, finding the product, concatenating strings.
         */
        BiFunction<ExportList, Product, ExportList> accumulator = (exportList, product) -> {
            exportList.getTotalItems().add(product.getProductName());
            exportList.setTotalPrice(exportList.getTotalPrice() + product.getPrice());
            return exportList;
        };
        // Combiner: merges two ExportList objects
        BinaryOperator<ExportList> combiner = (e1, e2) -> {
            e1.getTotalItems().addAll(e2.getTotalItems());
            e1.setTotalPrice(e1.getTotalPrice() + e2.getTotalPrice());
            return e1;
        };
        // Usage
        ExportList result = productList.stream()
                .reduce(new ExportList(new HashSet<>(), 0.0), accumulator, combiner);
        System.out.println(result);

        // collect(Collelctor<? super T, A, R colelctor>)
        // T: type of stream elements
        // A: mutable accumulation type [intermediate container]
        // R: result type [Final output]
        Set<Person> uniquePerson = personList.stream().collect(Collectors.toSet());
        System.out.println(uniquePerson);

        /*
         collect(Supplier<? super T> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R,R> combiner)
         You want to transform the stream into a mutable container or a complex result (e.g., List, Set, Map, custom objects).
         The result is a collection or a more complex structure, not just a single value.
         Example: Collecting elements into a list, grouping by a property, partitioning, summarizing statistics.
         */
        HashMap<String, String> mappedRes = personList.stream().collect(HashMap<String, String>::new,
                (hashMap, person2) -> hashMap.put(person2.getFirstName(), person2.getSecondName()),
                ((hashMap, hashMap2) -> hashMap.putAll(hashMap2)));
        System.out.println(mappedRes);

    }


    public static void streamDefaultMethods() throws IOException {
/*
        Modifier and Type     || Method                                                                   || Description
        default Stream<T>     || dropWhile(Predicate<? super T> predicate)                                || Returns, if this stream is ordered, a stream consisting of the remaining elements of this stream after dropping the longest prefix of elements that match the given predicate.
        default <R> Stream<R> || gather(Gatherer<? super T, ?, R> gatherer)                               || Returns a stream consisting of the results of applying the given Gatherer to the elements of this stream.
        default <R> Stream<R> || mapMulti(BiConsumer<? super T, ? super Consumer<R>> mapper)              || Returns a stream consisting of the results of replacing each element of this stream with multiple elements, specifically zero or more elements.
        default DoubleStream  || mapMultiToDouble(BiConsumer<? super T, ? super DoubleConsumer> mapper)   || Returns a DoubleStream consisting of the results of replacing each element of this stream with multiple elements, specifically zero or more elements.
        default IntStream     || mapMultiToInt(BiConsumer<? super T, ? super IntConsumer> mapper)         || Returns an IntStream consisting of the results of replacing each element of this stream with multiple elements, specifically zero or more elements.
        default LongStream    || mapMultiToLong(BiConsumer<? super T, ? super LongConsumer> mapper)       || Returns a LongStream consisting of the results of replacing each element of this stream with multiple elements, specifically zero or more elements.
        default Stream<T>     || takeWhile(Predicate<? super T> predicate)                                || Returns, if this stream is ordered, a stream consisting of the longest prefix of elements taken from this stream that match the given predicate.
        default List<T>       || toList()                                                                 || Accumulates the elements of this stream into a List.
*/

    }

    public static void streamStaticMethods() throws IOException {


        /*
        static <T> Stream.Builder<T> || builder()                                                             || Returns a builder for a Stream.
        static <T> Stream<T>         || concat(Stream<? extends T> a, Stream<? extends T> b)                  || Creates a lazily concatenated stream whose elements are all the elements of the first stream followed by all the elements of the second stream.
        static <T> Stream<T>         || empty()                                                               || Returns an empty sequential Stream.
        static <T> Stream<T>         || generate(Supplier<? extends T> s)                                     || Returns an infinite sequential unordered stream where each element is generated by the provided Supplier.
        static <T> Stream<T>         || iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)  || Returns a sequential ordered Stream produced by iterative application of the given next function to an initial element, conditioned on satisfying the given hasNext predicate.
        static <T> Stream<T>         || iterate(T seed, UnaryOperator<T> f)                                   || Returns an infinite sequential ordered Stream produced by iterative application of a function f to an initial element seed, producing a Stream consisting of seed, f(seed), f(f(seed)), etc.
        static <T> Stream<T>         || of(T t)                                                               || Returns a sequential Stream containing a single element.
        static <T> Stream<T>         || of(T... values)                                                       || Returns a sequential ordered stream whose elements are the specified values.
        static <T> Stream<T>         || ofNullable(T t)                                                       || Returns a sequential Stream containing a single element, if non-null, otherwise returns an empty Stream.
        */

//        List<Person> personList = generatePersonData();
        List<Person> personList = readPersonData();

//        1. Create a stream of custom Person objects using Stream.builder(). Add at least three people, then collect their emails into a list.

        Stream.Builder<Person> builder = Stream.builder();
        builder.add(personList.get(0));
        builder.add(personList.get(1));
        builder.add(personList.get(2));
        List<Person> list1 = builder.build().toList();
        System.out.println(list1);

//        2. Given two lists of Person objects (e.g., one for employees, one for customers), use Stream.concat() to create a single stream and print all unique addresses.
        Stream<Person> employees = personList.stream().limit(10);
        Stream<Person> customer = personList.stream().limit(5);
        List<String> add = Stream.concat(employees, customer).map(Person::getAddress).distinct().toList();
        System.out.println(add);

//        3. Write a method that returns an empty stream if a given list is null or empty, otherwise returns a stream of its elements. Demonstrate its use.
        Stream<Person> emptyStream = Stream.empty();

//        4. Use Stream.generate() to create a stream of random ages (integers between 18 and 60). Limit the stream to 10 elements and print them.
        List<Double> ageList = Stream.generate(() -> Math.random() * 100).limit(100).filter(age -> age > 18 && age < 60).toList();
        System.out.println(ageList);

//        5. Generate a stream of numbers starting from 1, incrementing by 2, and stopping before 20. Collect the numbers into a list.
        List<Integer> intList = Stream.iterate(1, x -> x + 2).limit(20).toList();
        System.out.println(intList);

//        6. Use Stream.iterate() to create an infinite stream of dates, each one day after the previous, starting from today. Limit to 5 dates and print them.
        List<Date> intList2 = Stream.iterate(new Date(), i -> i.before(Date.from(Instant.now().plus(5, ChronoUnit.DAYS))), i -> Date.from(i.toInstant().plus(1, ChronoUnit.DAYS))).toList();
        System.out.println(intList2);

//        7. Use of(T t)
        List<Person> persons = Stream.of(new Person()).toList();
        System.out.println(persons);

//        8. Use ofNullable(T t)
        Person p = null;
        List<Person> person = Stream.ofNullable(p).toList();
        System.out.println(person);

//        9. Use of(T... values)
        Person p1 = new Person();
        p1.setFirstName("Person1");
        Person p2 = new Person();
        p2.setFirstName("Person2");
        Artist a = new Artist();
        a.setName("Artist");
        Integer i = 20;
        List<Object> personLists = Stream.of(p1, p2, a, i).toList();
        System.out.println(personLists);
    }

    public static Long fifthNearestPalindromeNumber(long n,int kPosition) {

        Map<Long, Long> palindromes = generatePalindromes(n);
        Map<Long, List<Map.Entry<Long, Long>>> distanceToPalindromeMap = palindromes.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue));

//        distanceToPalindromeMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).toList().get(kPosition);
        Map.Entry<Long, List<Map.Entry<Long, Long>>> actualValue = distanceToPalindromeMap.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.naturalOrder())).skip(kPosition-1).findFirst().get();
        Long ans = 0L;

        // 3 ways to achieve same result
        ans = actualValue.getValue().stream().map(Map.Entry::getKey).sorted(Comparator.reverseOrder()).findFirst().get();
        ans = actualValue.getValue().stream().sorted(Comparator.comparing(Map.Entry::getKey)).map(Map.Entry::getKey).findFirst().get();
        ans = actualValue.getValue().stream().map(Map.Entry::getKey).max(Comparator.naturalOrder()).get(); // preferred
        return  ans;
    }

    private static Map<Long, Long> generatePalindromes(long n) {
        Map<Long, Long> map = new TreeMap<>();

        for (long i=11; i<2*n;i++) {
            if(isPalindrome(i)) {
                map.put(i, Math.abs(n-i));
            }
        }
        return map;
    }

    private static boolean isPalindrome(long i) {
        String a = String.valueOf(i);
        String b = new String("");
        for(int e= a.length()-1;e>=0;e--) {
            b = b.concat(String.valueOf(a.charAt(e)));
        }
        return a.equals(b);
    }

}
