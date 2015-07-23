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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(BenchmarkStream.N)
@Warmup(
        iterations = 20
)
@Measurement(
        iterations = 20
)
@Threads(4)
@Fork(5)
public class BenchmarkStream {
    public static final int N = 1000000;

    static String[] array = new String[N];

    static {
        Arrays.fill(array, "AbadadWbasdasdasdagalamagA");
    }

    @Benchmark
    public void loop() {
        List<String> returnList = new ArrayList<>();
        for (String s : array) {
            s = s.toLowerCase();
            returnList.add(s);
        }
    }

    @Benchmark
    public void sequential() {
        Arrays.stream(array).map(String::toLowerCase).collect(Collectors.toList());
    }

    @Benchmark
    public void parallel() {
        Arrays.stream(array).parallel().map(String::toLowerCase).collect(Collectors.toList());
    }
}

