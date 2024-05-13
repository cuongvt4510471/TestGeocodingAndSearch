package com.example.test_geocodingandsearch.Model;

public class Item {
    private String title;
    private String id;
    private String resultType;
    private ItemAddress address;
    private Position position;
    private Position[] access;
    private long distance;
    private Category[] categories;
    private Reference[] references;
    private Highlights highlights;

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getResultType() { return resultType; }
    public void setResultType(String value) { this.resultType = value; }

    public ItemAddress getAddress() { return address; }
    public void setAddress(ItemAddress value) { this.address = value; }

    public Position getPosition() { return position; }
    public void setPosition(Position value) { this.position = value; }

    public Position[] getAccess() { return access; }
    public void setAccess(Position[] value) { this.access = value; }

    public long getDistance() { return distance; }
    public void setDistance(long value) { this.distance = value; }

    public Category[] getCategories() { return categories; }
    public void setCategories(Category[] value) { this.categories = value; }

    public Reference[] getReferences() { return references; }
    public void setReferences(Reference[] value) { this.references = value; }

    public Highlights getHighlights() { return highlights; }
    public void setHighlights(Highlights value) { this.highlights = value; }
}
