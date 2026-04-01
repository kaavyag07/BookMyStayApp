# BookMyStayApp Clean Rebuild

This branch contains a clean Java rebuild of the hotel booking project.

## Structure

- `src/` contains shared domain classes and `UseCase1` to `UseCase12`
- `.gitignore` excludes compiled classes and generated persistence files

## Compile

From the `src` folder:

```bash
javac *.java
```

## Run

```bash
java UseCase1HotelBookingApp
java UseCase2RoomInitialization
java UseCase3InventorySetup
java UseCase4RoomSearch
java UseCase5BookingRequestQueue
java UseCase6RoomAllocationService
java UseCase7AddOnServiceSelection
java UseCase8BookingHistoryReport
java UseCase9ErrorHandlingValidation
java UseCase10BookingCancellation
java UseCase11ConcurrentBookingSimulation
java UseCase12DataPersistenceRecovery
```
