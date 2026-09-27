# Lab – Dependency Injection med Java och Weld

Del 1: `ManualWiringMain` – allt kopplas ihop för hand via konstruktorer.
Del 2: `ContainerMain` – egen container (`container/Container`) som skapar hela grafen med reflection.
Del 3: `WeldMain` – samma klasser, men Weld sköter injiceringen.

CDI-annotationer som används: `@Inject` (bara på konstruktorer), `@ApplicationScoped`, `@Dependent`.
`beans.xml` har `bean-discovery-mode="annotated"`, så bara klasser med scope-annotation blir beans.
Därför har `InvoicePaymentProcessor` och `SmsNotifier` ingen scope – annars vet Weld inte vilken den ska välja.

Kör: starta main-klasserna i IntelliJ. Tester: `mvn test`
