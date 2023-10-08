public class Unit implements Comparable<Unit> {

    private final Integer id;

    public Unit(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Unit unit) {
        if (unit == null) throw new NullPointerException();
        return this.id.compareTo(unit.getId());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
