package tests;

import org.testng.annotations.Test;

public class ApiSmokeTest {


    @Test(priority = 1)
    public void a() {
        System.out.println("a");
    }

    @Test(priority = 2, description = "")
    public void c() {
        System.out.println("c");
    }

    @Test(priority = 3)
    public void d() {
        System.out.println("d");
    }

    @Test(priority = 4)
    public void b() {
        System.out.println("b");
    }

}
