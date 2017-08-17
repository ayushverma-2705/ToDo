package todo;


public class Pair<K, V, Z, Y> {

    private K element0;
    private V element1;
    private Z element2;
    private Y element3;

    public static <K, V, Z, Y> Pair<K, V, Z,Y> createPair(K element0, V element1, Z element2, Y element3) {
        return new Pair<K, V, Z, Y>(element0, element1, element2, element3);
    }

    public Pair(K element0, V element1, Z element2, Y element3) {
        this.element0 = element0;
        this.element1 = element1;
        this.element2 = element2;
        this.element3 = element3;
    }

    public K getElement0() {
        return element0;
    }

    public V getElement1() {
        return element1;
    }

    public Z getElement2() {
        return element2;
    }

    public Y getElement3() {
        return element3;
    }
}