# Java Annotation Rules

| Pattern                    | Message                                                                 |
|----------------------------|-------------------------------------------------------------------------|
| `@Ignore`                  | ❌ Error: Old JUnit 4 `@Ignore` detected. Use `@Disabled` (JUnit 5).    |
| `@Before\s+public`         | ❌ Error: Old JUnit 4 `@Before` detected. Use `@BeforeEach` (JUnit 5).  |
| `@After\s+public`          | ❌ Error: Old JUnit 4 `@After` detected. Use `@AfterEach` (JUnit 5).    |
| `@Autowired\s+private`     | ⚠️ Warning: Field injection with `@Autowired` is discouraged. Use Constructor Injection. |
| `@SuppressWarnings\("all"\)`| ❌ Error: Do not suppress "all" warnings. Be specific.                  |
| `@Data`                    | ⚠️ Warning: Lombok `@Data` on Entity classes can cause performance issues (hashCode/equals). Use `@Getter/@Setter`. |