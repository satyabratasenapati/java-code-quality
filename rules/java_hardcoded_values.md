# Hardcoded Values & Secrets

| Pattern                                  | Message                                                                 |
|------------------------------------------|-------------------------------------------------------------------------|
| `"(?:[0-9]{1,3}\.){3}[0-9]{1,3}"`        | ❌ Error: IPv4 Address detected. Move IP addresses to configuration.    |
| `password\s*=\s*".+"`                    | ❌ Error: Possible hardcoded password. Use environment variables.       |
| `secret\s*=\s*".+"`                      | ❌ Error: Possible hardcoded secret. Use environment variables.         |
| `"[C|D]:\\[a-zA-Z0-9_]+`                 | ❌ Error: Windows absolute path detected. Use `Path` or relative paths. |
| `"/home/[a-zA-Z0-9_]+`                   | ❌ Error: Linux absolute path detected. Use `Path` or relative paths.   |
| `http://`                                | ⚠️ Warning: Insecure `http://` URL found. Use `https://`.               |