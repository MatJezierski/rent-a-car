package matjez.rentacarapp.commons;

public interface Mapper<F,T> {

    T map (F from);

    F reversedMap (T to);
}
