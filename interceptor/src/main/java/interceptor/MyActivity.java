package interceptor;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface MyActivity {
    @ActivityMethod
    void act();
}
