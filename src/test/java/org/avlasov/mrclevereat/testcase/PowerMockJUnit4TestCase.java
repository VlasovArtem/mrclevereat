package org.avlasov.mrclevereat.testcase;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created By artemvlasov on 20/01/2018
 **/
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*"})
public abstract class PowerMockJUnit4TestCase {
}
