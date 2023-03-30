package com.company.buildings.logic;

/**
 * This is the House class file that extends Building.
 */
public class House extends Building {
    private String mOwner;
    private boolean mPool;

    public House(int length, int width, int lotLength, int lotWidth) {
        super(length, width, lotLength, lotWidth);
        mOwner = null;
        mPool = false;
    }

    public House(int length, int width, int lotLength, int lotWidth, String owner) {
        super(length, width, lotLength, lotWidth);
        mOwner = owner;
        mPool = false;
    }

    public House(int length, int width, int lotLength, int lotWidth, String owner, boolean pool) {
        super(length, width, lotLength, lotWidth);
        mOwner = owner;
        mPool = pool;
    }

    public String getOwner() {
        return mOwner;
    }

    public boolean hasPool() {
        return mPool;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public void setPool(boolean pool) {
        mPool = pool;
    }

    @Override
    public String toString() {
        String str = "Owner: " + mOwner + ";";
        if (calcLotArea() > calcBuildingArea()) {
            str += " has a big open space";
        }
        if (mPool) {
            str += " has a pool";
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof House)) {
            return false;
        }
        House house = (House) o;
        return calcBuildingArea() == house.calcBuildingArea() && mPool == house.mPool;
    }
}

