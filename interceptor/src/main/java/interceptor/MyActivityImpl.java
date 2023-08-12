package interceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyActivityImpl implements MyActivity {
    @Override
    public void act() {
        log.info("Squirtle");
    }
}
