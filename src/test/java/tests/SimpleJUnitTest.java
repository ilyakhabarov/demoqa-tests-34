package tests;

import org.junit.jupiter.api.*;

public class SimpleJUnitTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("### beforeAll\n\n");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("### beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("### afterEach\n");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("### afterAll\n");
    }

    @Test
    void firstTest() {
        int result = getResult();
        System.out.println("###   firstTest");
        Assertions.assertTrue(result > 2);
    }

    @Test
    void secondTest() {
        int result = getResult();
        System.out.println("###   secondTest");
        Assertions.assertTrue(result > 2);
    }

    @Test
    void thirdTest() {
        int result = getResult();
        System.out.println("###   thirdTest");
        Assertions.assertTrue(result > 2);
    }

    private int getResult() {
        return 3;
    }
}
