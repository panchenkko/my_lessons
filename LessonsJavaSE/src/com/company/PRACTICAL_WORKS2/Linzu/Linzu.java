package com.company.PRACTICAL_WORKS2.Linzu;

public class Linzu extends UvelichitelnoeSteklo {

    private String dlyaKakogoZreniya;
    private String tipZreniya;

    public Linzu(double maxUvelichenie, int size, String dlyaKakogoZreniya, String tipZreniya) {
        super(maxUvelichenie, size);
        this.dlyaKakogoZreniya = dlyaKakogoZreniya;
        this.tipZreniya = tipZreniya;
    }

    @Override
    public String toString() {
        return "Максимальное увеличение: " + getMaxUvelichenie() +
                "; Размер: " + getSize() +
                "см; Для какого зрения: " + this.dlyaKakogoZreniya +
                "; Тип зрения: " + this.tipZreniya;
    }
}
