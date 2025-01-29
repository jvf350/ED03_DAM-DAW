package com.comprobar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Unit test for simple App.
 */
@DisplayName("Ejemplos de JUnit5")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Para fijar el orden
public class AppTest {
    @BeforeAll
    public static void setupAll() {
        System.out.println("Preparando las cosas comunes a todos los test. No soy Obligatorio");
    }

    @AfterAll
    public static void finishAll() {
        System.out.println("Aquí va todo lo que hacemos después de todos los test. No soy Obligatorio");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Me ejecuto antes de cada test. No soy Obligatorio");
    }

    @AfterEach
    public void setDown() {
        System.out.println("Me ejecuto después de cada test. No soy Obligatorio");
    }


    // Un test por cada cosa que quiera probar
    @Test
    @DisplayName("True == True")
    @Order(1) // Podemos fijar el orden de ejecución de los test
    public void shouldAnswerWithTrue() {
        // True siempre es igual a true :)
        System.out.println("True == True");
        assertTrue( true );
    }

    @DisplayName("7 == 7")
    @Order(2)
    @EnabledOnOs(value = {OS.LINUX, OS.WINDOWS}) // Solo ejecutar en Windows o Linux, no en Mac
    @EnabledOnJre(value = {JRE.JAVA_21}) // Solo ejecutar en Java 21
    @RepeatedTest(value = 3, name = "{displayName} {currentRepetition}/{totalRepetitions}") // Podemos repetir el test
    public void shouldAnswerWithEquals() {
        // True siempre es igual a true :)
        System.out.println("7 == 7");
        assertEquals(7, 7);
    }

    @Test
    @DisplayName("Arrays iguales")
    @EnabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_18) // Podemos activar o desactivar el test para un rango de JRE
    public void shouldAnswerWithArrayEquals() {
        // Arrange, preparamos el test
        int[] v = {1,2,3,4,5};
        int[] expected = {1,2,3,4,5};
        // Actuamos, en este caso no hacemos nada
        System.out.println("array == array");

        // Comprobamos resultados, Asserts
        assertArrayEquals(v, expected);
    }

    @Test
    @DisplayName("Saludo Test")
    //@Disabled("Test deshabilitado hasta que queramos que no sea así") // Lo deshabilitamos
    public void saludoTest() {
        // Preparo
        String expected = "Hola 1DAM";
        // Actuo
        String res = App.saludo();
        // Compruebo
        assertEquals(expected, res);
    }

    @Tag("Calculadora")
    @Test
    @DisplayName("Calculadora: suma -> 4 + 2 = 6")
    public void addTest() {
        double number1 = 4.0;
        double number2 = 2.0;
        double expResult = 6.0;
        double result = App.add(number1, number2);
        assertEquals(expResult, result);
    }

    @Test
    @Tag("Calculadora")
    @DisplayName("Calculadora: resta -> 4 - 2 = 2")
    @Disabled
    // O podemos pasarle parámetros
    //@ParameterizedTest
    //@CsvSource({"3,2,1", "4,2,2", "4,3,1"})
    public void subtractTest(int number1, int number2, int expResult) {
        //double number1 = 4.0;
        //double number2 = 2.0;
        //double expResult = 2.0;
        double result = App.subtract(number1, number2);
        assertEquals(expResult, result, 0.0);
    }

    @Tag("Calculadora")
    @Test
    @DisplayName("Calculadora: multiplicación -> 4 * 3 = 12")
    public void multiplyTest() {
        double number1 = 4.0;
        double number2 = 3.0;
        double expResult = 12.0;
        double result = App.multiply(number1, number2);
        assertEquals(expResult, result, 0.0); // Delta 0.0: Se espera coincidencia exacta
    }

    @Tag("Calculadora")
    @Test
    @DisplayName("Calculadora: división -> 18/2 = 9")
    public void divideTest() {
        double number1 = 18.0;
        double number2 = 2.0;
        double expResult = 9.001;
        double result = App.divide(number1, number2);
        assertEquals(expResult, result, 0.001); // Delta 0.001: Se permite una diferencia de hasta 0.001
    }

    @Tag("Calculadora")
    @Test
    //@DisplayName("Calculadora: división -> 10/0 -> No permitida")
    void divideTestByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            App.divide(10, 0);
        });

        // Otra forma de hacerlo usando fail en lugar de exp lambda
        try {
            App.divide(10, 0);
            fail("Se esperaba una excepción IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // La excepción fue lanzada correctamente
        }
    }

    @Test
    @DisplayName("Bisiesto Test")
    public void isBisiestoTest() {
        Assertions.assertAll(
                () -> assertTrue(App.isBisiesto(4), "Año 4"),
                () -> assertFalse(App.isBisiesto(100), "Año 100"),
                () -> assertTrue(App.isBisiesto(400), "Año 400"),
                () -> assertFalse(App.isBisiesto(2007), "Año 2007")
        );
//        assertTrue(App.isBisiesto(4), "Año 4");
//        assertFalse(App.isBisiesto(100), "Año 100");
//        assertTrue(App.isBisiesto(400), "Año 400");
//        assertFalse(App.isBisiesto(2007), "Año 207");
//        assertTrue(App.isBisiesto(2008), "Año 2008");
    }

    @Test
    @DisplayName("Primo Test")
    public void isPrimoTest() {
        Assertions.assertAll(
                () -> assertTrue(App.isPrimo(2), "2"),
                () -> assertFalse(App.isPrimo(-1), "-1"),
                () -> assertTrue(App.isPrimo(11), "1"),
                () -> assertTrue(App.isPrimo(17), "17"),
                () -> assertFalse(App.isPrimo(18), "18"),
                () -> assertFalse(App.isPrimo(15), "15")
        );
//        assertTrue(App.isPrimo(2), "2");
//        assertFalse(App.isPrimo(-1), "-1");
//        assertTrue(App.isPrimo(11), "1");
//        assertTrue(App.isPrimo(17), "17");
//        assertFalse(App.isPrimo(18), "18");
//        assertFalse(App.isPrimo(15), "15");
    }

    @Test
    @DisplayName("Palíndromo Test")
    @Disabled
    public void isPalindromoTest() {
        assertTrue(App.isPalindromo("sarabaras"), "sarabaras");
        assertFalse(App.isPalindromo("cacadelavaca"), "cacadelavaca");

        Assertions.assertAll(
                () -> assertTrue(App.isPalindromo("sarabaras"), "sarabaras"),
                () -> assertFalse(App.isPalindromo("cacadelavaca"), "cacadelavaca")
        );
    }

    @Test
    @DisplayName("Ordenación Burbuja Test")
    public void burbujaTest() {
        int[] V = {5, 7, 8, 2};
        int[] expected = {2, 5, 7, 8};
        App.burbuja(V);
        assertArrayEquals(expected, V, "Iguales");
    }

    @Test
    @DisplayName("Busqueda Secuencial Test")
    public void busquedaSecuencialTest() {
        int[] V = {5, 7, 8, 2};
        int expectedTrue = App.busquedaSecuencial(V,2);
        int expectedFalse = App.busquedaSecuencial(V,99);

        assertNotEquals(-1, expectedTrue, "Sí está");
        assertEquals(3, expectedTrue, "Sí está");
        assertEquals(-1, expectedFalse, "No está");
        Assertions.assertAll(
                () -> assertNotEquals(-1, expectedTrue, "Sí está"),
                () -> assertEquals(3, expectedTrue, "Sí está"),
                () -> assertEquals(-1, expectedFalse, "No está")
        );

    }

    @Test
    @DisplayName("Comprobar algo para hacer el test")
    @Disabled
    public void algoTest(){
        String user = System.getenv("USER");
        // Para continuar el test debemos asumir esto
        assumeTrue(user.contains("link"));
        System.out.println("JDK_HOME: " + user);
        // iria el test
        assertTrue(true);

    }
}