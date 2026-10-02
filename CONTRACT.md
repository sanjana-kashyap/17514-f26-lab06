# Contract Worksheet

One section per milestone. Fill each one in as you go, in order. Write each
prediction before you run anything. That is the part a TA asks about.

Keep it short and specific. Point at methods, call sites, and error text.

---

## Milestone 1: The notes overload

### Prediction (write this before you run the build, and you can deliberate with your agent)

**Will the consumer, untouched, still compile and pass?** Yes

**Why.** The compiler maps the existing calls to the first version of
`createBooking()` that the repository came with. The argument counts are
different and the consumer callsites will resolve to the one with 4 args.

### What happened

**The result.**

```
Sanjana@sanjanas-mac 17514-f26-lab06 % mvn -B test
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO]
[INFO] lab06-booking-parent                                               [pom]
[INFO] lab06-api                                                          [jar]
[INFO] lab06-consumer                                                     [jar]
[INFO]
[INFO] -----------------< edu.cmu.cs214:lab06-booking-parent >-----------------
[INFO] Building lab06-booking-parent 1.0.0                                [1/3]
[INFO]   from pom.xml
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] ----------------------< edu.cmu.cs214:lab06-api >-----------------------
[INFO] Building lab06-api 1.0.0                                           [2/3]
[INFO]   from api/pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ lab06-api ---
[INFO] skip non existing resourceDirectory /Users/Sanjana/repos/cmu-agentic-software/17514-f26-lab06/api/src/main/resources
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ lab06-api ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ lab06-api ---
[INFO] skip non existing resourceDirectory /Users/Sanjana/repos/cmu-agentic-software/17514-f26-lab06/api/src/test/resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ lab06-api ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- surefire:3.5.6:test (default-test) @ lab06-api ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running edu.cmu.cs214.booking.InMemoryBookingServiceTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.034 s -- in edu.cmu.cs214.booking.InMemoryBookingServiceTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --------------------< edu.cmu.cs214:lab06-consumer >--------------------
[INFO] Building lab06-consumer 1.0.0                                      [3/3]
[INFO]   from consumer/pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ lab06-consumer ---
[INFO] skip non existing resourceDirectory /Users/Sanjana/repos/cmu-agentic-software/17514-f26-lab06/consumer/src/main/resources
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ lab06-consumer ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ lab06-consumer ---
[INFO] skip non existing resourceDirectory /Users/Sanjana/repos/cmu-agentic-software/17514-f26-lab06/consumer/src/test/resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ lab06-consumer ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- surefire:3.5.6:test (default-test) @ lab06-consumer ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running edu.cmu.cs214.frontdesk.FrontDeskTest
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.045 s -- in edu.cmu.cs214.frontdesk.FrontDeskTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for lab06-booking-parent 1.0.0:
[INFO]
[INFO] lab06-booking-parent ............................... SUCCESS [  0.001 s]
[INFO] lab06-api .......................................... SUCCESS [  0.604 s]
[INFO] lab06-consumer ..................................... SUCCESS [  0.303 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.955 s
[INFO] Finished at: 2026-10-02T09:49:54-04:00
[INFO] ------------------------------------------------------------------------
```

**If your prediction was wrong,** N/A

**Is an additive change always safe in Java?** As long as the other callsites
don't change + the argument counts are different - otherwise we could have
conflicting types.

---

## Milestone 2: The request object

### Prediction (write this before you run the build)

**Will the untouched consumer still compile and pass?** No, the untouched
consumer will not compile. The module that goes red would be the
`lab06-consumer` at compile time.

**Where.** `bookWalkIn` and `joinWaitlist`

**What about the tests in `api/`, after you update them?** Once the tests in
`api/` are updated with the new Request object, they will pass. The same can be
said about the consumer - if we update the callsites accordingly, it can be
compiled and the build will pass.

### Step 1: after the fold

**What the build printed.**

```
Sanjana@sanjanas-mac 17514-f26-lab06 % mvn -B test
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO]
[INFO] lab06-booking-parent                                               [pom]
[INFO] lab06-api                                                          [jar]
[INFO] lab06-consumer                                                     [jar]
[INFO]
[INFO] -----------------< edu.cmu.cs214:lab06-booking-parent >-----------------
[INFO] Building lab06-booking-parent 1.0.0                                [1/3]
[INFO]   from pom.xml
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] ----------------------< edu.cmu.cs214:lab06-api >-----------------------
[INFO] Building lab06-api 1.0.0                                           [2/3]
[INFO]   from api/pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ lab06-api ---
[INFO] skip non existing resourceDirectory /Users/Sanjana/repos/cmu-agentic-software/17514-f26-lab06/api/src/main/resources
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ lab06-api ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ lab06-api ---
[INFO] skip non existing resourceDirectory /Users/Sanjana/repos/cmu-agentic-software/17514-f26-lab06/api/src/test/resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ lab06-api ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- surefire:3.5.6:test (default-test) @ lab06-api ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running edu.cmu.cs214.booking.InMemoryBookingServiceTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.035 s -- in edu.cmu.cs214.booking.InMemoryBookingServiceTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --------------------< edu.cmu.cs214:lab06-consumer >--------------------
[INFO] Building lab06-consumer 1.0.0                                      [3/3]
[INFO]   from consumer/pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ lab06-consumer ---
[INFO] skip non existing resourceDirectory /Users/Sanjana/repos/cmu-agentic-software/17514-f26-lab06/consumer/src/main/resources
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ lab06-consumer ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ lab06-consumer ---
[INFO] skip non existing resourceDirectory /Users/Sanjana/repos/cmu-agentic-software/17514-f26-lab06/consumer/src/test/resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ lab06-consumer ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- surefire:3.5.6:test (default-test) @ lab06-consumer ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running edu.cmu.cs214.frontdesk.FrontDeskTest
[ERROR] Tests run: 7, Failures: 0, Errors: 7, Skipped: 0, Time elapsed: 0.038 s <<< FAILURE! -- in edu.cmu.cs214.frontdesk.FrontDeskTest
[ERROR] edu.cmu.cs214.frontdesk.FrontDeskTest.walkInOnAFreeRoomIsConfirmed -- Time elapsed: 0.017 s <<< ERROR!
java.lang.Error:
Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

        at edu.cmu.cs214.frontdesk.FrontDesk.bookWalkIn(FrontDesk.java:27)
        at edu.cmu.cs214.frontdesk.FrontDeskTest.walkInOnAFreeRoomIsConfirmed(FrontDeskTest.java:23)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)

[ERROR] edu.cmu.cs214.frontdesk.FrontDeskTest.quietCancelLeavesTheWaitlistWhereItWas -- Time elapsed: 0.001 s <<< ERROR!
java.lang.Error:
Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

        at edu.cmu.cs214.frontdesk.FrontDesk.bookWalkIn(FrontDesk.java:27)
        at edu.cmu.cs214.frontdesk.FrontDeskTest.quietCancelLeavesTheWaitlistWhereItWas(FrontDeskTest.java:60)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)

[ERROR] edu.cmu.cs214.frontdesk.FrontDeskTest.walkInOnABusyRoomIsTurnedAway -- Time elapsed: 0.001 s <<< ERROR!
java.lang.Error:
Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

        at edu.cmu.cs214.frontdesk.FrontDesk.bookWalkIn(FrontDesk.java:27)
        at edu.cmu.cs214.frontdesk.FrontDeskTest.walkInOnABusyRoomIsTurnedAway(FrontDeskTest.java:31)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)

[ERROR] edu.cmu.cs214.frontdesk.FrontDeskTest.guestWithANameGoesOnTheWaitlist -- Time elapsed: 0 s <<< ERROR!
java.lang.Error:
Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

        at edu.cmu.cs214.frontdesk.FrontDesk.bookWalkIn(FrontDesk.java:27)
        at edu.cmu.cs214.frontdesk.FrontDeskTest.guestWithANameGoesOnTheWaitlist(FrontDeskTest.java:39)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)

[ERROR] edu.cmu.cs214.frontdesk.FrontDeskTest.cancellingWithAnOfferPromotesTheWaitedGuest -- Time elapsed: 0.001 s <<< ERROR!
java.lang.Error:
Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

        at edu.cmu.cs214.frontdesk.FrontDesk.bookWalkIn(FrontDesk.java:27)
        at edu.cmu.cs214.frontdesk.FrontDeskTest.cancellingWithAnOfferPromotesTheWaitedGuest(FrontDeskTest.java:48)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)

[ERROR] edu.cmu.cs214.frontdesk.FrontDeskTest.scheduleIsOrderedByStartAndHidesCancelledBookings -- Time elapsed: 0 s <<< ERROR!
java.lang.Error:
Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

        at edu.cmu.cs214.frontdesk.FrontDesk.bookWalkIn(FrontDesk.java:27)
        at edu.cmu.cs214.frontdesk.FrontDeskTest.scheduleIsOrderedByStartAndHidesCancelledBookings(FrontDeskTest.java:72)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)

[ERROR] edu.cmu.cs214.frontdesk.FrontDeskTest.waitlistedGuestsAreShownWithTheirName -- Time elapsed: 0.001 s <<< ERROR!
java.lang.Error:
Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

        at edu.cmu.cs214.frontdesk.FrontDesk.bookWalkIn(FrontDesk.java:27)
        at edu.cmu.cs214.frontdesk.FrontDeskTest.waitlistedGuestsAreShownWithTheirName(FrontDeskTest.java:86)
        at java.base/java.lang.reflect.Method.invoke(Method.java:580)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)

[INFO]
[INFO] Results:
[INFO]
[ERROR] Errors:
[ERROR]   FrontDeskTest.cancellingWithAnOfferPromotesTheWaitedGuest:48 »  Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

[ERROR]   FrontDeskTest.guestWithANameGoesOnTheWaitlist:39 »  Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

[ERROR]   FrontDeskTest.quietCancelLeavesTheWaitlistWhereItWas:60 »  Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

[ERROR]   FrontDeskTest.scheduleIsOrderedByStartAndHidesCancelledBookings:72 »  Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

[ERROR]   FrontDeskTest.waitlistedGuestsAreShownWithTheirName:86 »  Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

[ERROR]   FrontDeskTest.walkInOnABusyRoomIsTurnedAway:31 »  Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

[ERROR]   FrontDeskTest.walkInOnAFreeRoomIsConfirmed:23 »  Unresolved compilation problem:
        The method createBooking(BookingRequest) in the type BookingApi is not applicable for the arguments (String, long, long, null)

[INFO]
[ERROR] Tests run: 7, Failures: 0, Errors: 7, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for lab06-booking-parent 1.0.0:
[INFO]
[INFO] lab06-booking-parent ............................... SUCCESS [  0.000 s]
[INFO] lab06-api .......................................... SUCCESS [  0.612 s]
[INFO] lab06-consumer ..................................... FAILURE [  0.328 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.987 s
[INFO] Finished at: 2026-10-02T10:13:52-04:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.6:test (default-test) on project lab06-consumer:
[ERROR]
[ERROR] See /Users/Sanjana/repos/cmu-agentic-software/17514-f26-lab06/consumer/target/surefire-reports for the individual test results.
[ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
[ERROR]
[ERROR] After correcting the problems, you can resume the build with the command
[ERROR]   mvn <args> -rf :lab06-consumer
```

**Which module's tests ran, and which did not.** Modules whose tests ran:
`lab06-booking-parent` and `lab06-api`

Did not run: `lab06-consumer`

The consumer can detect a break in contract.

### Step 2: the deprecation path

**What you added.** The signatures that came back, and what they delegate to.

**The warnings.** Paste one deprecation warning line from the build log (from a
`mvn -B clean test` run, since a rerun with nothing to compile prints none).

**What the deprecation path resolves.** Who can now build that could not build
during step 1, and who is on which schedule.

**What the warnings accomplish that a README note would not.** Be concrete about
where the warning shows up and who sees it without looking for it.

---

## Milestone 3: The misuse critique

Not coded. One misuse, one redesign, one cost. Discuss it with your TA.

### The misuse

**What is easy to get wrong.** One specific thing about the API surface.

**The call site.** File and line in `consumer/`, with the call. Show the code
that a reader cannot understand without opening the javadoc, or that a caller
could get wrong with the compiler still happy.

**What goes wrong when it happens.** Silent bad behavior, wrong data, a crash
somewhere far away?

### The redesign

**The proposal.** Types, enums, factories, or whatever you are proposing. Show
the new signature and the new call site.

**Why the mistake is now hard or impossible to make.** Point at the mechanism,
such as the compiler, a validating constructor, or an exhaustive switch.

### One tradeoff

**What it costs.** Something real, such as caller ceremony, migration burden
against the deprecation path you just built, or more types for a newcomer to
learn. "No real downside" does not count.

**When the price is worth paying.** A condition under which it is.
