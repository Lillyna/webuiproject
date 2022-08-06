package org.example.homework4;

public class TriangleArea {
    private int a;
    private int b;
    private int c;
    public TriangleArea() {
    }


    public double getTriangleArea(int a, int b, int c) throws InvalidTriangleSidesException {
        if(a<=0 || b<=0 || c<=0){
            throw new InvalidTriangleSidesException();
        }
        double p = (a + b + c)/2.0;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}
