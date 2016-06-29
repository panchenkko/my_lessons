package com.company.lessons_average.Search_multiple_values;

class PointsIdentifier {

    private String name;
    private String address;

    public PointsIdentifier(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public boolean contains(String seq) {
        return name.contains(seq) || address.contains(seq);
    }

    @Override
    public boolean equals(Object obj) {
        Points other = (Points) obj;
        return name.equals(other.name) && address.equals(other.address);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + address.hashCode();
    }
}
