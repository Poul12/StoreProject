package entity;

public class Boots extends Product
{
    private int size;
    private boolean isNaturalSkin;

    public Boots(long id, String productName, float price, float weight, String color, int productCount, int size, boolean isNaturalSkin)
    {
        super(id, productName, price, weight, color, productCount);
        this.size = size;
        this.isNaturalSkin = isNaturalSkin;
    }

    public int getSize()
    {
        return size;
    }

    public boolean isNaturalSkin()
    {
        return isNaturalSkin;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "Boots Size: " + this.size +
                "Is Natural Skin: " + this.isNaturalSkin;
    }
}