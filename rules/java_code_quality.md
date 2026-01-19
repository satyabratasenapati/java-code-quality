# General Code Quality

| Pattern                    | Message                                                                 |
|----------------------------|-------------------------------------------------------------------------|
| `System\.out\.print`       | ❌ Error: `System.out` usage. Use SLF4J/Log4j logging instead.          |
| `System\.err\.print`       | ❌ Error: `System.err` usage. Use SLF4J/Log4j logging instead.          |
| `\.printStackTrace\(\)`    | ❌ Error: `printStackTrace()` swallows the stack trace. Log the error.  |
| `Thread\.sleep\(`          | ❌ Error: `Thread.sleep` creates brittle tests. Use `Awaitility` or polling.|
| `catch\s*\(\w+\s+\w+\)\s*\{\s*\}` | ❌ Error: Empty catch block detected. Never swallow exceptions silently.|
| `==\s*null`                | ⚠️ Warning: Consider using `Objects.isNull()` or `Optional` for better readability. |
| `new\s+String\(`           | ⚠️ Warning: Redundant `new String()`. Just use the string literal `""`. |