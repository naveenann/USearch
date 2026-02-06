package cloud.unum.usearch;

public class SearchResult {

    private final long key;
    private final float distance;

    public SearchResult(long key, float distance) {
        this.key = key;
        this.distance = distance;
    }

    public long getKey() {
        return key;
    }

    public float getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "SearchResult{key=" + key + ", distance=" + distance + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SearchResult that = (SearchResult) obj;
        return key == that.key && Float.compare(that.distance, distance) == 0;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(key);
        result = 31 * result + Float.hashCode(distance);
        return result;
    }

    public static class Results {

        private final SearchResult[] results;
        private final int totalResults;

        public Results(SearchResult[] results) {
            this.results = results;
            this.totalResults = results.length;
        }

        public SearchResult[] getResults() {
            return results;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public SearchResult get(int index) {
            return results[index];
        }

        @Override
        public String toString() {
            return "Results{totalResults=" + totalResults + ", results=" + java.util.Arrays.toString(results) + "}";
        }
    }
}
