package sebaszczen.domain;

public class LimitConfiguration {

    int max;
    int min;
    int middle;

    public LimitConfiguration(int max, int min, int middle) {
        this.max = max;
        this.min = min;
        this.middle = middle;
    }

    public LimitConfiguration() {
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getMiddle() {
        return middle;
    }
}

