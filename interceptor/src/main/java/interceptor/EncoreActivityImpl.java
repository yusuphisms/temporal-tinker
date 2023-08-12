package interceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncoreActivityImpl implements EncoreActivity {

    @Override
    public void encore() {
        log.info("A well-received encore. Bravissima!");
    }
}
