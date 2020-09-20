package utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Steps {

    private static ExtentTest eachTestCaseSection;

    public static void init(ExtentTest tcSection) {
        eachTestCaseSection = tcSection;
    }

    public static void log(String stepInfo) {
        eachTestCaseSection.info(stepInfo);
    }

    public static void logJson(String content){
        eachTestCaseSection.info(MarkupHelper.createCodeBlock(content, CodeLanguage.JSON));
    }

    public static void imgLog(String message){
        // To Do later
    }
}
