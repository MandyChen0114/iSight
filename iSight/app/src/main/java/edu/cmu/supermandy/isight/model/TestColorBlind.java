package edu.cmu.supermandy.isight.model;

/**
 * Created by Mandy on 4/11/16.
 */
public class TestColorBlind extends Test {
    String plateType="all";

    int testImgId;
    int resImgId;
    int num_normal;
    int num_red;
    int num_mild_red;
    int num_green;
    int num_mild_green;
    int num_red_green;
    int num_majority;
    int num_total;

    String TestInstruction="Please hold phone with arms straight, and identify the hidden number or line within 5 seconds.";

    String TestResult;
    String res_normal="Normal color vision: ";
    String res_red="Red color blind (protanopia): ";
    String res_mild_red="Mild red color blind (prontanomaly): ";
    String res_green="Green color blind (deuteranopia): ";
    String res_mild_green="Mild green color blind (deuteranomaly): ";
    String res_red_green="Red Green color blind: ";
    String res_total="Total color blind: ";
    String res_majority="Majority of color blind people: ";

    public TestColorBlind() {
    }

    //plate: 1
    public TestColorBlind(int testImgId, int resImgId, int num_normal) {
        this.testImgId = testImgId;
        this.resImgId = resImgId;
        this.num_normal = num_normal;
        this.plateType="all";
    }

    //plate: 2-5
    public TestColorBlind(int testImgId, int resImgId, int num_normal, int num_red_green, int num_total) {
        this.testImgId = testImgId;
        this.resImgId = resImgId;
        this.num_normal = num_normal;
        this.num_red_green = num_red_green;
        this.num_total = num_total;
        this.plateType="normal_redGreen_total";
    }

    // plate: 8, 14, 15
    public TestColorBlind(int testImgId, int resImgId, int num_normal, int num_majority) {
        this.testImgId = testImgId;
        this.resImgId = resImgId;
        this.num_normal = num_normal;
        this.num_majority = num_majority;
        if(num_majority==0) {
            this.plateType = "normal_majority";
        }
        if(num_normal==0){
            this.plateType="normalTotal_redGreen";
        }
    }

    // plate: 16, 17
    public TestColorBlind(int testImgId, int resImgId, int num_normal, int num_red, int num_mild_red, int num_green, int num_mild_green) {
        this.testImgId = testImgId;
        this.resImgId = resImgId;
        this.num_normal = num_normal;
        this.num_red = num_red;
        this.num_mild_red = num_mild_red;
        this.num_green = num_green;
        this.num_mild_green = num_mild_green;
        this.plateType="normal_red2_green2";
    }

    // plate: 18-24
    public TestColorBlind(int testImgId, int resImgId,String plateType) {
        this.testImgId = testImgId;
        this.resImgId = resImgId;
        this.plateType=plateType;
    }

    public int getTestImgId() {
        return testImgId;
    }

    public void setTestImgId(int testImgId) {
        this.testImgId = testImgId;
    }

    public int getResImgId() {
        return resImgId;
    }

    public void setResImgId(int resImgId) {
        this.resImgId = resImgId;
    }

    public void setTestResult(String testResult) {
        TestResult = testResult;
    }

    public String getTestInstruction() {
        return TestInstruction;
    }

    public void setTestInstruction(String testInstruction) {
        TestInstruction = testInstruction;
    }

    @Override
    public String getTestResult() {
        if(plateType.equals("all")){
            this.TestResult="Everyone should see a '12'";
        }else if(plateType.equals("normal_redGreen_total")) {
            this.TestResult = res_normal +"'" +num_normal + "'\n" + res_red_green +"'"+ num_red_green + "'\n" + res_total +"See Nothing";
        }else if(plateType.equals("normal_red2_green2")) {
            this.TestResult = res_normal +"'" +num_normal + "'\n" + res_red +"'"+ num_red + "'\n" + res_mild_red +"'"+ num_mild_red + "'\n" + res_green +"'"+ num_green + "'\n" + res_mild_green +"'"+ num_mild_green + "'";
        }else if(plateType.equals("normal_majority")) {
            this.TestResult = res_normal +"'" +num_normal + "'\n" + res_majority +"See Nothing";
        }else if(plateType.equals("normalTotal_redGreen")) {
            this.TestResult =  res_normal +"See Nothing\n"+res_total +"See Nothing\n" +res_red_green + num_majority;
        }else if(plateType.equals("plate18")) {
            this.TestResult = res_normal+"purple and red lines\n"+res_red+"purple\n"+res_mild_red+"red, with increased difficulty\n"+res_green+"red\n"+res_mild_red+"purple, with increased difficulty";
        }else if(plateType.equals("plate19")) {
            this.TestResult = res_normal+"Nothing\n"+res_total +"See Nothing\nMost "+res_red_green+"wiggly line, depending on the severity of the condition";
        }else if(plateType.equals("plate20")) {
            this.TestResult = res_normal+"green wiggly line\nAny form of color blind: Nothing";
        }else if(plateType.equals("plate22")) {
            this.TestResult = res_normal+"blue-green/yellow-green wiggly line\n"+res_red_green+"blue-green and red line\n"+res_total+"Nothing";
        }else if(plateType.equals("plate23")) {
            this.TestResult = res_normal + "red and orange wiggly line\n" + res_red_green + " red and blue-green wiggly line\n" + res_total + "Nothing";
        }else if(plateType.equals("plate24")) {
            this.TestResult = "Everyone should be able to trace this wiggly line";
        }
        return TestResult;
    }
}
