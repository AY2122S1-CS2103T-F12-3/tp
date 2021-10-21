package seedu.address.ui;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UiManagerTest {


    @Test
    public void constructor_nullLogic_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UiManager(null));
    }
}
