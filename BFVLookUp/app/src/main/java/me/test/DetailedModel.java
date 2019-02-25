package me.test;

public class DetailedModel  {
    public int imageId;
    public String name;
    public Model detail1;
    public Model detail2;

    public DetailedModel(int imageId,String name,Model detail1,Model detail2){
        this.name=name;
        this.imageId=imageId;
        this.detail1=detail1;
        this.detail2=detail2;
    }
}
