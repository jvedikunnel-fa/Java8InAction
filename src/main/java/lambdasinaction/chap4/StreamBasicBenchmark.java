package lambdasinaction.chap4;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;


@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(
        iterations = 10
)
@Measurement(
        iterations = 10
)
@Threads(1)
@Fork(10)
public class StreamBasicBenchmark {

    @Benchmark
    public void java7() {
        StreamBasic.getLowCaloricDishesNamesInJava7(Dish.menu);
    }

    @Benchmark
    public void java8() {
        StreamBasic.getLowCaloricDishesNamesInJava8(Dish.menu);
    }

    @Benchmark
    public void java8Parallel() {
        StreamBasic.getLowCaloricDishesNamesInJava8Parallel(Dish.menu);
    }
}
