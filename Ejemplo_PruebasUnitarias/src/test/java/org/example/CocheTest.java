package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class CocheTest {

    Coche coche;

    @BeforeEach
    void init() {
       this.coche = new Coche("Porsche",12000,500);
    }

    @BeforeAll
    static void beforeAll() { System.out.println("Inicializando tests unitarios"); }

    @AfterAll
    static void afterAll() { System.out.println("Finalizando tests unitarios"); }


        /**
         * Test para el método comprar. Si el método comprar es correcto la prueba debe ser exitosa.
         * Vamos a comprobar si partiendo de un stock de 500 coches si compro 0 coches, el stock se mantiene.
         *
         * @throws Exception
         */
        @Test
        @DisplayName("Test unitario valor válido 0")
        public void testComprarValido1() throws Exception {
            System.out.println("Test de prueba para comprar 0 coches (límite inferior)");
            int cantidad = 0;
            int cantidad_final = 0;
            try {
                Coche coche = new Coche("Porsche",12000,500);
                coche.comprar(cantidad);
                cantidad_final = coche.obtenerStock();
                assertEquals(500, coche.obtenerStock()); /* Como parto de un stock de 500 al comprar 0
                 * coches el stock no debe variar  */
                assertEquals("Porsche", coche.obtenerNombre());
                assertEquals(500, cantidad_final);
            } catch (Exception e) { /* no debería saltar ninguna excepción en este caso,
                por lo que si lo hace es porque algo no está bien y el test debería fallar */
                fail("Se ha producido una excepción no esperada: " + e);
            }
        }

        /**
         * Test para el método comprar. Si el método comprar es correcto la prueba debe ser exitosa.
         * Vamos a comprobar si partiendo de un stock de 500 coches si compro 300 coches
         * más, el stock es 800.
         *
         * @throws Exception
         */
        @Test
        @DisplayName("Test unitario valor válido 300")
        public void testComprarValido2() throws Exception {
            System.out.println("Test de prueba para comprar 300 coches (límite superior)");
            int cantidad = 300;
            int cantidad_final;
            try {
                //Coche coche = new Coche("Porsche",12000,500);
                coche.comprar(cantidad);
                cantidad_final = coche.obtenerStock();
                assertEquals(800, coche.obtenerStock()); /* Como parto de un stock de 500 al comprar 300
                 * coches más ahora el stock es de 800  */
                assertSame("Porsche", coche.obtenerNombre());
                assertEquals(800, cantidad_final);
            } catch (Exception e) { /* no debería saltar ninguna excepción en este caso,
            por lo que si lo hace es porque algo no está bien y el test debería fallar */
                fail("Se ha producido una excepción no esperada: " + e);
            }
        }

        /**
         * Test para el método Comprar. En esta prueba intento comprar una cantidad negativa de
         * coches. Debe saltar la excepción. Con esta prueba comprobamos que el método comprar no
         * acepta números negativos.
         *
         * @throws Exception
         */
        @Test
        @DisplayName("Test unitario valor NO válido -1")
        public void testComprarNoValidoNegativo() throws Exception {
            System.out.println("Test de prueba para comprar un número negativo de coches");
            int cantidad = -1;
            try {
                coche.comprar(cantidad);
                fail("Intento de comprar un número negativo de coches");
            } catch (Exception e) {
                System.out.println(e);
                assertEquals(500, coche.obtenerStock());
                assertFalse(coche.obtenerNombre() == "Ferrari");
                assertNotSame("Ferrari", coche.obtenerNombre());
            }
        }

        /**
         * Test para el método Comprar. En esta prueba intento comprar una cantidad mayor de
         * la permitida de coches. Debe saltar la excepción. Con esta prueba comprobamos que el
         * método comprar no acepta números por encima del límite superior del rango.
         *
         * @throws Exception
         */
        @Test
        @DisplayName("Test unitario valor NO válido 301")
        public void testComprarNoValidoMayor() throws Exception {
            System.out.println("Test de prueba para comprar un número mayor no permitido de coches");
            int cantidad = 301;
            int cantidad_final = 0;
            try {
                coche.comprar(cantidad);
                cantidad_final = coche.obtenerStock();
                fail("Intento de comprar un número negativo de coches");
            } catch (Exception e) {
                System.out.println(e);
                assertEquals(500, coche.obtenerStock());
                assertFalse(coche.obtenerNombre() == "Ferrari");
                assertNotSame("Ferrari", coche.obtenerNombre());
            }
        }

    /*
    @Test
    void vender() {
    }
    */
}