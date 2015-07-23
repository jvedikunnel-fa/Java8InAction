package lambdasinaction.chap1;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(Benchmark2.N)
@Warmup(
        iterations = 5
)
@Measurement(
        iterations = 5
)
@Threads(4)
@Fork(5)
public class Benchmark2 {
    public static final int N = 1000000;

    static List<Integer> sourceList = new ArrayList<>();

    static {
        for (int i = 0; i < N; i++) {
            sourceList.add(i);
        }
    }

    public Benchmark2() {
    }

    @Benchmark
    public void vanilla() {
        List<Double> result = new ArrayList<>();
        for (Integer i : sourceList) {
            if (i % 2 == 0) {
                result.add(Math.sqrt(i));
            }
        }
//        return result;
    }

    @Benchmark
    public void stream() {
        sourceList.stream()
                .filter(i -> i % 2 == 0)
                .map(Math::sqrt)
                .collect(Collectors.counting());
    }

    @Benchmark
    public void parallelStream() {
        sourceList.parallelStream()
                .filter(i -> i % 2 == 0)
                .map(Math::sqrt)
                .collect(Collectors.counting());
    }
}
