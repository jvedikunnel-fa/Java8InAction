package lambdasinaction.chap13;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(
        iterations = 5
)
@Measurement(
        iterations = 5
)
@Threads(4)
@Fork(2)
public class Recursion {

//    public static void main(String[] args) {
//        System.out.println(factorialIterative(5));
//        System.out.println(factorialRecursive(5));
//        System.out.println(factorialStreams(5));
//        System.out.println(factorialTailRecursive(5));
//    }

    @Benchmark
    public void factorialIterative() {
        factorialIterative(5);
    }

    public static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r*=i;
        }
        return r;
    }

    @Benchmark
    public void factorialRecursive() {
        factorialRecursive(5);
    }

    public static long factorialRecursive(long n) {
        return n == 1 ? 1 : n*factorialRecursive(n-1);
    }

    @Benchmark
    public void factorialStreams() {
        factorialStreams(5);
    }

    public static long factorialStreams(long n){
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long a, long b) -> a * b);
    }

    @Benchmark
    public void factorialTailRecursive() {
        factorialTailRecursive(5);
    }

    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    public static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n-1);
    }
}
