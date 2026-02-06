import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cloud.unum.usearch.Index;
import cloud.unum.usearch.SearchResult;
import org.junit.Test;

public class SearchResultTest {

    @Test
    public void testSearchWithDistances() {
        try (Index index = new Index.Config().metric("cos").dimensions(3).build()) {
            index.reserve(10);

            float[] vec1 = {1.0f, 0.0f, 0.0f};
            float[] vec2 = {0.0f, 1.0f, 0.0f};
            float[] vec3 = {0.0f, 0.0f, 1.0f};

            index.add(1, vec1);
            index.add(2, vec2);
            index.add(3, vec3);

            SearchResult.Results results = index.searchWithDistances(vec1, 3);

            assertEquals(3, results.getTotalResults());
            assertEquals(3, results.getResults().length);

            assertEquals(1, results.get(0).getKey());
            assertTrue(results.get(0).getDistance() < 0.01f);

            System.out.println("Search results:");
            for (SearchResult result : results.getResults()) {
                System.out.println("  Key: " + result.getKey() + ", Distance: " + result.getDistance());
            }
        }
    }

    @Test
    public void testSearchWithDistancesDouble() {
        try (Index index = new Index.Config()
                .metric("cos")
                .dimensions(3)
                .quantization("f64")
                .build()) {
            index.reserve(10);

            double[] vec1 = {1.0, 0.0, 0.0};
            double[] vec2 = {0.0, 1.0, 0.0};

            index.add(1, vec1);
            index.add(2, vec2);

            SearchResult.Results results = index.searchWithDistances(vec1, 2);

            assertEquals(2, results.getTotalResults());
            assertEquals(1, results.get(0).getKey());
        }
    }

    @Test
    public void testSearchWithDistancesByte() {
        try (Index index = new Index.Config()
                .metric("cos")
                .dimensions(4)
                .quantization("i8")
                .build()) {
            index.reserve(10);

            byte[] vec1 = {10, 20, 30, 40};
            byte[] vec2 = {50, 60, 70, 80};

            index.add(1, vec1);
            index.add(2, vec2);

            SearchResult.Results results = index.searchWithDistances(vec1, 2);

            assertEquals(2, results.getTotalResults());
            assertEquals(1, results.get(0).getKey());
        }
    }
}
