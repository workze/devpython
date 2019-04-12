import com.ze.spellchecker.SpellChecker;
import com.ze.spellchecker.WordComparor;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import sun.security.acl.WorldGroupImpl;

import static org.junit.Assert.assertEquals;

/**
 * Created by ZE-C on 2017/12/8.
 */
public class SpellCheckerTest {

    @Test
    public void testHello() {
        assertEquals(1, 1);
        assertEquals(1, 1);
    }

    @Test
    public void testCheckSpell() {
//        new SpellChecker().check();
    }

    @Test
    public void testWord(){
        assertEquals(WordComparor.Status.Equal, new WordComparor().compare("hello","hello"));
        assertEquals(WordComparor.Status.Similar, new WordComparor().compare("kello","hello"));
        assertEquals(WordComparor.Status.Similar, new WordComparor().compare("hkllo","hello"));
        assertEquals(WordComparor.Status.Similar, new WordComparor().compare("hellk","hello"));
        assertEquals(WordComparor.Status.Similar, new WordComparor().compare("ahello","hello"));
        assertEquals(WordComparor.Status.Similar, new WordComparor().compare("ello","hello"));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SpellCheckerTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
