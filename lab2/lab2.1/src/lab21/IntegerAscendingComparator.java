package lab21;

public class IntegerAscendingComparator implements MyComparator<Integer> {
    @Override
    public int compare(Integer t1, Integer t2) {
        return t1 - t2; // ascending order
    }
}
