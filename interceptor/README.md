Workflow interceptors are hard to understand.

My goal with this exercise at least is I want to execute behavior _after_ an activity is finished.

https://docs.temporal.io/dev-guide/typescript/features#interceptors (the best docs I found on interceptors is unfortunately on the Javascript SDK).

After playing around with this, I could be convinced that the path forward is to add a combo method on the activity: activityA(), doActivityAndEncore(). It gives reliability guarantees and is easier to manage. It does seem to require potential domain boundaries to be messier than before. (If encore typically is its own set of activities, decoupled from the other one.) TODO: Actually, we could test the reliability guarantees with the interceptor I think. Since it sounds like it might still be able to do retries appropriately if either ActivityA or the Encore activity fails for any reason.

But I guess I got it to work in the end. The Inbound/Outbound distinction can be hard to understand.

It's also hard for me to discern how much of this is typical Java middleware design versus confusion from Temporal SDK specifically.

But hey, it works. And that was the goal.
