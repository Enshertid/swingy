package swingy.utils;

public class ActualAndMaxValuePair<T, Z>{
    private T left;
    private Z right;

    public ActualAndMaxValuePair(T left, Z right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public Z getRight() {
        return right;
    }

    public void setRight(Z right) {
        this.right = right;
    }
}
