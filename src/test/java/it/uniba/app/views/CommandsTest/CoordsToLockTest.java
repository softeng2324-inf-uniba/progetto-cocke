package it.uniba.app.views.CommandsTest;

import it.uniba.app.model.Coordinate;
import it.uniba.app.views.Commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Classe di test per i metodi relativi a COORDS_TO_LOCK in Commands.
 */
class CoordsToLockTest {

    /**
     * Coordinate di cui verificare la presenza nel test di COORDS_TO_LOCK.
     */
    private static Coordinate tempCord;

    /**
     * Coordinate errate di cui verificare la presenza nel test di COORDS_TO_LOCK.
     */
    private static Coordinate tempOutCord;

    /**
     * Posizione di COORDS_TO_LOCK da cui recuperare il contenuto.
     */
    private static int pos;

    /**
     * Posizione errata di COORDS_TO_LOCK da cui recuperare il contenuto.
     */
    private static int outPos;

    /**
     * Dimensione corrente attesa per COORDS_TO_LOCK.
     */
    private static int size;

    /**
     * Costante utile a risolvere gli errori checkstyle relativi ai numeri magici.
     */
    private static final int ANTI_MAGIC_NUM = 3;

    /**
     * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
     */
    static final String IN_CTL = "L'oggetto si trova in COORDS_TO_LOCK";

    /**
     * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
     */
    static final String NOT_IN_CTL = "L'oggetto non si trova in COORDS_TO_LOCK";

    /**
     * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
     */
    static final String NOT_IN_CTL_ATPOS = "L'oggetto non si trova nella posizione 0 di COORDS_TO_LOCK";

    /**
     * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
     */
    static final String INVALID_POS = "Posizione di COORDS_TO_LOCK non accessibile";

    /**
     * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
     */
    static final String SIZE_ERR = "Dimensione corrente di COORDS_TO_LOCK non corretta";

    /**
     * Imposta uno specchio per il vettore COORDS_TO_LOCK in commands, in modo da poterne testare i metodi.
     * Imposta, inoltre, gli altri campi utili ai fini del test.
     * @throws NoSuchFieldException viene lanciata se il campo che si vuole specchiare non esiste.
     * @throws IllegalAccessException viene lanciata se il campo che si vuole specchiare non è accessibile.
     */
    @BeforeAll
    static void setUpCoordsToLockTest() throws NoSuchFieldException, IllegalAccessException {
        Commands com = new Commands();
        Field nameField = com.getClass().getDeclaredField("COORDS_TO_LOCK");
        nameField.setAccessible(true);
        Vector<Coordinate> tempCTL = (Vector<Coordinate>) nameField.get(com);
        pos = 0;
        outPos = ANTI_MAGIC_NUM;
        size++;
        tempCord = new Coordinate(ANTI_MAGIC_NUM, ANTI_MAGIC_NUM - size);
        tempOutCord = new Coordinate(ANTI_MAGIC_NUM * ANTI_MAGIC_NUM, ANTI_MAGIC_NUM + ANTI_MAGIC_NUM - size);
        tempCTL.add(tempCord);
    }

    /**
     * Test del metodo isInCoordsToLock.
     * Verifica se tempCord, precedentemente inserito, è presente in COORDS_TO_LOCK.
     */
    @Test
    void testIsInCoordsToLock() {
        assertTrue(Commands.isInCoordsToLock(tempCord), NOT_IN_CTL);
    }

    /**
     * Test del metodo isInCoordsToLock.
     * Verifica se tempOutCord non è presente in COORDS_TO_LOCK.
     */
    @Test
    void testIsNotInCoordsToLock() {
        assertFalse(Commands.isInCoordsToLock(tempOutCord), IN_CTL);
    }

    /**
     * Test del metodo getCoordToLock.
     * Verifica che tempCord sia in posizione pos di COORDS_TO_LOCK.
     */
    @Test
    void testGetCoordToLock() {
        assertEquals(tempCord, Commands.getCoordToLock(pos), NOT_IN_CTL_ATPOS);
    }

    /**
     * Test del metodo getCoordToLock.
     * Verifica se la funzione in oggetto solleva eccezioni se si accede ad una posizione fuori da COORDS_TO_LOCK.
     */
    @Test
    void testGetOutOfCoordToLock() {
        try {
            Commands.getCoordToLock(outPos);
            fail("Eccezione non raggiunta");
        } catch (Exception e) {
            System.out.println(INVALID_POS);
        }
    }

    /**
     * Test del metodo getCoordToLockSize.
     * Verifica che venga restituita la dimensione corrente corretta di COORDS_TO_LOCK.
     */
    @Test
    void testGetCoordsToLockSize() {
        assertEquals(size, Commands.getCoordsToLockSize(), SIZE_ERR);
    }

}
