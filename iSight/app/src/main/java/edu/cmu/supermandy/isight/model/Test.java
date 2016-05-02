package edu.cmu.supermandy.isight.model;


/**
 * Created by Mandy on 4/4/16.
 */
public abstract class  Test implements Rule{
    private Integer id;
    private String name;
    private String Introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }


}
