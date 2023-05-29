package dk.sdu.student.miser21.common.data;

public class Color {
    private float r;
    private float g;
    private float b;
    private float a;

    public Color(float r, float g, float b, float a) {
        setRGBA(r, g, b, a);
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public void setRGBA(float r, float g, float b, float a) {
        setR(r);
        setG(g);
        setB(b);
        setA(a);
    }
}