package interceptor;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface EncoreActivity {
    @ActivityMethod
    void encore();
}
